package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;


public class ProductTest {

	
    private static final String NAME = "Mr. Sparkle";
    private static final BigDecimal PRICE = BigDecimal.valueOf(1);

	@Test
    public void testProductName() throws Exception{
        //given
    	
        // when
        Product product = new Product(NAME, PRICE);
        
        // then
        assertEquals(NAME, product.getName());
    }
    
    @Test
    public void testProductPrice() throws Exception{
        //given
    	
        // when
        Product product = new Product(NAME, PRICE);
        
        // then
        assertBigDecimalCompareValue(product.getPrice(), PRICE);
    }

    @Test
    public void testGetProductPriceWithDiscount(){
        // given
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(900);
        BigDecimal expectedProductPrice = BigDecimal.valueOf(1000);
        BigDecimal discountTotal = BigDecimal.valueOf(0.1);
        Product product = new Product("asdf", expectedProductPrice, discountTotal);

        //when
        BigDecimal actualTotalPrice = product.getPrice();

        //then
        assertBigDecimalCompareValue(expectedTotalPrice, actualTotalPrice);
    }
}