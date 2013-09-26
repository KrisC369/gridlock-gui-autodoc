/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.projection;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.awt.geom.Point2D;

// TODO: Auto-generated Javadoc
/**
 * The Class MercatorProjection.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class MercatorProjection implements Projection {

    private static final double RADIUS = 6378100;
    private static final double lambdaZero = 0;

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.projection.Projection#map(be.kuleuven.cs.gridlock.geo.coordinates.Coordinates)
     */
    @Override
    public Point2D map( Coordinates coordinates ) {
        final double phi = coordinates.getLatitudeInRadians();
        final double lambda = coordinates.getLongitudeInRadians();

        double x = RADIUS * ( lambda - lambdaZero );
        double y = RADIUS * Math.log( ( 1 + Math.sin( phi ) ) / Math.cos( phi ) );

        return new Point2D.Double( x, y );
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.projection.Projection#map(java.awt.geom.Point2D)
     */
    @Override
    public Coordinates map( Point2D point ) {
        final double lon = point.getX() / RADIUS + lambdaZero;
        final double lat = 1 / Math.tan( Math.sinh( point.getY() / RADIUS ) );

        return Coordinates.coordinatesAt( lat, lon );
    }

}