<%-- 
    Document   : list
    Created on : Aug 17, 2009, 2:00:17 PM
    Author     : brh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
    <head>
        <title>Lunch</title>
    </head>
    <body>
        <h1>List of products</h1>
        <ul>
            <c:forEach var="product" items="${products}">
              <li>${product.name}</li>
            </c:forEach>    
        </ul>

    </body>
</html>
