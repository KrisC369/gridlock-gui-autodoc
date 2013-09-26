/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.kuleuven.cs.gridlock.gui.map.projection;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.awt.geom.Point2D;

/**
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public interface Projection {

    public Point2D map( Coordinates coordinates );

    public Coordinates map( Point2D point );

}