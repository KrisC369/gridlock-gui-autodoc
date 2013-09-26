/*
 * 
 */
package be.kuleuven.cs.gridlock.gui.map.utils;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections.buffer.CircularFifoBuffer;

// TODO: Auto-generated Javadoc
/**
 * A generic wrapper arround a non generic circular fifo buffer.
 * 
 * @param <ElementType>
 *          the generic type
 * @author Kristof Coninx <kristof.coninx at student.kuleuven.be>
 */
public class GenericCircularFifoBuffer<ElementType> implements Collection<ElementType> {

    private CircularFifoBuffer storageArea;

    /**
     * Instantiates a new generic circular fifo buffer.
     * 
     * @param maxSize
     *          the max size
     */
    public GenericCircularFifoBuffer(final int maxSize) {
        if (maxSize > 0) {
            storageArea = new CircularFifoBuffer(maxSize);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* (non-Javadoc)
     * @see java.util.Collection#size()
     */
    @Override
    public int size() {
        return storageArea.size();
    }

    /* (non-Javadoc)
     * @see java.util.Collection#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return storageArea.isEmpty();
    }

    /* (non-Javadoc)
     * @see java.util.Collection#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object o) {
        return storageArea.contains(o);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#iterator()
     */
    @Override
    public Iterator<ElementType> iterator() {
        return new Iterator<ElementType>() {

            private Iterator tmp = storageArea.iterator();

            @Override
            public boolean hasNext() {
                return tmp.hasNext();
            }

            @Override
            public ElementType next() {
                return (ElementType) tmp.next();
            }

            @Override
            public void remove() {
                tmp.remove();
            }
        };
    }

    /* (non-Javadoc)
     * @see java.util.Collection#toArray()
     */
    @Override
    public Object[] toArray() {
        return storageArea.toArray();
    }

    /* (non-Javadoc)
     * @see java.util.Collection#toArray(java.lang.Object[])
     */
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /* (non-Javadoc)
     * @see java.util.Collection#add(java.lang.Object)
     */
    @Override
    public boolean add(ElementType e) {
        return storageArea.add(e);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#remove(java.lang.Object)
     */
    @Override
    public boolean remove(Object o) {
        return storageArea.remove(o);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
       return storageArea.containsAll(c);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    @Override
    public boolean addAll(Collection<? extends ElementType> c) {
        return storageArea.addAll(c);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return storageArea.removeAll(c);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#retainAll(java.util.Collection)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return storageArea.retainAll(c);
    }

    /* (non-Javadoc)
     * @see java.util.Collection#clear()
     */
    @Override
    public void clear() {
        storageArea.clear();
    }
}
