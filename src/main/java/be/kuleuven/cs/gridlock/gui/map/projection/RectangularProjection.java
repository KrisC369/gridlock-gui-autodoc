/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.projection;

import be.kuleuven.cs.gridlock.gui.map.projection.Projection;
import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.awt.geom.Point2D;

// TODO: Auto-generated Javadoc
/**
 * The Class RectangularProjection.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class RectangularProjection implements Projection {

    private static final double WGS84_VERTICAL = 110574;
    private static final double WGS84_HORIZONTAL = 111320;

    private final double horizontalMeterPerRadian;

    private final double verticalMeterPerRadian;

    /**
     * Instantiates a new rectangular projection.
     */
    public RectangularProjection() {
        this( WGS84_HORIZONTAL, WGS84_VERTICAL );
    }

    /**
     * Instantiates a new rectangular projection.
     * 
     * @param horizontalMeterPerRadian
     *          the horizontal meter per radian
     * @param verticalMeterPerRadian
     *          the vertical meter per radian
     */
    public RectangularProjection(double horizontalMeterPerRadian, double verticalMeterPerRadian) {
        this.horizontalMeterPerRadian = horizontalMeterPerRadian;
        this.verticalMeterPerRadian = verticalMeterPerRadian;
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.projection.Projection#map(be.kuleuven.cs.gridlock.geo.coordinates.Coordinates)
     */
    @Override
    public Point2D map(Coordinates coordinates) {
        double y = coordinates.getLatitudeInRadians() * verticalMeterPerRadian;
        double x = coordinates.getLongitudeInRadians() * horizontalMeterPerRadian;
        return new Point2D.Double( x, y );
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.projection.Projection#map(java.awt.geom.Point2D)
     */
    @Override
    public Coordinates map(Point2D point) {
        double lat = point.getY() / verticalMeterPerRadian;
        double lon = point.getX() / horizontalMeterPerRadian;

        return Coordinates.coordinatesAt( lat, lon );
    }


}