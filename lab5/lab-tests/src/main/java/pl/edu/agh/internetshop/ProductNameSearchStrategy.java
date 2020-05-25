package pl.edu.agh.internetshop;

public class ProductNameSearchStrategy implements SearchStrategy{
    private final String productName;

    public ProductNameSearchStrategy(String name){
        this.productName = name;
    }

    @Override
    public boolean filter(Order order) {
        return order.getProducts()
                .stream()
                .anyMatch(product -> product.getName().equals(productName));
    }
}
