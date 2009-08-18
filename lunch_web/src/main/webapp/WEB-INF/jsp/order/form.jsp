<%@include file="/WEB-INF/jsp/header.jsp" %>
  <h1>Add order</h1>
  <form:form commandName="order">
      <input type="hidden" value="${order.id}" />
      <table>
          <tr>
              <td>Who:</td>
              <td><form:select path="who" items="${users}" /></td>
          </tr>
          <tr>
              <td>User:</td>
              <td><form:select path="user" items="${users}" /></td>
          </tr>
          <tr>
              <td>Date:</td>
              <td><form:input path="date" /></td>
          </tr>
          <tr>
              <td>Products:</td>
              <td><form:checkboxes path="products" items="${products}" /></td>
          </tr>
          
          <tr>
              <td colspan="2"><input type="submit" value="save" /></td>
          </tr>
      </table>
  </form:form>
<%@include file="/WEB-INF/jsp/footer.jsp" %>