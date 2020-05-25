package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CompositeSearchStrategyTest {
    private Order getOrderWithParams(String customerName, String productName, BigDecimal price){
        Order order = mock(Order.class);
        Product p = mock(Product.class);
        Address a = mock(Address.class);
        Shipment s = mock(Shipment.class);

        given(a.getName()).willReturn(customerName);
        given(s.getRecipientAddress()).willReturn(a);

        given(p.getName()).willReturn(productName);

        given(order.getShipment()).willReturn(s);
        given(order.getPriceWithTaxes()).willReturn(price);
        given(order.getProducts()).willReturn(Collections.singletonList(p));
        return order;

    }

    @Test
    public void testFiltering(){
        // given
        BigDecimal min = BigDecimal.valueOf(2);
        BigDecimal max = BigDecimal.valueOf(10);
        CustomerNameSearchStrategy searchStrategy1 = new CustomerNameSearchStrategy("Franek");
        ProductNameSearchStrategy searchStrategy2 = new ProductNameSearchStrategy("Mleko");
        PriceSearchStrategy searchStrategy3 = new PriceSearchStrategy(min, max);
        CompositeSearchStrategy compositeSearchStrategy = new CompositeSearchStrategy();
        compositeSearchStrategy.addStrategy(searchStrategy1);
        compositeSearchStrategy.addStrategy(searchStrategy2);
        compositeSearchStrategy.addStrategy(searchStrategy3);

        // when
        Order order1 = getOrderWithParams("Franek", "Mleko", BigDecimal.valueOf(20));
        Order order2 = getOrderWithParams("Nie-Franek", "Ziemniaki", BigDecimal.valueOf(2));
        Order order3 = getOrderWithParams("Franek", "Muszyna", BigDecimal.valueOf(4));
        Order order4 = getOrderWithParams("Staszek", "Chleb", BigDecimal.valueOf(11));
        Order order5 = getOrderWithParams("Franek", "Mleko", BigDecimal.valueOf(5));

        //then
        assertFalse(compositeSearchStrategy.filter(order1));
        assertFalse(compositeSearchStrategy.filter(order2));
        assertFalse(compositeSearchStrategy.filter(order3));
        assertFalse(compositeSearchStrategy.filter(order4));
        assertTrue(compositeSearchStrategy.filter(order5));
    }
}
