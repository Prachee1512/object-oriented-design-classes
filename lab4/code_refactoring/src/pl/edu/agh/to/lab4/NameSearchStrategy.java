package pl.edu.agh.to.lab4;

public class NameSearchStrategy implements SearchStrategy{
    private final String name;

    public NameSearchStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean filter(Suspect s) {
        return s.getFirstName().equals(name);
    }
}
