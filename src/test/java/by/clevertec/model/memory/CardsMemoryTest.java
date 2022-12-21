package by.clevertec.model.memory;

import by.clevertec.model.beans.cards.Cards;
import by.clevertec.model.beans.products.AbstractProducts;
import by.clevertec.model.beans.products.NonPromotionalProduct;
import by.clevertec.model.beans.products.PromotionalProduct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CardsMemoryTest {

    static Map<String, Cards> cardsMap = new HashMap<>();

    @BeforeEach
    void prepareData() {
            cardsMap.put("card-1234", new Cards("1234", 2));
            cardsMap.put("card-1235", new Cards("1235", 3));
            cardsMap.put("card-1236", new Cards("1236", 5));
            cardsMap.put(null, new Cards(null, 0));
        }

        @ParameterizedTest
        @ValueSource(strings = {"card-1234","","sdgdh"})
        void getById (String id){
            for (Map.Entry<String, Cards> item : cardsMap.entrySet()) {
                if (id != item.getKey()) {
                    id = null;
                }
            }
            assertNotNull(CardsMemory.getById(id));
        }
    }

