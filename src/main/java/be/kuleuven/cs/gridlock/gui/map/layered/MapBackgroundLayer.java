package be.kuleuven.cs.gridlock.gui.map.layered;

import be.kuleuven.cs.gridlock.gui.map.PixelMapper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class MapBackgroundLayer implements MapLayer {

    private Color backgroundColor;

    public MapBackgroundLayer( Color backgroundColor ) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void paintLayer( PixelMapper mapper, Graphics2D graphics) {
        Dimension dimension = mapper.getDimensions();
        graphics.setColor( this.backgroundColor );
        graphics.fillRect( 0, 0, dimension.width, dimension.height );
    }

}
