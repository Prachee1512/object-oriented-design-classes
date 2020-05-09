package pl.edu.agh.to.lab4;

public class NotInJailSearchStrategy implements SearchStrategy {
    @Override
    public boolean filter(Suspect s) {
        boolean isPrisoner = (s instanceof Prisoner);
        return !isPrisoner || !((Prisoner) s).isJailedNow();
    }
}
