package pl.edu.agh.to.lab4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Finder {
    private final CompositeAggregate compositeAggregate;

    public Finder(Collection<CracowCitizen> allCracovCitizens, Map<String, Collection<Prisoner>> allPrisoners) {
        SuspectAggregate p = () -> new FlatIterator<>(
                allPrisoners.values()
                        .stream()
                        .map(Collection::iterator)
                        .collect(Collectors.toList())
        );

        this.compositeAggregate = new CompositeAggregate(List.of(p, allCracovCitizens::iterator));
    }

    public Finder(PersonDataProvider personDataProvider, PrisonersDatabase prisonersDatabase) {
        this.compositeAggregate = new CompositeAggregate(List.of(personDataProvider, prisonersDatabase));
    }

    public void displayAllSuspectsWithName(String name) {
        var strategy = new CompositeSearchStrategy();
        strategy.addStrategy(new NameSearchStrategy(name));
        strategy.addStrategy(new AgeSearchStrategy(18));
        strategy.addStrategy(new NotInJailSearchStrategy());

        var res = new ArrayList<Suspect>();
        var iterator = compositeAggregate.iterator();
        while(iterator.hasNext()){
            Suspect s = iterator.next();
            if(strategy.filter(s)){
                res.add(s);
                if(res.size() > 10){
                    break;
                }
            }
        }

        System.out.println("Znalazlem " + res.size() + " pasujacych podejrzanych!");
        res.forEach(s -> System.out.println(s.display()));
    }
}
