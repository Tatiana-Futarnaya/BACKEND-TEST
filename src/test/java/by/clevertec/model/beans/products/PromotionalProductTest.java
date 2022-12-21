package by.clevertec.model.beans.products;

import by.clevertec.model.action.Actions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PromotionalProductTest {
    AbstractProducts product1;
    AbstractProducts product2;
    AbstractProducts product3;

     static final double DISCOUNT=10;
    static final double AMOUNT=5;

    @BeforeEach
    void prepareData(){
        product1 =new PromotionalProduct("Milk",15,10.25);
        product2 =new PromotionalProduct("Cheese",13);
        product3 =new PromotionalProduct();

    }
    @Test
    void cost() {
        double result=0;
        double cost=0.0;
        if(product1.getAmount()>=AMOUNT){
            result = product1.getPriceWithoutDiscount()*(1-DISCOUNT/100);
            cost = Actions.roundingNumber(result);
            assertEquals(cost,product1.cost());
        }else {
            result = product1.getPriceWithoutDiscount();
        }
        cost = Actions.roundingNumber(result);
        assertEquals(cost,product1.cost());
    }

    @Test
    void costWithoutDiscount() {
        double result=0;
        product1.setAmount(1);
        if(product1.getAmount()>=AMOUNT){
            result = product1.getPriceWithoutDiscount()*(1-DISCOUNT/100);
        }else {
            result = product1.getPriceWithoutDiscount();
        }
        double scale = Math.pow(10, 2);
        double cost = Math.ceil(result * scale) / scale;
        assertEquals(cost,product1.cost());
    }


    @Test
    void getDiscountAmount() {
        double result=0;
        if(product1.getAmount()>=AMOUNT){
            result=product1.getPriceWithoutDiscount()*DISCOUNT/100;
        }
        assertEquals(result,product1.getDiscountAmount());
    }
}