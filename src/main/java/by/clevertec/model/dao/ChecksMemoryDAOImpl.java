package by.clevertec.model.dao;

import by.clevertec.model.beans.cards.Cards;
import by.clevertec.model.beans.checks.Checks;
import by.clevertec.model.beans.products.AbstractProducts;
import by.clevertec.model.enums.ProductClasses;
import by.clevertec.model.exceptions.IdProductNotFoundException;
import by.clevertec.model.memory.CardsMemory;
import by.clevertec.model.memory.ProductsMemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChecksMemoryDAOImpl implements IChecksDAO {

    @Override
    public boolean isValidString(String[] args) {
        String arrToStr= Arrays.toString(args);
        Pattern pattern = Pattern.compile(".(\\d+[-]\\d+[,]\s){3}\\w*.*.$");
        Matcher matcher = pattern.matcher(arrToStr);
        boolean found = matcher.matches();
        return found;
    }

    @Override
    public Checks generateСheck(String[] args) throws IdProductNotFoundException, IllegalArgumentException {
        List<AbstractProducts> products=new ArrayList<>();
        Cards cards;
        if(isValidString(args)) {
            cards = CardsMemory.getById(args[args.length - 1]);
            for (int i = 0; i < args.length - 1; i++) {
                String[] word = args[i].split("-");
                int id = Integer.parseInt(word[0]);
                String amount = word[1];
                AbstractProducts product = null;
                product = ProductsMemory.getById(id);
                product.setAmount(Integer.parseInt(amount));
                products.add(product);
            }
        }else{
            throw new IllegalArgumentException("Invalid argument input format");
        }
        Checks checks=new Checks(cards,products);
        return checks;
    }

}
