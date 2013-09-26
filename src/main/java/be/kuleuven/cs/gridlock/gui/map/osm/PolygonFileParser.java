/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.osm;

import be.kuleuven.cs.gridlock.geo.coordinates.Coordinates;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class PolygonFileParser.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class PolygonFileParser {

    private final BufferedReader reader;

    private String currentLabel;
    private boolean cutout = false;

    private List<Coordinates> coordinates;

    private final Map<String,List<Coordinates>> shapes;
    private final Map<String,List<Coordinates>> cutouts;

    /**
     * Instantiates a new polygon file parser.
     * 
     * @param input
     *          the input
     * @throws IOException
     *           Signals that an I/O exception has occurred.
     */
    public PolygonFileParser( InputStream input ) throws IOException {
        this.shapes = new HashMap<String,List<Coordinates>>();
        this.cutouts = new HashMap<String,List<Coordinates>>();
        this.reader = new BufferedReader( new InputStreamReader( input ) );
        this.parse();
    }

    private void parse() throws IOException {
        reader.readLine();
        String line;


        while( ( line = reader.readLine() ) != null ) {
            if( currentLabel == null ) {
                currentLabel = line.trim();
                cutout = currentLabel.startsWith( "!" );
                coordinates = new LinkedList<Coordinates>();
            }
            else if( line.trim().toLowerCase().equals( "end" ) ) {
                Map<String,List<Coordinates>> set = cutout ? cutouts : shapes;
                set.put( currentLabel, coordinates );
                currentLabel = null;
            }
            else {
                String[] pieces = line.trim().split( "\\s+", 2 );
                double longitude = Double.parseDouble( pieces[0] );
                double latitude = Double.parseDouble( pieces[1] );
                coordinates.add( Coordinates.coordinatesAt( latitude, longitude ) );
            }
        }
    }

    /**
     * Gets the shapes.
     * 
     * @return the shapes
     */
    public Collection<List<Coordinates>> getShapes() {
        return this.shapes.values();
    }

    /**
     * Gets the cutouts.
     * 
     * @return the cutouts
     */
    public Collection<List<Coordinates>> getCutouts() {
        return this.cutouts.values();
    }
}
