/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map;

import be.kuleuven.cs.gridlock.gui.map.projection.Projection;
import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class MapView.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public abstract class MapView extends JComponent implements ComponentListener {

    private final PixelMapper mapper;

    /**
     * Instantiates a new map view.
     * 
     * @param mapper
     *          the mapper
     */
    public MapView( PixelMapper mapper ) {
        this.mapper = mapper;
        this.addComponentListener( this );
    }

    /**
     * Instantiates a new map view.
     * 
     * @param projection
     *          the projection
     */
    public MapView( Projection projection ) {
        this( new AutofitPixelMapper( projection ) );
    }

    /* (non-Javadoc)
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
     */
    @Override
    public void componentHidden( ComponentEvent ce ) {
        // NO-OP
    }

    /* (non-Javadoc)
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
     */
    @Override
    public void componentMoved( ComponentEvent ce ) {
        // NO-OP
    }

    /* (non-Javadoc)
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
     */
    @Override
    public void componentResized( ComponentEvent ce ) {
        this.calculateScale();
    }

    /* (non-Javadoc)
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
     */
    @Override
    public void componentShown( ComponentEvent ce ) {
        this.calculateScale();
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#getMaximumSize()
     */
    @Override
    public Dimension getMaximumSize() {
        return super.getMaximumSize();
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#getMinimumSize()
     */
    @Override
    public Dimension getMinimumSize() {
        return super.getMinimumSize();
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#getPreferredSize()
     */
    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public final void paint( Graphics graphics ) {
        if( !this.mapper.isReady() ) {
            this.calculateScale();
        }

        this.paintMap( graphics );
    }

    /**
     * Gets the pixel mapper.
     * 
     * @return the pixel mapper
     */
    protected PixelMapper getPixelMapper() {
        return mapper;
    }

    /**
     * Calculate scale.
     */
    public void calculateScale() {
        this.mapper.update( this.getSize() );
    }

    /**
     * Update.
     * 
     * @param coordinates
     *          the coordinates
     */
    public void update( Coordinates coordinates ) {
        mapper.update( coordinates );
    }

    /**
     * Map.
     * 
     * @param coordinates
     *          the coordinates
     * @return the int[]
     */
    public int[] map( Coordinates coordinates ) {
        return mapper.mapCoordinates( coordinates );
    }

    /**
     * Map.
     * 
     * @param point
     *          the point
     * @return the int[]
     */
    public int[] map( Point2D point ) {
        return mapper.mapPoint( point );
    }

    /**
     * Paint map.
     * 
     * @param graphics
     *          the graphics
     */
    protected abstract void paintMap( Graphics graphics );
}
