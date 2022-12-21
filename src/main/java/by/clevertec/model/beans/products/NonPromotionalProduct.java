package by.clevertec.model.beans.products;

import by.clevertec.model.action.Actions;

public class NonPromotionalProduct extends AbstractProducts {
    private static double discountPercent;

    public NonPromotionalProduct() {
    }

    public NonPromotionalProduct(String name, double price) {
        super(name,price);
    }

    public NonPromotionalProduct(String name, int amount, double price) {
        super(name,amount,price);
    }

    public static void setDiscountPercent(double discountPercent) {
        NonPromotionalProduct.discountPercent = discountPercent;
    }

    public static double getDiscountPercent() {
        return discountPercent;
    }


    @Override
    public double cost() {
        double result=getPriceWithoutDiscount()*(1-discountPercent/(double)100);
        return Actions.roundingNumber(result);
    }

    @Override
    public double getDiscountAmount() {
        return getPriceWithoutDiscount()*discountPercent/(double) 100;
    }


}
