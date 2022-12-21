package by.clevertec;

import by.clevertec.model.action.Actions;
import by.clevertec.model.beans.checks.Checks;
import by.clevertec.model.exceptions.IdProductNotFoundException;


import java.io.*;
import java.util.Scanner;

public class Runner_1 {
    public static void main(String[] args) {
        final String FILE_NAME_CHECKED="src/main/resources/listFileTXT/printedCheck.txt";
        final String FILE_NAME_PRODUCTS="src/main/resources/listFileTXT/productList.txt";
        final String FILE_NAME_CARDS="src/main/resources/listFileTXT/cardList.txt";

        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.println("Action List");
            System.out.println("1. Output a list of products and maps from a file");
            System.out.println("2. Retrieve a list of products and maps from the database");
            System.out.println("3. Withdraw a check");
            System.out.println("4. Exit");

            int command=scanner.nextInt();

            switch (command) {
                case 1:
                    System.out.println("Output a list of products and maps from a file");
                    System.out.println("List products");
                    Actions.fileReader(FILE_NAME_PRODUCTS);
                    System.out.println("\nList maps");
                    Actions.fileReader(FILE_NAME_CARDS);
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Retrieve a list of products and maps from the database");
                    Actions.executeSqlFile();
                    System.out.println("List products");
                    Actions.getProductsList();
                    System.out.println("\nList maps");
                    Actions.getCardsList();
                    System.out.println();
                    break;
                case 3:
                    Checks checks=new Checks();

                    try {
                        checks.printCheck(args);
                    } catch (IdProductNotFoundException e) {
                        System.out.println(e.getId());
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage()+args);
                    }

                    try {
                        checks.writerCheckInFile(FILE_NAME_CHECKED,args);
                    } catch (IOException e) {
                        System.out.println("File not found");
                    } catch (IdProductNotFoundException e) {
                        System.out.println(e.getMessage());
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nThere is no such item! Make another choice\n");
             }
        }
    }
}


