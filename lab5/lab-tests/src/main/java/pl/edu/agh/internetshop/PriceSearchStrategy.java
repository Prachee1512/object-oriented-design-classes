package pl.edu.agh.internetshop;

import java.math.BigDecimal;

public class PriceSearchStrategy implements SearchStrategy {
    private final BigDecimal min;
    private final BigDecimal max;

    public PriceSearchStrategy(BigDecimal min, BigDecimal max){
        this.max = max;
        this.min = min;
    }

    @Override
    public boolean filter(Order order) {
        return order.getPriceWithTaxes().compareTo(min) >= 0 &&
                order.getPriceWithTaxes().compareTo(max) <= 0;
    }
}
