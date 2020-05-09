package pl.edu.agh.to.lab4;

public class CracowCitizen extends Suspect {
    public CracowCitizen(String firstname, String lastname, int age) {
        super(firstname, lastname, age);
    }

    @Override
    public boolean canBeAccussed(String n) {
        return super.canBeAccussed(n) && isAdult();
    }
}
