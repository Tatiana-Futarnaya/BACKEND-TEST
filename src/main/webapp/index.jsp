<%@ page import="by.clevertec.constant.AppConstant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>MainPage</title>
</head>
<body>
<h1> Print check</h1>
<form action="<c:url value="<%= AppConstant.PRINT_CHECK_CONT %>"/>" method="get">
  1. ProductParameters: </div><input type="text" name="itemId1" value="3"> <input type="text" name="quantity1" value="1">
  <br> <br>
  2. ProductParameters:<input type="text" name="itemId2" value="2"> <input type="text" name="quantity2" value="5">
  <br> <br>
  3. ProductParameters:<input type="text" name="itemId3" value="5"> <input type="text" name="quantity3" value="1">
  <br> <br>
  Card: <input type="text" name="itemIdCard" value="card">  <input type="text" name="quantityCard" value="1234">
  <br> <br>
  <input type="submit" value="check">
</form>
</body>
</html>
