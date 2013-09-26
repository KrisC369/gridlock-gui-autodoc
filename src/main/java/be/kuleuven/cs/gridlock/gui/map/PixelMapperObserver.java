package be.kuleuven.cs.gridlock.gui.map;

/**
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public interface PixelMapperObserver {

    public void notifyOfChange( PixelMapper mapper );
    
}
