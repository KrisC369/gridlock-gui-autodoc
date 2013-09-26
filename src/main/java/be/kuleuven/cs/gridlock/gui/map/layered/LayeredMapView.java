/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.layered;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import be.kuleuven.cs.gridlock.gui.map.MapView;
import be.kuleuven.cs.gridlock.gui.map.projection.MercatorProjection;
import be.kuleuven.cs.gridlock.gui.map.projection.Projection;
import be.kuleuven.cs.gridlock.simulation.SimulationComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class LayeredMapView.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class LayeredMapView extends MapView {

    private final List<MapLayer> layers;

    /**
     * Instantiates a new layered map view.
     * 
     * @param projection
     *          the projection
     */
    public LayeredMapView( Projection projection ) {
        super( projection );
        this.layers = new LinkedList<MapLayer>();
    }

    /**
     * Instantiates a new layered map view.
     * 
     * @param projection
     *          the projection
     * @param layers
     *          the layers
     */
    public LayeredMapView( Projection projection, MapLayer... layers ) {
        this( projection );
        for( MapLayer layer : layers ) {
            this.addLayer( layer );
        }
    }

    /**
     * Adds the layer.
     * 
     * @param layer
     *          the layer
     */
    public void addLayer( MapLayer layer ) {
        synchronized( this.layers ) {
            this.layers.add( layer );
        }
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.MapView#paintMap(java.awt.Graphics)
     */
    @Override
    protected void paintMap( Graphics g ) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        for( MapLayer layer : layers ) {
            final Graphics2D layerGraphics = (Graphics2D)graphics.create();
            layer.paintLayer( this.getPixelMapper(), layerGraphics);
            layerGraphics.dispose();
        }

        graphics.dispose();
    }

    /**
     * Show.
     * 
     * @param layers
     *          the layers
     * @return the simulation component
     */
    public static SimulationComponent show( final MapLayer... layers ) {
      final ActiveLayeredMapView view = new ActiveLayeredMapView( new MercatorProjection(), layers );
        //final ActiveLayeredMapView view = new ActiveLayeredMapView( new RectangularProjection(), layers );
        EventQueue.invokeLater( new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame( "Map view" );
                frame.add( view );
                frame.pack();
                frame.setExtendedState( JFrame.MAXIMIZED_BOTH );
                frame.setVisible( true );
                frame.setExtendedState( JFrame.MAXIMIZED_BOTH );
            }
        } );

        return view;
        // GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        // device.setFullScreenWindow( frame );
    }
}