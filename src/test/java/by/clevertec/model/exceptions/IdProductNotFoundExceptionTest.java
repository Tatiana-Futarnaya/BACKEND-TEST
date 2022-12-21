package by.clevertec.model.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdProductNotFoundExceptionTest {
    IdProductNotFoundException exp;

    @BeforeEach
    void prepareData(){
        exp=new IdProductNotFoundException("message",5);
    }

    @Test
    void getId() {
        assertEquals(5,exp.getId());
    }
}