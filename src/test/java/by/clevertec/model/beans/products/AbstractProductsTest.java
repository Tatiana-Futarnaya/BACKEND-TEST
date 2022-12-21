package by.clevertec.model.beans.products;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AbstractProductsTest {
    AbstractProducts product1;
    AbstractProducts product2;
    AbstractProducts product3;
    AbstractProducts product4;
    AbstractProducts product5;
    AbstractProducts product6;

    @BeforeEach
    void prepareData(){
        product1 =new NonPromotionalProduct("Milk",15,10.25);
        product2 =new NonPromotionalProduct("Cheese",13);
        product3 =new NonPromotionalProduct();
        product4 =new PromotionalProduct("Milk",15,10.25);
        product5 =new PromotionalProduct("Cheese",13);
        product4 =new PromotionalProduct();
    }

    @Test
    void getName() {
        assertEquals("Milk", product1.getName());

    }


    @Test
    void getAmount() {
        assertEquals(15, product1.getAmount());
    }


    @ParameterizedTest
    @ValueSource(ints = {10})
    void setAmount(int amount) {
        product1.setAmount(amount);
        assertEquals(amount, product1.getAmount());
    }

    @Test
    void getPrice() {
        assertEquals(10.25, product1.getPrice());
    }

    @ParameterizedTest
    @ValueSource(doubles = {25.30})
    void setPrice(double price) {
        product1.setPrice(price);
        assertEquals(price, product1.getPrice());
    }

    @Test
    void getPriceWithoutDiscount() {
        double x= product1.getAmount()* product1.getPrice();
        assertEquals(x, product1.getPriceWithoutDiscount());
    }
}