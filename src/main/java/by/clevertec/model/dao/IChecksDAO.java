package by.clevertec.model.dao;

import by.clevertec.model.beans.checks.Checks;
import by.clevertec.model.beans.products.AbstractProducts;
import by.clevertec.model.exceptions.IdProductNotFoundException;

import java.util.List;

public interface IChecksDAO {
    boolean isValidString(String[] args);
    Checks generateСheck(String[] args) throws IdProductNotFoundException,IllegalArgumentException;

}
