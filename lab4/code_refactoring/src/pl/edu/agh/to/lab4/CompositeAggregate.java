package pl.edu.agh.to.lab4;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeAggregate implements SuspectAggregate{
    private final List<SuspectAggregate> aggregateList;

    public CompositeAggregate(List<SuspectAggregate> aggregateList) {
        this.aggregateList = aggregateList;
    }

    @Override
    public Iterator<? extends Suspect> iterator() {
        return new FlatIterator<Suspect>(
                aggregateList.stream().map(a -> (Iterator<Suspect>) a.iterator()).collect(Collectors.toList())
        );
    }
}
