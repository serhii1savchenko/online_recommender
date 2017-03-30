<%@include file="header.jsp"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">

<c:choose>
    <c:when test="${not empty films}">
    	<h4>Here is a list of recommended for you films. It's based on your previous preferences</h4><hr/>
        <c:forEach var="film" items="${films}">
			<a href="<c:url value="/user/film/${film.id}"/>" style="color: black;" onMouseOver="this.style.color='#00008B';" onMouseOut="this.style.color='black';">
				<div class="col-md-2" style="text-align: center; padding-bottom: 30px; height:300px;">
					<p>${film.title} (${film.year})<br/>
					Average rating: ${film.avgRating}<br/>
					Prediction for you: ${film.exactUserPrediction}</p>
					<img src="data:image/jpg;base64,${film.poster}" width="150px" height="200px"/><!-- <img src="data:image/jpg;base64,${film.poster}" width="85%" height="55%"/> -->
				</div>
			</a>
		</c:forEach><hr/>
		<h4>And here is a list of top rated films you havn't seen yet</h4><hr/>
		<c:forEach var="film" items="${filmsByAvgRating}">
			<a href="<c:url value="/user/film/${film.id}"/>" style="color: black;" onMouseOver="this.style.color='#00008B';" onMouseOut="this.style.color='black';">
				<div class="col-md-2" style="text-align: center; padding-bottom: 30px; height:300px;">
					<p>${film.title} (${film.year})<br/>
					Average rating: ${film.avgRating}<br/>
					<img src="data:image/jpg;base64,${film.poster}" width="150px" height="200px"/>
				</div>
			</a>
		</c:forEach>
    </c:when>
    <c:otherwise>
        <h4>Sorry, we can't give you personalized recommendations. Here is a list of top rated films you havn't seen yet</h4><hr/>
        <c:forEach var="film" items="${filmsByAvgRating}">
			<a href="<c:url value="/user/film/${film.id}"/>" style="color: black;" onMouseOver="this.style.color='#00008B';" onMouseOut="this.style.color='black';">
				<div class="col-md-2" style="text-align: center; padding-bottom: 30px; height:300px;">
					<p>${film.title} (${film.year})<br/>
					Average rating: ${film.avgRating}<br/>
					<img src="data:image/jpg;base64,${film.poster}" width="150px" height="200px"/>
				</div>
			</a>
		</c:forEach>
    </c:otherwise>
</c:choose>


		</div>
	</div>
</div>

<%@include file="footer.jsp"%>
</body>
</html>