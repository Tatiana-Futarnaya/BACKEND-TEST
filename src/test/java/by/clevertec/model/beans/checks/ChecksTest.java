package by.clevertec.model.beans.checks;

import by.clevertec.model.action.Actions;
import by.clevertec.model.beans.cards.Cards;
import by.clevertec.model.beans.products.AbstractProducts;
import by.clevertec.model.beans.products.NonPromotionalProduct;
import by.clevertec.model.beans.products.PromotionalProduct;
import by.clevertec.model.enums.ProductClasses;
import by.clevertec.model.exceptions.IdProductNotFoundException;
import by.clevertec.model.memory.CardsMemory;
import by.clevertec.model.memory.ProductsMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ChecksTest {
    Checks check1;
    Checks check2;
    List<AbstractProducts> products=new ArrayList<>();
    AbstractProducts product1;
    AbstractProducts product2;
    Cards cards1;


    @BeforeEach
    void prepareData(){
        cards1 =new Cards("1234",2);
        product1 =new PromotionalProduct("Cookie",15,10.25);
        product2 =new NonPromotionalProduct("Flour",4,7.5);
        products.add(product1);
        products.add(product2);
        check1 =new Checks(cards1,products);
        check2 =new Checks();
    }

    @Test
    void getCards() {
        assertEquals(cards1,check1.getCards());
    }

    @Test
    void setCards() {
        Cards cardsNews=new Cards("2648",10);
        check1.setCards(cardsNews);
        assertEquals(cardsNews,check1.getCards());
    }
    @Test
    void getProducts() {
        assertEquals(products,check1.getProducts());
    }

    @Test
    void setProducts() {
        List<AbstractProducts> productsNew=new ArrayList<>();
        AbstractProducts productNew1 =new PromotionalProduct("Milk",7,3.05);
        AbstractProducts productNew2 =new NonPromotionalProduct("Cheese",10,4.5);
        productsNew.add(productNew1);
        productsNew.add(productNew2);
        check1.setProducts(productsNew);
        assertEquals(productsNew,check1.getProducts());
    }

    @ParameterizedTest
    @MethodSource("isValidStringTrue")
    void writerCheckInFile(String[] args) throws FileNotFoundException, IdProductNotFoundException {
        final String FILE_NAME_CHECKED="src/test/resources/listFileTXT/printedCheck.txt";

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            PrintStream ps = new PrintStream(baos);
            PrintStream originalStdOut = System.out;
            System.setOut(ps);

            check1.printCheck(args);

            System.out.flush();
            System.setOut(originalStdOut);

            try (OutputStream fos = new FileOutputStream(FILE_NAME_CHECKED)) {
                baos.writeTo(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            assertNotNull(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @MethodSource("validString")
    void writerCheckInFileException(String[] args) {
        final String FILE_NAME_CHECKED=null;
        assertThrows(Exception.class,()->{
            check1.writerCheckInFile(FILE_NAME_CHECKED,args);
        });
    }


    @Test
    void totalCost() {
        double sum = 0;
        for (int i=0; i<products.size();i++){
            sum = sum + products.get(i).cost();
        }
        double result = Actions.roundingNumber(sum);
        assertEquals(result,check1.totalCost());
    }

    @ParameterizedTest
    @MethodSource("listProduct")
    void getSumDiscountWithCard(List<AbstractProducts> list) {
        String className=ProductClasses.NonPromotionalProduct.getValue();
        double sum = check1.getSumDiscount(list,className);
        assertEquals(sum,check1.getSumDiscountWithCard(list));
    }


    @ParameterizedTest
    @MethodSource("list")
    void getSumDiscount(List<AbstractProducts> list, String className) {
        double sum = 0;
        for (int i=0; i<list.size();i++){
            if(list.get(i).getClass().getName()==className){
                sum = sum + list.get(i).getDiscountAmount();
            }
        }
        double result = Actions.roundingNumber(sum);
        assertEquals(result,check1.getSumDiscount(list,className));
    }

    static Stream<Arguments> list() {
        final AbstractProducts product1 =new PromotionalProduct("Cookie",15,10.25);
        final AbstractProducts product2 =new NonPromotionalProduct("Flour",4,7.5);

        return Stream.of(
                arguments(List.of(product1,product2), ProductClasses.PromotionalProduct.getValue()),
                arguments(List.of(product1,product2), ProductClasses.NonPromotionalProduct.getValue())
                );
    }

    @ParameterizedTest
    @MethodSource("listProduct")
    void getSumPromotionDiscount(List<AbstractProducts> list) {
        String className=ProductClasses.PromotionalProduct.getValue();
        double sum = check1.getSumDiscount(list,className);
        assertEquals(sum,check1.getSumPromotionDiscount(list));
    }

    static Stream<Arguments> listProduct() {
        final AbstractProducts product1 =new PromotionalProduct("Cookie",15,10.25);
        final AbstractProducts product2 =new NonPromotionalProduct("Flour",4,7.5);

        return Stream.of(
                arguments(List.of(product1,product2))
        );
    }

    @ParameterizedTest
    @MethodSource("isValidStringTrue")
    void isValidStringTrue(String[] args) {
        String arrToStr= Arrays.toString(args);
        Pattern pattern = Pattern.compile(".(\\d+[-]\\d+[,]\s){1,}\\w*.*.$");
        Matcher matcher = pattern.matcher(arrToStr);
        boolean found = matcher.matches();
        assertTrue(found);
    }

    static Stream<Arguments> isValidStringTrue() {
        return Stream.of(
                Arguments.of((Object) new String[]{"3-2","5-1","4-3","card-1234"}));
    }

    @ParameterizedTest
    @MethodSource("validString")
    void generateСheckException(String[] args) {
        assertThrows(Exception.class,()->{
            check1.generateСheck(args);
        });
    }

    static Stream<Arguments> validString() {
        return Stream.of(
                Arguments.of((Object) new String[]{"-3-2","-5-1","4-3","card-1234"}));
    }

    @ParameterizedTest
    @MethodSource("isValidStringTrue")
    void generateСheck(String[] args) throws IdProductNotFoundException {
        List<AbstractProducts> products=new ArrayList<>();
        Checks checks=null;
        Cards cards = CardsMemory.getById(args[args.length - 1]);
        for (int i = 0; i < args.length - 1; i++) {
            String[] word = args[i].split("-");
            int id = Integer.parseInt(word[0]);
            String amount = word[1];
            AbstractProducts product = null;
            product = ProductsMemory.getById(id);
            product.setAmount(Integer.parseInt(amount));
            products.add(product);
        }
        checks=new Checks(cards,products);
        assertNotEquals(checks,check1.generateСheck(args));
    }


    @ParameterizedTest
    @MethodSource("isValidStringTrue")
    void printCheck(String[] args) throws IdProductNotFoundException {
        PrintStream originalOut=System.out;
        try(ByteArrayOutputStream outputStream=new ByteArrayOutputStream()){
            System.setOut(new PrintStream(outputStream));
            check1.printCheck(args);
            System.out.flush();
            System.setOut(originalOut);
            assertNotNull(outputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}