package be.kuleuven.cs.gridlock.gui.map.layered;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import be.kuleuven.cs.gridlock.gui.map.PixelMapper;
import be.kuleuven.cs.gridlock.simulation.api.LinkReference;
import be.kuleuven.cs.gridlock.simulation.api.NodeReference;
import be.kuleuven.cs.gridlock.utilities.graph.Edge;
import be.kuleuven.cs.gridlock.utilities.graph.Graph;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class NetworkLayer implements MapLayer {

    private Stroke stroke = new BasicStroke( 1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );

    private Color color = Color.LIGHT_GRAY;

    private Graph<NodeReference,LinkReference> graph;

    private boolean detailed = true;

    public NetworkLayer( Graph<NodeReference, LinkReference> graph, boolean detailed ) {
        this.graph = graph;
        this.detailed = detailed;
    }

    public NetworkLayer( Graph<NodeReference, LinkReference> graph ) {
        this( graph, true );
    }

    @Override
    public void paintLayer( PixelMapper mapper, Graphics2D graphics) {
        for( LinkReference edge : graph.getEdges() ) {
            this.paintEdge( edge, mapper, graphics );
        }
    }

    @SuppressWarnings( "unchecked" )
    private void paintEdge( LinkReference reference, PixelMapper mapper, Graphics2D graphics ) {
        List<Coordinates> coordinates;
        Edge<NodeReference,LinkReference> edge = graph.getEdge( reference );
        if( this.detailed ) {
            coordinates = (List<Coordinates>)edge.getAnnotation( "coordinates" );
        }
        else {
            coordinates = Arrays.asList( (Coordinates)edge.getOrigin().getAnnotation( "location" ), (Coordinates)edge.getDestination().getAnnotation( "location" ) );
        }

        int[][] points = mapCoordinates( coordinates, mapper );
        graphics.setColor( this.chooseColor( reference ) );
        graphics.setStroke( this.chooseStroke( reference ) );
        graphics.drawPolyline( points[0], points[1], points[0].length );
    }

    protected int[][] mapCoordinates( List<Coordinates> coordinates, PixelMapper mapper ) {
        int[][] points = new int[2][coordinates.size()];

        for( int i = 0; i < coordinates.size(); i++ ) {
            int[] point = mapper.mapCoordinates( coordinates.get(i) );
            points[0][i] = point[0];
            points[1][i] = point[1];
        }

        return points;
    }

    private Color chooseColor( LinkReference reference ) {
        return this.color;
    }

    private Stroke chooseStroke( LinkReference reference ) {
        return this.stroke;
    }
    
    protected Color getColor() {
        return color;
    }

    protected boolean isDetailed() {
        return detailed;
    }

    protected Graph<NodeReference, LinkReference> getGraph() {
        return graph;
    }

    protected Stroke getStroke() {
        return stroke;
    }
}
