package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class OrderHistoryTest {
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
    public void testHistoryList(){
        // given
        Order o1 = mock(Order.class);
        Order o2 = mock(Order.class);
        Order o3 = mock(Order.class);
        List<Order> expectedOrders = Arrays.asList(o1, o2, o3);
        OrderHistory orderHistory = new OrderHistory();

        // when
        orderHistory.addOrder(o1);
        orderHistory.addOrder(o2);
        orderHistory.addOrder(o3);

        //then
        assertEquals(expectedOrders, orderHistory.getOrders());
    }

    @Test
    public void testSearch() {
        BigDecimal min = BigDecimal.valueOf(2);
        BigDecimal max = BigDecimal.valueOf(10);
        CustomerNameSearchStrategy searchStrategy1 = new CustomerNameSearchStrategy("Franek");
        ProductNameSearchStrategy searchStrategy2 = new ProductNameSearchStrategy("Mleko");
        PriceSearchStrategy searchStrategy3 = new PriceSearchStrategy(min, max);
        CompositeSearchStrategy compositeSearchStrategy = new CompositeSearchStrategy();
        compositeSearchStrategy.addStrategy(searchStrategy1);
        compositeSearchStrategy.addStrategy(searchStrategy2);
        compositeSearchStrategy.addStrategy(searchStrategy3);
        Order order1 = getOrderWithParams("Franek", "Mleko", BigDecimal.valueOf(20));
        Order order2 = getOrderWithParams("Nie-Franek", "Ziemniaki", BigDecimal.valueOf(2));
        Order order3 = getOrderWithParams("Franek", "Muszyna", BigDecimal.valueOf(4));
        Order order4 = getOrderWithParams("Staszek", "Chleb", BigDecimal.valueOf(11));
        Order order5 = getOrderWithParams("Franek", "Mleko", BigDecimal.valueOf(5));
        List<Order> expectedOrders = Collections.singletonList(order5);
        OrderHistory orderHistory = new OrderHistory();

        // when
        orderHistory.addOrder(order1);
        orderHistory.addOrder(order2);
        orderHistory.addOrder(order3);
        orderHistory.addOrder(order4);
        orderHistory.addOrder(order5);

        //then
        assertEquals(expectedOrders, orderHistory.search(compositeSearchStrategy));
    }
}
