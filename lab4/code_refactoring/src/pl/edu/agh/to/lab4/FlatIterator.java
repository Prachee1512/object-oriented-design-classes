package pl.edu.agh.to.lab4;

import java.util.Collection;
import java.util.Iterator;

public class FlatIterator<Type> implements Iterator<Type> {
    private final Iterator<Iterator<Type>> suspectIterators;
    private Iterator<Type> currentIterator;

    public FlatIterator(Collection<Iterator<Type>> suspectCollection) {
        this.suspectIterators = suspectCollection.iterator();
        if(!suspectCollection.isEmpty()){
            this.currentIterator = suspectIterators.next();
        }
    }

    @Override
    public boolean hasNext() {
        if(currentIterator == null){
            return false;
        }

        if(currentIterator.hasNext()){
            return true;
        }

        if(suspectIterators.hasNext()){
            return (currentIterator = suspectIterators.next()).hasNext();
        }

        return false;
    }

    @Override
    public Type next() {
        return currentIterator.next();
    }
}
