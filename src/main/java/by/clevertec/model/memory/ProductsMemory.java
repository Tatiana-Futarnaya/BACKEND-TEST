package by.clevertec.model.memory;

import by.clevertec.model.beans.products.AbstractProducts;
import by.clevertec.model.beans.products.NonPromotionalProduct;
import by.clevertec.model.beans.products.PromotionalProduct;
import by.clevertec.model.exceptions.IdProductNotFoundException;


import java.util.HashMap;
import java.util.Map;

public class ProductsMemory {
    private static Map<Integer, AbstractProducts> productsMap = new HashMap<>();

    static {
        productsMap.put(1,new PromotionalProduct("Milk",10));
        productsMap.put(2,new NonPromotionalProduct("Meat",5.0));
        productsMap.put(3,new PromotionalProduct("Cheese",3.0));
        productsMap.put(4,new NonPromotionalProduct("Cookie",4.68));
        productsMap.put(5,new PromotionalProduct("Flour",8.0));
        productsMap.put(6,new NonPromotionalProduct("Juice",7.0));
    }

    public static AbstractProducts getById(int id) throws IdProductNotFoundException {
        AbstractProducts products;
        if(id>0 && id<=productsMap.size()){
            products=productsMap.get(id);
        }else{
            throw new IdProductNotFoundException("Id product not found: ",id);
        }
        return products;
    }
}
