/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.layered;

import be.kuleuven.cs.gridlock.gui.map.projection.Projection;
import be.kuleuven.cs.gridlock.simulation.SimulationComponent;
import be.kuleuven.cs.gridlock.simulation.SimulationContext;
import be.kuleuven.cs.gridlock.simulation.api.VirtualTime;
import be.kuleuven.cs.gridlock.simulation.timeframe.TimeFrameConsumer;
import java.util.Collection;
import java.util.Collections;

// TODO: Auto-generated Javadoc
/**
 * The Class ActiveLayeredMapView.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class ActiveLayeredMapView extends LayeredMapView implements TimeFrameConsumer, SimulationComponent {

    private long refreshRate = 1000 * 60;

    private double waiting = refreshRate / 1000.0;

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new active layered map view.
     * 
     * @param projection
     *          the projection
     * @param layers
     *          the layers
     */
    public ActiveLayeredMapView( Projection projection, MapLayer... layers ) {
        super( projection, layers );
    }

    /**
     * Instantiates a new active layered map view.
     * 
     * @param projection
     *          the projection
     */
    public ActiveLayeredMapView( Projection projection ) {
        super( projection );
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.simulation.timeframe.TimeFrameConsumer#consume(be.kuleuven.cs.gridlock.simulation.api.VirtualTime, double)
     */
    @Override
    public void consume( VirtualTime currentTime, double timeFrameDuration ) {
        waiting += timeFrameDuration;
        if( waiting * 1000 >= refreshRate ) {
            this.repaint();
            waiting = 0;
        }
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.simulation.timeframe.TimeFrameConsumer#continueSimulation()
     */
    @Override
    public boolean continueSimulation() {
        return false;
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.simulation.SimulationComponent#getConsumers()
     */
    @Override
    public Collection<? extends TimeFrameConsumer> getConsumers() {
        return Collections.singleton( this );
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.simulation.SimulationComponent#getSubComponents()
     */
    @Override
    public Collection<? extends SimulationComponent> getSubComponents() {
        return Collections.emptyList();
    }

    /* (non-Javadoc)
     * @see be.kuleuven.cs.gridlock.simulation.SimulationComponent#initialize(be.kuleuven.cs.gridlock.simulation.SimulationContext)
     */
    @Override
    public void initialize( SimulationContext simulationContext ) {
        // NO-OP
    }
}
