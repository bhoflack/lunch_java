<%@ include file="/WEB-INF/jsp/header.jsp" %>
        <h1>List of products</h1>
        <ul>
            <c:forEach var="product" items="${products}">
              <li>${product.name} <a href="edit.do?productId=${product.id}">edit</a></li>
            </c:forEach>    
        </ul>

        <a href="add.do">Add a product</a>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>