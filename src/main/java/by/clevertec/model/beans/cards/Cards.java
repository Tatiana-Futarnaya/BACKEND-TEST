package by.clevertec.model.beans.cards;

import java.util.HashMap;
import java.util.Map;

public class Cards {
    private String number;
    private int discount;

    public Cards() {
    }

    public Cards(String number, int discount) {
        this.number = number;
        this.discount = discount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


}
