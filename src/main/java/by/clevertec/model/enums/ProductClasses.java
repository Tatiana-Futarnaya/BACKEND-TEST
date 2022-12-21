package by.clevertec.model.enums;

import by.clevertec.model.beans.products.AbstractProducts;

public enum ProductClasses {
    PromotionalProduct("by.clevertec.model.beans.products.PromotionalProduct"),
    NonPromotionalProduct("by.clevertec.model.beans.products.NonPromotionalProduct");

    private String value;

    ProductClasses(String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }


}
