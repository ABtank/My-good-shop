package ru.abtank.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.abtank.representations.ProductRepr;
import ru.abtank.servises.CartService;
import ru.abtank.servises.CartServiceImpl;
import ru.abtank.servises.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init(){
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart(){
        Assertions.assertEquals(0,cartService.getLineItems().size()); // карзина пуста
        Assertions.assertEquals(BigDecimal.ZERO, cartService.getSubTotal()); // сумма в карзине = 0
    }

    @Test
    public void testAddOneProduct(){
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));

        cartService.addProductQty(expectedProduct,1);

        List<LineItem> lineItems = cartService.getLineItems();
        Assertions.assertNotNull(lineItems);
        Assertions.assertEquals(1,lineItems.size());
        LineItem lineItem = lineItems.get(0);
        Assertions.assertEquals(1, lineItem.getQty());
        Assertions.assertEquals(expectedProduct.getId(), lineItem.getProductId());
        Assertions.assertNotNull(lineItem.getProductRepr());
        Assertions.assertEquals(expectedProduct.getName(),lineItem.getProductRepr().getName());

    }
}
