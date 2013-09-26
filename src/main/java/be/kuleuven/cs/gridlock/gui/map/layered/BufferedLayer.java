package be.kuleuven.cs.gridlock.gui.map.layered;

import be.kuleuven.cs.gridlock.gui.map.PixelMapper;
import be.kuleuven.cs.gridlock.gui.map.PixelMapperObserver;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class BufferedLayer implements MapLayer, PixelMapperObserver {

    private final MapLayer delegate;
    private PixelMapper mapper;
    private BufferedImage image;

    protected BufferedLayer( MapLayer delegate ) {
        this.delegate = delegate;
    }

    @Override
    public void notifyOfChange( PixelMapper mapper ) {
        this.reset();
    }

    @Override
    public void paintLayer( PixelMapper mapper, Graphics2D graphics ) {
        if( this.mapper != mapper ) {
            if( this.mapper != null ) {
                mapper.removeObserver( this );
            }
            this.mapper = mapper;
            this.mapper.addObserver( this );
            this.reset();
        }

        if( this.image != null ) {
            graphics.drawImage( image, null, null );
        }
    }

    public void reset() {
        Dimension dimensions = mapper.getDimensions();
        final BufferedImage newImage = new BufferedImage( dimensions.width, dimensions.height, BufferedImage.TYPE_INT_ARGB );
        Graphics2D imageGraphics = GraphicsEnvironment.getLocalGraphicsEnvironment().createGraphics( newImage );
        imageGraphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        delegate.paintLayer( mapper, imageGraphics );
        imageGraphics.dispose();
        image = newImage;
    }

    public static BufferedLayer buffer( MapLayer layer ) {
        if( layer instanceof BufferedLayer ) {
            return (BufferedLayer) layer;
        }
        return new BufferedLayer( layer );
    }
}