package be.kuleuven.cs.gridlock.gui.map.utils;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections.buffer.CircularFifoBuffer;

/**
 * A generic wrapper arround a non generic circular fifo buffer.
 * @author Kristof Coninx <kristof.coninx at student.kuleuven.be>
 * @param <ElementType> 
 */
public class GenericCircularFifoBuffer<ElementType> implements Collection<ElementType> {

    private CircularFifoBuffer storageArea;

    public GenericCircularFifoBuffer(final int maxSize) {
        if (maxSize > 0) {
            storageArea = new CircularFifoBuffer(maxSize);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int size() {
        return storageArea.size();
    }

    @Override
    public boolean isEmpty() {
        return storageArea.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return storageArea.contains(o);
    }

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

    @Override
    public Object[] toArray() {
        return storageArea.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean add(ElementType e) {
        return storageArea.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return storageArea.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
       return storageArea.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends ElementType> c) {
        return storageArea.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return storageArea.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return storageArea.retainAll(c);
    }

    @Override
    public void clear() {
        storageArea.clear();
    }
}
