package pl.edu.agh.internetshop;

import java.util.LinkedList;
import java.util.List;

public class CompositeSearchStrategy implements SearchStrategy {
    List<SearchStrategy> searchStrategies;

    public CompositeSearchStrategy() {
        this.searchStrategies = new LinkedList<>();
    }

    public void addStrategy(SearchStrategy strategy){
        this.searchStrategies.add(strategy);
    }

    @Override
    public boolean filter(Order o) {
        return searchStrategies
                .stream()
                .allMatch(searchStrategy -> searchStrategy.filter(o));
    }
}