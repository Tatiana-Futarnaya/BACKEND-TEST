<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Check</title>
</head>
<body>
<div style="width: 350px;">
    <div >
        <div style="width: 250px; text-align: center;">
            CASH RECEIPT <br>
            supermarket 123<br>
            12, MILKYWAY Galaxy/Earth<br>
            Tel: 123-456-7890
        </div>
        <div>
            <div>
                <div style="width: 160px; float: left;" >
                    Cashier: 1520
                </div>
                <div style="width: 190px; float: left;">
                   DATE:${check.stringDate()}
                </div>
            </div>
            <div>
                <div style="width: 160px; float: left;" >
                    <p> </p>
                </div>
                <div style="width: 190px; float: left;">
                    TIME:${check.stringTime()}
                </div>
            </div>
        </div>
    </div>
        -----------------------------------------------------
    <table >
    <tr>
        <th>QTY</th>
        <th>DESCRIPTION</th>
        <th>PRICE</th>
        <th>TOTAL</th>

    <c:forEach var="checkNew" items="${check.getProducts()}">
        <tr>
            <td>${checkNew.amount}</td>
            <td>${checkNew.name}</td>
            <td>$${checkNew.price}</td>
            <td>$${checkNew.cost()}</td>
        </tr>
        </c:forEach>

    </tr>
    </table>
    ----------------------------------------------------- <br>
    -----------------------------------------------------
    <div>
        <div>
            <div style="width: 210px; float: left;">TOTAL.</div>
            <div style="width: 140px;  float: left;">
              $${check.totalCost()}
            </div>
        </div>
        <div>
            <div style="width:210px; float: left;">
                DISCOUNT WITH CARD.${NonPromotionalProduct.getDiscountPercent()}%
            </div>
            <div style="width: 140px;  float: left;">
                $${check.getSumDiscountWithCard(check.getProducts())}
            </div>
        </div>
        <div>
            <div style="width: 210px; float: left;">
                PROMOTION DISCOUNT.
            </div>
            <div style="width: 140px; float: left;">
                $${check.getSumPromotionDiscount(check.getProducts())}
            </div>
        </div>
    </div>
</div>

</body>
</html>
