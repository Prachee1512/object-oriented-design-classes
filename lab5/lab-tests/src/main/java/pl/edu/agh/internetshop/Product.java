package pl.edu.agh.internetshop;

import java.math.BigDecimal;

public class Product {
	
	public static final int PRICE_PRECISION = 2;
	public static final int ROUND_STRATEGY = BigDecimal.ROUND_HALF_UP;
	private BigDecimal discount = BigDecimal.ZERO;
    private final String name;
    private final BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.price.setScale(PRICE_PRECISION, ROUND_STRATEGY);
    }

    public Product(String name, BigDecimal price, BigDecimal discount) {
        this(name, price);
        setDiscount(discount);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.multiply(BigDecimal.ONE.subtract(discount));
    }

    public BigDecimal getDiscount(){
        return discount;
    }

    public void setDiscount(BigDecimal d) throws IllegalArgumentException{
        if(d.compareTo(BigDecimal.ZERO) < 0 || d.compareTo(BigDecimal.ONE) > 0){
            throw new IllegalArgumentException("Invalid discount value");
        }

        this.discount = d;
        this.discount.setScale(PRICE_PRECISION, ROUND_STRATEGY);
    }
}
