package by.clevertec.controllers.checksControllers;

import by.clevertec.constant.AppConstant;
import by.clevertec.model.beans.checks.Checks;
import by.clevertec.model.exceptions.IdProductNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PrintCheckController", value = AppConstant.PRINT_CHECK_CONT)
public class PrintCheckController extends AbstractChecksController{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId1=request.getParameter("itemId1");
        String quantity1=request.getParameter("quantity1");
        String itemId2=request.getParameter("itemId2");
        String quantity2=request.getParameter("quantity2");
        String itemId3=request.getParameter("itemId3");
        String quantity3=request.getParameter("quantity3");
        String itemIdCard=request.getParameter("itemIdCard");
        String quantityCard=request.getParameter("quantityCard");

        String str=itemId1+"-"+quantity1+" "+
                itemId2+"-"+quantity2+" "+
                itemId3+"-"+quantity3+" "+
                itemIdCard+"-"+quantityCard;
        String[] arr = str.split(" ");
        System.out.println(arr);
        Checks check=null;
        try {
            check=checksDAO.generateСheck(arr);
            request.setAttribute(AppConstant.CHECK_ATTR,check);
        } catch (IdProductNotFoundException e) {
            e.printStackTrace();
        }
        forward(request,response,AppConstant.CHECK_JSP);
    }
}
