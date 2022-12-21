package by.clevertec.model.enums;

import by.clevertec.model.beans.cards.Cards;
import by.clevertec.model.beans.checks.Checks;
import by.clevertec.model.beans.products.NonPromotionalProduct;
import by.clevertec.model.beans.products.PromotionalProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductClassesTest {

    @Test
    void getValue() {
        assertEquals("by.clevertec.model.beans.products.NonPromotionalProduct"
                ,ProductClasses.NonPromotionalProduct.getValue());
    }
}