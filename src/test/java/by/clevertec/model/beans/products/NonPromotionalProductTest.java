package by.clevertec.model.beans.products;

import by.clevertec.model.action.Actions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NonPromotionalProductTest {
   static double discountPercent=5;


    AbstractProducts product1;
    AbstractProducts product2;
    AbstractProducts product3;

    @BeforeEach
    void prepareData(){
        product1 =new NonPromotionalProduct("Milk",15,10.25);
        product2 =new NonPromotionalProduct("Cheese",13);
        product3 =new NonPromotionalProduct();
        NonPromotionalProduct.setDiscountPercent(discountPercent);
    }


    @ParameterizedTest
    @ValueSource(doubles = {26})
    void setDiscountPercent(double number) {
        discountPercent=number;
        NonPromotionalProduct.setDiscountPercent(number);
        assertEquals(discountPercent,NonPromotionalProduct.getDiscountPercent());
    }


    @Test
    void getDiscountPercent(){
        assertEquals(discountPercent,NonPromotionalProduct.getDiscountPercent());
    }


    @Test
    void cost() {
        double cost=product1.getPriceWithoutDiscount()*(1-discountPercent/(double)100);
        double scale = Math.pow(10, 2);
        double result = Actions.roundingNumber(cost);
        assertEquals(result,product1.cost());
    }


    @Test
    void getDiscountAmount() {
        double discount=product1.getPriceWithoutDiscount()*discountPercent/100;
        System.out.println(discountPercent);
        double x=product1.getDiscountAmount();
        System.out.println(discount);
        System.out.println(x);
        assertEquals(discount,product1.getDiscountAmount());
    }
}