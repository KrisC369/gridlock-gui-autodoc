/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.osm;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import be.kuleuven.cs.gridlock.gui.map.PixelMapper;
import be.kuleuven.cs.gridlock.gui.map.layered.MapLayer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class PolyShapeLayer.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class PolyShapeLayer implements MapLayer {

    private final List<List<Coordinates>> shapes;
    private final List<List<Coordinates>> cutouts;

    private final Color shapeColor = Color.YELLOW;
    private final Color cutoutColor = Color.YELLOW;

    /**
     * Instantiates a new poly shape layer.
     * 
     * @param stream
     *          the stream
     * @throws IOException
     *           Signals that an I/O exception has occurred.
     */
    public PolyShapeLayer( InputStream stream ) throws IOException {
        PolygonFileParser parser = new PolygonFileParser( stream );
        this.shapes = new ArrayList<List<Coordinates>>( parser.getShapes() );
        this.cutouts = new ArrayList<List<Coordinates>>( parser.getCutouts() );
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.layered.MapLayer#paintLayer(be.kuleuven.cs.gridlock.gui.map.PixelMapper, java.awt.Graphics2D)
     */
    @Override
    public void paintLayer( PixelMapper mapper, Graphics2D graphics) {
        graphics.setStroke( new BasicStroke( 2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND ) );
        graphics.setColor( shapeColor );
        for( List<Coordinates> coordinates : shapes ) {
            int[][] points = mapper.mapCoordinates( coordinates );
            // graphics.fillPolygon( points[0], points[1], points[0].length );
            graphics.drawPolyline( points[0], points[1], points[0].length );
        }

        graphics.setColor( cutoutColor );
        for( List<Coordinates> coordinates : cutouts ) {
            int[][] points = mapper.mapCoordinates( coordinates );
            graphics.drawPolyline( points[0], points[1], points[0].length );
        }
    }
}