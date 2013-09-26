/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map;

// TODO: Auto-generated Javadoc
/**
 * An asynchronous update interface for receiving notifications about
 * PixelMapper information as the PixelMapper is constructed.
 * 
 * @author Rutger Claes <rutger.claes@cs.kuleuven.be>
 */
public interface PixelMapperObserver {

    /**
     * This method is called when information about an PixelMapper which was
     * previously requested using an asynchronous interface becomes available.
     * 
     * @param mapper
     *          the mapper
     */
    public void notifyOfChange( PixelMapper mapper );
    
}
