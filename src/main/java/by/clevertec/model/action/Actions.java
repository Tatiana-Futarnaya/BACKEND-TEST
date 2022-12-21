package by.clevertec.model.action;

import by.clevertec.model.db.ConnectionManager;
import by.clevertec.model.db.SQLRequest;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Actions {
    public static void fileReader(String filename) {
        try (Reader reader = new FileReader(filename);
             Scanner sc = new Scanner(reader)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getProductsList(){
        try(Connection cn= ConnectionManager.getConnection();
            PreparedStatement pst=cn.prepareStatement(SQLRequest.SELECT_PRODUCTS)) {
            ResultSet rs= pst.executeQuery();
            while (rs.next()){
                int id=rs.getInt(SQLRequest.ID_PRODUCT);
                String name=rs.getString(SQLRequest.NAME_PRODUCT);
                double price=rs.getDouble(SQLRequest.PRICE_PRODUCT);
                System.out.printf("%d.%s-$%.2f\n",id,name,price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getCardsList(){
        try(Connection cn= ConnectionManager.getConnection();
            PreparedStatement pst=cn.prepareStatement(SQLRequest.SELECT_CARDS)) {
            ResultSet rs= pst.executeQuery();
            while (rs.next()){
                int id=rs.getInt(SQLRequest.ID_CARD);
                String name=rs.getString(SQLRequest.NAME_CARD);
                int percent=rs.getInt(SQLRequest.PERCENT_CARD);
                System.out.printf("%d.%s-%d%s\n",id,name,percent,"%");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeSqlFile(){
        String script = "src/main/resources/operationsDDL.sql";

        try(Connection con = ConnectionManager.getConnection()) {
            ScriptRunner sr = new ScriptRunner(con);
            Reader reader = new BufferedReader(new FileReader(script));
            sr.runScript(reader);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static double roundingNumber(double number){
        double scale = Math.pow(10, 2);
        double result = Math.ceil(number * scale) / scale;
        return result;
    }
}
