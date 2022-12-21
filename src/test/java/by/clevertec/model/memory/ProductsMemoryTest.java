package by.clevertec.model.memory;

import by.clevertec.model.beans.products.AbstractProducts;
import by.clevertec.model.beans.products.NonPromotionalProduct;
import by.clevertec.model.beans.products.PromotionalProduct;
import by.clevertec.model.exceptions.IdProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductsMemoryTest {
    static Map<Integer, AbstractProducts> productsMap = new HashMap<>();

    @BeforeEach
    void prepareData() {
        productsMap.put(1,new PromotionalProduct("Milk",10));
        productsMap.put(2,new NonPromotionalProduct("Meat",5.0));
        productsMap.put(3,new PromotionalProduct("Cheese",3.0));
        productsMap.put(4,new NonPromotionalProduct("Cookie",4.68));
        productsMap.put(5,new PromotionalProduct("Flour",8.0));
        productsMap.put(6,new NonPromotionalProduct("Juice",7.0));
    }


    @ParameterizedTest
    @ValueSource(ints = {1,6})
    void getById(int id) throws IdProductNotFoundException {
        AbstractProducts products;
        if(id>0 && id<=productsMap.size()){
            products=productsMap.get(id);
        }
        assertNotNull(ProductsMemory.getById(id));
    }

    @ParameterizedTest
    @ValueSource(ints = {0,15,-2})
    void getByIdException(int id) {
        assertThrows(Exception.class,()->{
           ProductsMemory.getById(id);
        });
    }
}