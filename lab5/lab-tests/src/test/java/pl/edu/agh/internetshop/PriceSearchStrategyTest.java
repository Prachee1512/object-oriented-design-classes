package pl.edu.agh.internetshop;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PriceSearchStrategyTest {
    private Order getOrderWithPrice(BigDecimal price) {
        Order order = mock(Order.class);
        given(order.getPriceWithTaxes()).willReturn(price);
        return order;
    }

    @Test
    void testFiltering() {
        // given
        BigDecimal min = BigDecimal.valueOf(2);
        BigDecimal max = BigDecimal.valueOf(10);
        PriceSearchStrategy searchStrategy = new PriceSearchStrategy(min, max);

        // when
        Order order1 = getOrderWithPrice(BigDecimal.valueOf(3));
        Order order2 = getOrderWithPrice(BigDecimal.valueOf(11));
        Order order3 = getOrderWithPrice(BigDecimal.valueOf(1));
        Order order4 = getOrderWithPrice(BigDecimal.valueOf(-1));
        Order order5 = getOrderWithPrice(BigDecimal.valueOf(0));
        Order order6 = getOrderWithPrice(min);
        Order order7 = getOrderWithPrice(max);

        //then
        assertTrue(searchStrategy.filter(order1));
        assertFalse(searchStrategy.filter(order2));
        assertFalse(searchStrategy.filter(order3));
        assertFalse(searchStrategy.filter(order4));
        assertFalse(searchStrategy.filter(order5));
        assertTrue(searchStrategy.filter(order6));
        assertTrue(searchStrategy.filter(order7));
    }
}
