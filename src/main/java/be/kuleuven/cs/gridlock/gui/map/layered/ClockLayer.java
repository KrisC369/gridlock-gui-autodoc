/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.layered;

import java.awt.Color;
import java.awt.Graphics2D;

import be.kuleuven.cs.gridlock.gui.map.PixelMapper;
import be.kuleuven.cs.gridlock.simulation.SimulationContext;
import be.kuleuven.cs.gridlock.simulation.TimeFrameSimulation;

// TODO: Auto-generated Javadoc
/**
 * The Class ClockLayer.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class ClockLayer implements MapLayer {

    private final TimeFrameSimulation simulation;

    /**
     * Instantiates a new clock layer.
     * 
     * @param context
     *          the context
     */
    public ClockLayer( SimulationContext context ) {
        this( context.getSimulation() );
    }

    /**
     * Instantiates a new clock layer.
     * 
     * @param simulation
     *          the simulation
     */
    public ClockLayer( TimeFrameSimulation simulation ) {
        this.simulation = simulation;
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.gui.map.layered.MapLayer#paintLayer(be.kuleuven.cs.gridlock.gui.map.PixelMapper, java.awt.Graphics2D)
     */
    @Override
    public void paintLayer( PixelMapper mapper, Graphics2D graphics) {
        String time = simulation.getCurrentVirtualTime().toString();
        graphics.setColor( Color.WHITE );
        graphics.drawChars( time.toCharArray(), 0, time.length(), 20, 20 );
    }

}
