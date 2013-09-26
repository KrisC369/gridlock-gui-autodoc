/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.cs.gridlock.gui.map.projection;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.awt.geom.Point2D;

// TODO: Auto-generated Javadoc
/**
 * The Interface Projection.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public interface Projection {

    /**
     * Map.
     * 
     * @param coordinates
     *          the coordinates
     * @return the point2 d
     */
    public Point2D map( Coordinates coordinates );

    /**
     * Map.
     * 
     * @param point
     *          the point
     * @return the coordinates
     */
    public Coordinates map( Point2D point );

}