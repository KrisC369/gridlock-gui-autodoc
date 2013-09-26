package be.kuleuven.cs.gridlock.gui.map;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public interface PixelMapper {

    boolean isReady();

    int[] mapPoint( Point2D point );

    int[][] mapPoints( List<Point2D> points );

    int[] mapCoordinates( Coordinates coordinates );

    int[][] mapCoordinates( List<Coordinates> coordinates );

    void update( Coordinates coordinates );

    void update( Dimension dimension );

    void addObserver( PixelMapperObserver observer );

    void removeObserver( PixelMapperObserver observer );

    public Dimension getDimensions();
}
