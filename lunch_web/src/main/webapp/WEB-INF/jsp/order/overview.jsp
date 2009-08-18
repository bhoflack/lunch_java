<%@ include file="/WEB-INF/jsp/header.jsp" %>
        <h1>Order overview</h1>

        <ul>
          <c:forEach var="order" items="${orders}">
              <li>${order}</li>
          </c:forEach>
        </ul>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
