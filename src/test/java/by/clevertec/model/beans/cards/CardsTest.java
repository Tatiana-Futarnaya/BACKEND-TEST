package by.clevertec.model.beans.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardsTest {
    Cards cards1;
    Cards cards2;

    @BeforeEach
    void prepareData(){
        cards1 =new Cards("1234",2);
        cards2 =new Cards();

    }

    @Test
    void getNumber() {
        assertEquals("1234", cards1.getNumber());
    }

    @Test
    void setNumber() {
        cards1.setNumber("2589");
        assertEquals("2589", cards1.getNumber());
    }

    @Test
    void getDiscount() {
        assertEquals(2, cards1.getDiscount());
    }

    @Test
    void setDiscount() {
        cards1.setDiscount(5);
        assertEquals(5, cards1.getDiscount());
    }
}