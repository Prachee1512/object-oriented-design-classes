package pl.edu.agh.to.lab4;

public abstract class Suspect {
    private final String firstName;
    private final String surname;
    private int age;

    public Suspect(String f, String l, int age){
        this.firstName = f;
        this.surname = l;
        this.age = age;
    }

    public Suspect(String f, String l){
        this(f, l, 0);
    }

    public boolean canBeAccussed(String n){
        return this.firstName.equals(n);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String display(){
        return firstName + " " + surname;
    }

    public boolean isAdult(){
        return getAge() >= 18;
    }

    @Override
    public String toString() {
        return display();
    }
}
