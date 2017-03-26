<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<script type="text/javascript"> var context = '${pageContext.request.contextPath}';</script> 
<html>

<head>
	<title>Film Recommender</title>
	<!-- jQuery -->
	<script type="text/javascript" src="<c:url value="/resources/jquery-3.2.0.min.js"/>"/></script>
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css"> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<!-- Google Fonts -->
	<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
	<!-- My style -->
	<link rel="stylesheet" href="<c:url value="/resources/style/style.css"/>" >
</head>

<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<c:url value="/home"/>" style="color:#FFD700;">Online Film Recommender</a>
    </div>
    <ul class="nav navbar-nav">
      <!-- ADMIN menu -->
      <sec:authorize access="hasRole('ADMIN')">
      	<li><a href="#">Add film</a></li>
      	<li><a href="#">Renew recommender engine</a></li>
      </sec:authorize>
      <!-- USER menu -->
      <sec:authorize access="hasRole('USER')">
      	<li><a href="#">All films</a></li>
      	<li><a href="#">My films</a></li>
      	<li><a href="#">Top 10 recommended</a></li>
      </sec:authorize>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <sec:authorize access="isAnonymous()">
      	<li><a href="<c:url value="/registration"/>"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      	<li><a href="<c:url value="/login"/>"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </sec:authorize>
      <sec:authorize access="isAuthenticated()">
      	<li><a href="<c:url value="/logout"/>"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </sec:authorize>
    </ul>
  </div>
</nav>
