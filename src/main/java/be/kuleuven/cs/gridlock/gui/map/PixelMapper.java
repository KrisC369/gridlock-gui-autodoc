/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface PixelMapper.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public interface PixelMapper {

    /**
     * Checks if is ready.
     * 
     * @return true, if is ready
     */
    boolean isReady();

    /**
     * Map point.
     * 
     * @param point
     *          the point
     * @return the int[]
     */
    int[] mapPoint( Point2D point );

    /**
     * Map points.
     * 
     * @param points
     *          the points
     * @return the int[][]
     */
    int[][] mapPoints( List<Point2D> points );

    /**
     * Map coordinates.
     * 
     * @param coordinates
     *          the coordinates
     * @return the int[]
     */
    int[] mapCoordinates( Coordinates coordinates );

    /**
     * Map coordinates.
     * 
     * @param coordinates
     *          the coordinates
     * @return the int[][]
     */
    int[][] mapCoordinates( List<Coordinates> coordinates );

    /**
     * Update.
     * 
     * @param coordinates
     *          the coordinates
     */
    void update( Coordinates coordinates );

    /**
     * Update.
     * 
     * @param dimension
     *          the dimension
     */
    void update( Dimension dimension );

    /**
     * Adds the observer.
     * 
     * @param observer
     *          the observer
     */
    void addObserver( PixelMapperObserver observer );

    /**
     * Removes the observer.
     * 
     * @param observer
     *          the observer
     */
    void removeObserver( PixelMapperObserver observer );

    /**
     * Gets the dimensions.
     * 
     * @return the dimensions
     */
    public Dimension getDimensions();
}
