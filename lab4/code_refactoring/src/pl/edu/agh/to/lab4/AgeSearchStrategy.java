package pl.edu.agh.to.lab4;

public class AgeSearchStrategy implements SearchStrategy {
    private final int age;

    public AgeSearchStrategy(int age) {
        this.age = age;
    }

    @Override
    public boolean filter(Suspect s) {
        boolean isCitizen = (s instanceof CracowCitizen);
        return !isCitizen || s.getAge() >= age;
    }
}
