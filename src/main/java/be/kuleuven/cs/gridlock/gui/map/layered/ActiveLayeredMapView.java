package be.kuleuven.cs.gridlock.gui.map.layered;

import be.kuleuven.cs.gridlock.gui.map.projection.Projection;
import be.kuleuven.cs.gridlock.simulation.SimulationComponent;
import be.kuleuven.cs.gridlock.simulation.SimulationContext;
import be.kuleuven.cs.gridlock.simulation.api.VirtualTime;
import be.kuleuven.cs.gridlock.simulation.timeframe.TimeFrameConsumer;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public class ActiveLayeredMapView extends LayeredMapView implements TimeFrameConsumer, SimulationComponent {

    private long refreshRate = 1000 * 60;

    private double waiting = refreshRate / 1000.0;

    private static final long serialVersionUID = 1L;

    public ActiveLayeredMapView( Projection projection, MapLayer... layers ) {
        super( projection, layers );
    }

    public ActiveLayeredMapView( Projection projection ) {
        super( projection );
    }

    @Override
    public void consume( VirtualTime currentTime, double timeFrameDuration ) {
        waiting += timeFrameDuration;
        if( waiting * 1000 >= refreshRate ) {
            this.repaint();
            waiting = 0;
        }
    }

    @Override
    public boolean continueSimulation() {
        return false;
    }

    @Override
    public Collection<? extends TimeFrameConsumer> getConsumers() {
        return Collections.singleton( this );
    }

    @Override
    public Collection<? extends SimulationComponent> getSubComponents() {
        return Collections.emptyList();
    }

    @Override
    public void initialize( SimulationContext simulationContext ) {
        // NO-OP
    }
}
