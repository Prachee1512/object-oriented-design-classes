package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

public class OrderTest {

	private Order getOrderWithMockedProduct() {
		Product product = mock(Product.class);
		return new Order(Collections.singletonList(product));
	}

	@Test
	public void testGetProductThroughOrder() {
		// given
		Product expectedProduct = mock(Product.class);
		Product expectedProduct2 = mock(Product.class);
		Order order = new Order(Arrays.asList(expectedProduct, expectedProduct2));

		// when
		Product actualProduct = order.getProducts().get(0);
		Product actualProduct2 = order.getProducts().get(1);

		// then
		assertSame(expectedProduct, actualProduct);
		assertSame(expectedProduct2, actualProduct2);
	}

	@Test
	public void testCreateOrderWithNullList(){
		// when
		// given
		assertThrows(NullPointerException.class, () -> new Order(null));
	}

	@Test
	public void testSetShipment() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();
		Shipment expectedShipment = mock(Shipment.class);

		// when
		order.setShipment(expectedShipment);

		// then
		assertSame(expectedShipment, order.getShipment());
	}

	@Test
	public void testShipmentWithoutSetting() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertNull(order.getShipment());
	}

	@Test
	public void testGetPrice() throws Exception {
		// given
		BigDecimal expectedTotalPrice = BigDecimal.valueOf(1666);
		BigDecimal expectedProductPrice = BigDecimal.valueOf(1000);
		BigDecimal expectedProductPrice2 = BigDecimal.valueOf(666);
		Product product = mock(Product.class);
		Product product2 = mock(Product.class);
		given(product.getPrice()).willReturn(expectedProductPrice);
		given(product2.getPrice()).willReturn(expectedProductPrice2);
		Order order = new Order(Arrays.asList(product, product2));

		// when
		BigDecimal actualTotalPrice = order.getPrice();

		// then
		assertBigDecimalCompareValue(expectedTotalPrice, actualTotalPrice);
	}

	@Test
	public void testGetTotalPriceWithDiscount() throws Exception {
		// given
		BigDecimal expectedTotalPrice = BigDecimal.valueOf(1499.4);
		BigDecimal expectedProductPrice = BigDecimal.valueOf(1000);
		BigDecimal expectedProductPrice2 = BigDecimal.valueOf(666);
		BigDecimal discountTotal = BigDecimal.valueOf(0.1);
		Product product = mock(Product.class);
		Product product2 = mock(Product.class);
		given(product.getPrice()).willReturn(expectedProductPrice);
		given(product2.getPrice()).willReturn(expectedProductPrice2);
		Order order = new Order(Arrays.asList(product, product2), discountTotal);

		// when
		BigDecimal actualTotalPrice = order.getPrice();

		// then
		assertBigDecimalCompareValue(expectedTotalPrice, actualTotalPrice);
	}

	@Test
	public void testInvalidDiscount() throws IllegalArgumentException{
		// when
		// given
		assertThrows(IllegalArgumentException.class,
				() -> new Product("asdf", BigDecimal.valueOf(1), BigDecimal.valueOf(-1)));

		assertThrows(IllegalArgumentException.class,
				() -> new Product("asdf2", BigDecimal.valueOf(1), BigDecimal.valueOf(2133)));
	}


	private Order getOrderWithCertainProductPrice(double productPriceValue) {
		BigDecimal productPrice = BigDecimal.valueOf(productPriceValue);
		Product product = mock(Product.class);
		given(product.getPrice()).willReturn(productPrice);
		return new Order(Collections.singletonList(product));
	}

	@Test
	public void testPriceWithTaxesWithoutRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(2); // 2 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(2.46)); // 2.44 PLN
	}

	@Test
	public void testPriceWithTaxesWithRoundDown() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(0.01); // 0.01 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.01)); // 0.01 PLN
																							
	}

	@Test
	public void testPriceWithTaxesWithRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(0.03); // 0.03 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.04)); // 0.04 PLN
	}

	@Test
	public void testSetShipmentMethod() {
		// given
		Order order = getOrderWithMockedProduct();
		ShipmentMethod surface = mock(SurfaceMailBus.class);

		// when
		order.setShipmentMethod(surface);

		// then
		assertSame(surface, order.getShipmentMethod());
	}

	@Test
	public void testSending() {
		// given
		Order order = getOrderWithMockedProduct();
		SurfaceMailBus surface = mock(SurfaceMailBus.class);
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when
		order.setShipmentMethod(surface);
		order.setShipment(shipment);
		order.send();

		// then
		assertTrue(order.isSent());
	}

	@Test
	public void testIsSentWithoutSending() {
		// given
		Order order = getOrderWithMockedProduct();
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when

		// then
		assertFalse(order.isSent());
	}

	@Test
	public void testWhetherIdExists() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertNotNull(order.getId());
	}

	@Test
	public void testSetPaymentMethod() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);

		// when
		order.setPaymentMethod(paymentMethod);

		// then
		assertSame(paymentMethod, order.getPaymentMethod());
	}

	@Test
	public void testPaying() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);
		given(paymentMethod.commit(any(MoneyTransfer.class))).willReturn(true);
		MoneyTransfer moneyTransfer = mock(MoneyTransfer.class);
		given(moneyTransfer.isCommitted()).willReturn(true);

		// when
		order.setPaymentMethod(paymentMethod);
		order.pay(moneyTransfer);

		// then
		assertTrue(order.isPaid());
	}

	@Test
	public void testIsPaidWithoutPaying() throws Exception {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertFalse(order.isPaid());
	}
}
