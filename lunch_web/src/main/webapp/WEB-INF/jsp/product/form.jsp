<%-- 
    Document   : add
    Created on : Aug 18, 2009, 9:07:08 AM
    Author     : brh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<h1>Add product</h1>

        <form:form commandName="product">
            <input type="hidden" name="id" value="${product.id}" />
            <table>
                <tr>
                    <td>Name:</td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><form:input path="price" /></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="save" /></td>
                </tr>
            </table>
        </form:form>
 <%@ include file="/WEB-INF/jsp/footer.jsp" %>