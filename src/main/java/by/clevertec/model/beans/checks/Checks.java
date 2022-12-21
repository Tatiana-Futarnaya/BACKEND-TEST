package by.clevertec.model.beans.checks;

import by.clevertec.model.action.Actions;
import by.clevertec.model.beans.cards.Cards;
import by.clevertec.model.beans.products.AbstractProducts;
import by.clevertec.model.beans.products.NonPromotionalProduct;
import by.clevertec.model.enums.ProductClasses;
import by.clevertec.model.exceptions.IdProductNotFoundException;
import by.clevertec.model.memory.CardsMemory;
import by.clevertec.model.memory.ProductsMemory;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checks {
    private Cards cards;
    private List<AbstractProducts> products;
    Date date;

    public Checks() {

    }

    public Checks(Cards cards, List<AbstractProducts> products) {
        this.cards = cards;
        this.products = products;
        NonPromotionalProduct.setDiscountPercent(cards.getDiscount());

    }

    public String stringDate(){
        date=new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = DateFor.format(date);
        return stringDate;
    }

    public String stringTime(){
        date=new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("HH:mm:ss");
        String stringTime = DateFor.format(date);
        return stringTime;
    }

    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public List<AbstractProducts> getProducts() {
        return products;
    }

    public void setProducts(List<AbstractProducts> products) {
        this.products = products;
    }


    public void writerCheckInFile(String FILE_NAME, String[] args) throws IOException, IdProductNotFoundException {
        if(FILE_NAME==null){
            throw new FileNotFoundException();
        }
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PrintStream ps = new PrintStream(baos);
            PrintStream originalStdOut = System.out;
            System.setOut(ps);
            printCheck(args);
            System.out.flush();
            System.setOut(originalStdOut);
            try (OutputStream fos = new FileOutputStream(FILE_NAME)) {
                baos.writeTo(fos);
            }
        }
    }

    public double totalCost(){
        double sum = 0;
        for (int i=0; i<products.size();i++){
            sum = sum + products.get(i).cost();
        }
        return Actions.roundingNumber(sum);
    }

    public double getSumDiscountWithCard(List<AbstractProducts> list){
        String className=ProductClasses.NonPromotionalProduct.getValue();
        return getSumDiscount(list,className);
    }

    public double getSumDiscount(List<AbstractProducts> list, String className){
        double sum = 0;
        for (int i=0; i<list.size();i++){
            if(list.get(i).getClass().getName()==className){
                sum = sum + list.get(i).getDiscountAmount();
            }
        }
        return  Actions.roundingNumber(sum);
    }

    public double getSumPromotionDiscount(List<AbstractProducts> list){
        String className=ProductClasses.PromotionalProduct.getValue();
        return getSumDiscount(list,className);
    }

    public boolean isValidString(String[] args){
        String arrToStr= Arrays.toString(args);
        Pattern pattern = Pattern.compile(".(\\d+[-]\\d+[,]\s){1,}\\w*.*.$");
        Matcher matcher = pattern.matcher(arrToStr);
        boolean found = matcher.matches();
        return found;
    }

    public Checks generateСheck(String[] args) throws IdProductNotFoundException,IllegalArgumentException {
        List<AbstractProducts> products=new ArrayList<>();
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

    public void printCheck(String[] args) throws IdProductNotFoundException {
        Checks checks=generateСheck(args);
        List<AbstractProducts> list=checks.getProducts();
        System.out.println(  "             CASH RECEIPT\n"
                            +"            supermarket 123\n"
                            +"       12, MILKYWAY Galaxy/Earth\n"
                            +"           Tel: 123-456-7890");
        System.out.println("Cashier: 1520"+"          "+"DATE:"+stringDate());
        System.out.println("                       "+"TIME:"+ stringTime());
        System.out.println("----------------------------------------");
        System.out.println("QTY"+"     "+"DESCRIPTION"+"     "+"PRICE"+"     "+"TOTAL");
        for (int i=0; i<checks.getProducts().size();i++){
        System.out.printf("%-8d%-16s%-10.2f%-2.2f\n"
                ,list.get(i).getAmount(),list.get(i).getName(),list.get(i).getPrice(),list.get(i).cost());}
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL%29s%.2f\n","$",checks.totalCost());
        System.out.printf("DISCOUNT WITH CARD.%.0f%-13s%s%.2f\n"
                ,NonPromotionalProduct.getDiscountPercent(),"%","$", getSumDiscountWithCard(checks.getProducts()));
        System.out.printf("PROMOTION DISCOUNT.%15s%.2f\n\n"
                ,"$", getSumPromotionDiscount(checks.getProducts()));
    }
}
