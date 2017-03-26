<%@ page session="true" %>
<%@include file="header.jsp" %>


<h1>
	Hello  <c:out value="${sessionScope.name}"/>!
</h1>

<%@include file="footer.jsp" %>
</body>
</html>