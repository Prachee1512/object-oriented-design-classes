package pl.edu.agh.internetshop;

public class CustomerNameSearchStrategy implements SearchStrategy{
    private final String name;

    public CustomerNameSearchStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean filter(Order o) {
        return o.getShipment().getRecipientAddress().getName().equals(name);
    }
}
