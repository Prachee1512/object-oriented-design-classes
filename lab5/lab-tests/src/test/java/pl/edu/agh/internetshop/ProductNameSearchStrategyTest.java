package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ProductNameSearchStrategyTest {
    private Order getOrderContainsProductName(String name) {
        Product p = mock(Product.class);
        given(p.getName()).willReturn(name);
        return new Order(Collections.singletonList(p));
    }

    @Test
    void testFiltering() {
        // given
        ProductNameSearchStrategy searchStrategy = new ProductNameSearchStrategy("Mleko");

        // when
        Order order1 = getOrderContainsProductName("Mleko");
        Order order2 = getOrderContainsProductName("Nie-Mleko");

        //then
        assertTrue(searchStrategy.filter(order1));
        assertFalse(searchStrategy.filter(order2));
    }
}
