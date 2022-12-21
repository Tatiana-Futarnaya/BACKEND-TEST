package by.clevertec.model.beans.products;

import by.clevertec.model.action.Actions;

public class PromotionalProduct extends AbstractProducts {
    private final static double DISCOUNT=10;
    private final static double AMOUNT=5;

    public PromotionalProduct() {
    }

    public PromotionalProduct(String name,double price) {
        super(name,price);
    }

    public PromotionalProduct(String name, int amount, double price) {
        super(name, amount, price);
    }



    public double cost() {
        double result=0;

        if(getAmount()>=AMOUNT){
            result = getPriceWithoutDiscount()*(1-DISCOUNT/100);
        }else {
            result = getPriceWithoutDiscount();
        }
        return Actions.roundingNumber(result);
    }

    @Override
    public double getDiscountAmount() {
        double result=0;
        if(getAmount()>=AMOUNT){
            result=getPriceWithoutDiscount()*DISCOUNT/100;
        }
        return result;
    }


}
