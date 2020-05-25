package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


public class CustomerNameSearchStrategyTest {
    private Order getOrderWithCustomerName(String name) {
        Address a = mock(Address.class);
        Shipment s = mock(Shipment.class);
        Order o = mock(Order.class);

        given(a.getName()).willReturn(name);
        given(s.getRecipientAddress()).willReturn(a);
        given(o.getShipment()).willReturn(s);

        return o;
    }

    @Test
    void testFiltering() {
        // given
        CustomerNameSearchStrategy searchStrategy = new CustomerNameSearchStrategy("Franek");

        // when
        Order order1 = getOrderWithCustomerName("Franek");
        Order order2 = getOrderWithCustomerName("Nie-Franek");

        //then
        assertTrue(searchStrategy.filter(order1));
        assertFalse(searchStrategy.filter(order2));
    }
}
