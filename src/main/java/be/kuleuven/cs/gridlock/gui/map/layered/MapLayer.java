/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.layered;

import be.kuleuven.cs.gridlock.gui.map.PixelMapper;
import java.awt.Graphics2D;

// TODO: Auto-generated Javadoc
/**
 * The Interface MapLayer.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public interface MapLayer {

    /**
     * Paint layer.
     * 
     * @param mapper
     *          the mapper
     * @param graphics
     *          the graphics
     */
    public void paintLayer( PixelMapper mapper, Graphics2D graphics);

}