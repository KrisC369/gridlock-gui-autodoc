/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.layered;

import be.kuleuven.cs.gridlock.gui.map.PixelMapper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

// TODO: Auto-generated Javadoc
/**
 * The Class MapBackgroundLayer.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class MapBackgroundLayer implements MapLayer {

    private Color backgroundColor;

    /**
     * Instantiates a new map background layer.
     * 
     * @param backgroundColor
     *          the background color
     */
    public MapBackgroundLayer( Color backgroundColor ) {
        this.backgroundColor = backgroundColor;
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.layered.MapLayer#paintLayer(be.kuleuven.cs.gridlock.gui.map.PixelMapper, java.awt.Graphics2D)
     */
    @Override
    public void paintLayer( PixelMapper mapper, Graphics2D graphics) {
        Dimension dimension = mapper.getDimensions();
        graphics.setColor( this.backgroundColor );
        graphics.fillRect( 0, 0, dimension.width, dimension.height );
    }

}
