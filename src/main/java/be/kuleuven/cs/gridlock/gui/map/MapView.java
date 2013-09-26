package be.kuleuven.cs.gridlock.gui.map;

import be.kuleuven.cs.gridlock.gui.map.projection.Projection;
import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

/**
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public abstract class MapView extends JComponent implements ComponentListener {

    private final PixelMapper mapper;

    public MapView( PixelMapper mapper ) {
        this.mapper = mapper;
        this.addComponentListener( this );
    }

    public MapView( Projection projection ) {
        this( new AutofitPixelMapper( projection ) );
    }

    @Override
    public void componentHidden( ComponentEvent ce ) {
        // NO-OP
    }

    @Override
    public void componentMoved( ComponentEvent ce ) {
        // NO-OP
    }

    @Override
    public void componentResized( ComponentEvent ce ) {
        this.calculateScale();
    }

    @Override
    public void componentShown( ComponentEvent ce ) {
        this.calculateScale();
    }

    @Override
    public Dimension getMaximumSize() {
        return super.getMaximumSize();
    }

    @Override
    public Dimension getMinimumSize() {
        return super.getMinimumSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }

    @Override
    public final void paint( Graphics graphics ) {
        if( !this.mapper.isReady() ) {
            this.calculateScale();
        }

        this.paintMap( graphics );
    }

    protected PixelMapper getPixelMapper() {
        return mapper;
    }

    public void calculateScale() {
        this.mapper.update( this.getSize() );
    }

    public void update( Coordinates coordinates ) {
        mapper.update( coordinates );
    }

    public int[] map( Coordinates coordinates ) {
        return mapper.mapCoordinates( coordinates );
    }

    public int[] map( Point2D point ) {
        return mapper.mapPoint( point );
    }

    protected abstract void paintMap( Graphics graphics );
}
