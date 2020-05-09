package pl.edu.agh.to.lab4;

import java.util.Calendar;

public class Prisoner extends Suspect{
    private final int judgementYear;
    private final int senteceDuration;
    private final String pesel;

    public Prisoner(String name, String surname, String pesel, int judgementYear, int sentenceDuration) {
        super(name, surname);
        this.pesel = pesel;
        this.judgementYear = judgementYear;
        this.senteceDuration = sentenceDuration;
    }

    @Override
    public boolean canBeAccussed(String n) {
        return super.canBeAccussed(n) && !isJailedNow();
    }

    private int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public boolean isJailedNow() {
        return judgementYear + senteceDuration >= getCurrentYear();
    }

    public int getJudgementYear() {
        return judgementYear;
    }

    public int getSenteceDuration() {
        return senteceDuration;
    }

    public String getPesel() {
        return pesel;
    }
}
