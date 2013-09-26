/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map;

import be.kuleuven.cs.gridlock.gui.map.projection.Projection;
import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class AutofitPixelMapper.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class AutofitPixelMapper implements PixelMapper {

    /** The Constant OFFSET. */
    public static final int OFFSET = 10;

    private double minX = Double.POSITIVE_INFINITY;
    private double maxX = Double.NEGATIVE_INFINITY;
    private double minY = Double.POSITIVE_INFINITY;
    private double maxY = Double.NEGATIVE_INFINITY;

    private double offsetX;
    private double offsetY;

    private final Projection projection;

    private Dimension dimensions;
    private double scale = 1;

    private final Set<PixelMapperObserver> observers;

    /**
     * Instantiates a new autofit pixel mapper.
     * 
     * @param projection
     *          the projection
     */
    public AutofitPixelMapper( Projection projection ) {
        this.projection = projection;
        this.observers = new HashSet<PixelMapperObserver>();
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#update(be.kuleuven.cs.gridlock.geo.coordinates.Coordinates)
     */
    @Override
    public void update( Coordinates coordinates ) {
        update( this.projection.map( coordinates ) );
    }

    private void update( Point2D point ) {
        boolean change = false;
        if( point.getX() < minX ) {
            this.minX = point.getX();
            change = true;
        }
        if( point.getX() > maxX ) {
            this.maxX = point.getX();
            change = true;
        }
        if( point.getY() < minY ) {
            this.minY = point.getY();
            change = true;
        }
        if( point.getY() > maxY ) {
            this.maxY = point.getY();
            change = true;
        }

        if( change ) {
            this.notifyObservers();
        }
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#update(java.awt.Dimension)
     */
    @Override
    public void update( Dimension dimension ) {
        if( dimension != null ) {
            this.dimensions = dimension;

            double xRange = maxX - minX;
            double yRange = maxY - minY;

            double xScale = (dimension.getWidth() - (2*OFFSET)) / xRange;
            double yScale = (dimension.getHeight() - (2*OFFSET)) / yRange;

            this.scale = xScale < yScale ? xScale : yScale;

            this.offsetX = OFFSET + ( this.dimensions.getWidth() - OFFSET * 2 - xRange * scale ) / 2d;
            this.offsetY = OFFSET + ( this.dimensions.getHeight() - OFFSET * 2 - yRange * scale ) / 2d;

            this.notifyObservers();
        }
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#mapPoint(java.awt.geom.Point2D)
     */
    @Override
    public int[] mapPoint( Point2D point ) {
        if( point != null && point.getX() > this.maxX || point.getX() < this.minX ) {
            this.update( point );
        }
        if( point != null && point.getY() > this.maxY || point.getX() < this.minY ) {
            this.update( point );
        }

        if( this.dimensions != null && point != null ) {
            double x = offsetX + ( point.getX() - minX ) * scale;
            double y = this.dimensions.getHeight() - offsetY - (point.getY() - minY ) * scale;

            return new int[] { (int)x, (int)y };
        }
        else {
            return new int[] { 0, 0 };
        }
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#mapPoints(java.util.List)
     */
    @Override
    public int[][] mapPoints( List<Point2D> pointList ) {
        int[][] points = new int[2][ pointList.size() ];
        for( int i = 0; i < pointList.size(); i++ ) {
            int[] point = this.mapPoint( pointList.get( i ) );
            points[0][i] = point[0];
            points[1][i] = point[1];
        }
        return points;
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#mapCoordinates(be.kuleuven.cs.gridlock.geo.coordinates.Coordinates)
     */
    @Override
    public int[] mapCoordinates( Coordinates coordinates ) {
        return this.mapPoint( this.projection.map( coordinates ) );
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#mapCoordinates(java.util.List)
     */
    @Override
    public int[][] mapCoordinates( List<Coordinates> coordinates ) {
        int[][] points = new int[2][ coordinates.size() ];
        for( int i = 0; i < coordinates.size(); i++ ) {
            int[] point = this.mapCoordinates( coordinates.get( i ) );
            points[0][i] = point[0];
            points[1][i] = point[1];
        }
        return points;
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#isReady()
     */
    @Override
    public boolean isReady() {
        return this.dimensions != null && !Double.isInfinite( this.minX );
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#addObserver(be.kuleuven.cs.gridlock.gui.map.PixelMapperObserver)
     */
    @Override
    public void addObserver( PixelMapperObserver observer ) {
        synchronized( this.observers ) {
            this.observers.add( observer );
        }
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#removeObserver(be.kuleuven.cs.gridlock.gui.map.PixelMapperObserver)
     */
    @Override
    public void removeObserver( PixelMapperObserver observer ) {
        synchronized( this.observers ) {
            this.observers.remove( observer );
        }
    }

    private void notifyObservers() {
        synchronized( this.observers ) {
            for( PixelMapperObserver observer : this.observers ) {
                observer.notifyOfChange( this );
            }
        }
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.PixelMapper#getDimensions()
     */
    @Override
    public Dimension getDimensions() {
        return this.dimensions;
    }
}