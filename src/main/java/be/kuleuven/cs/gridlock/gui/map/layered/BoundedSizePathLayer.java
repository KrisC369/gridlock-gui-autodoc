/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.layered;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import be.kuleuven.cs.gridlock.gui.map.PixelMapper;
import be.kuleuven.cs.gridlock.gui.map.utils.GenericCircularFifoBuffer;
import be.kuleuven.cs.gridlock.routing.Path;
import be.kuleuven.cs.gridlock.routing.RoutingService;
import be.kuleuven.cs.gridlock.simulation.SimulationContext;
import be.kuleuven.cs.gridlock.simulation.api.LinkReference;
import be.kuleuven.cs.gridlock.simulation.api.NodeReference;
import be.kuleuven.cs.gridlock.simulation.events.Event;
import be.kuleuven.cs.gridlock.simulation.events.EventFilter;
import be.kuleuven.cs.gridlock.simulation.events.EventListener;
import be.kuleuven.cs.gridlock.simulation.model.SimulationModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class BoundedSizePathLayer.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class BoundedSizePathLayer implements MapLayer {

    private final Queue<Path> queue;

    private final  Collection<List<Coordinates>> coordinates;

    /** The context. */
    protected final SimulationContext context;

    private final Random random = new Random();

    /**
     * Instantiates a new bounded size path layer.
     * 
     * @param context
     *          the context
     */
    public BoundedSizePathLayer( SimulationContext context ) {
        this.context = context;
        this.coordinates = new GenericCircularFifoBuffer<List<Coordinates>>(250);
        this.queue = new LinkedList<Path>();
    }

    /**
     * Adds the path.
     * 
     * @param path
     *          the path
     */
    public void addPath( Path path ) {
        synchronized( this.coordinates ) {
            this.queue.add( path );
        }
    }

    private List<Coordinates> resolve( Path path ) {
        List<NodeReference> nodeList = path.getNodes();
        List<Coordinates> coordinateList = new ArrayList<Coordinates>( nodeList.size() );
        for( NodeReference reference : nodeList ) {
            LinkReference link = path.getLinkAfter( reference);
            if( link != null ) {
                final List<Coordinates> linkCoordinates = context.getSimulationComponent( SimulationModel.class ).getLinkElement( link ).getCoordinates();
                coordinateList.addAll( linkCoordinates );
            }
        }
        return coordinateList;
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.layered.MapLayer#paintLayer(be.kuleuven.cs.gridlock.gui.map.PixelMapper, java.awt.Graphics2D)
     */
    @Override
    public void paintLayer( PixelMapper mapper, Graphics2D graphics) {
        synchronized( this.coordinates ) {
            while( !this.queue.isEmpty() ) {
                this.coordinates.add( this.resolve( this.queue.poll() ) );
            }

            for( List<Coordinates> path : this.coordinates ) {
                this.paintPath( path, mapper, graphics );
            }
        }
    }

    private void distort( int[][] points ) {
        for( int i = 0; i < points[0].length; i++ ) {
            points[0][i] += random.nextGaussian() * 2;
            points[1][i] += random.nextGaussian() * 2;
        }
    }

    private void paintPath( List<Coordinates> path, PixelMapper mapper, Graphics2D graphics ) {
        int[][] points = mapper.mapCoordinates( path );
        distort( points );
        graphics.setColor( new Color( 255, 0, 0, 25 ) );
        graphics.setStroke( new BasicStroke( 1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND ) );
        graphics.drawPolyline( points[0], points[1], path.size() );
    }

    /**
     * The Class VehiclePathLayer.
     */
    public static class VehiclePathLayer extends BoundedSizePathLayer implements EventListener, EventFilter {

        private final RoutingService routing;

        /**
         * Instantiates a new vehicle path layer.
         * 
         * @param context
         *          the context
         * @param routing
         *          the routing
         */
        public VehiclePathLayer( SimulationContext context, RoutingService routing ) {
            super( context );
            this.routing = routing;
            context.getEventController().registerListener( this, this );
        }

        /* (non-Javadoc)
         * @see be.kuleuven.cs.gridlock.simulation.events.EventListener#notifyOf(be.kuleuven.cs.gridlock.simulation.events.Event)
         */
        @Override
        public void notifyOf( Event event ) {
            NodeReference origin = event.getAttribute( "origin", NodeReference.class );
            NodeReference destination = event.getAttribute( "destination", NodeReference.class );

            Path path = this.routing.route( origin, destination, this.context.getSimulationComponent( SimulationModel.class ).getGraph() );
            if( path != null ) {
                this.addPath( path );
            }
        }

        /* (non-Javadoc)
         * @see be.kuleuven.cs.gridlock.simulation.events.EventFilter#pass(be.kuleuven.cs.gridlock.simulation.events.Event)
         */
        @Override
        public boolean pass( Event event ) {
            return event.getType().equals( "vehicle:creation" );
        }
    }
}