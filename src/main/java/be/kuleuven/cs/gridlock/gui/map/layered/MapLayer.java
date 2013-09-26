package be.kuleuven.cs.gridlock.gui.map.layered;

import be.kuleuven.cs.gridlock.gui.map.PixelMapper;
import java.awt.Graphics2D;

/**
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public interface MapLayer {

    public void paintLayer( PixelMapper mapper, Graphics2D graphics);

}