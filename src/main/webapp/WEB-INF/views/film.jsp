<%@include file="header.jsp"%>

<div class="container-fluid">
	<div class="row" style="padding-top:10px;">
		<div class="col-sm-4" style="text-align: center;">
			<img src="data:image/jpg;base64,${film.poster}" width="400px" height="534px"/>
		</div>
		<div class="col-sm-2" style="padding-left:25px;padding-top:25px;">
			<h2>
				Title: ${film.title} <br/><br/>
			</h2>
			<h3>
				Year: ${film.year} <br/><br/>
				<c:choose>
					<c:when test="${film.avgRating > 0}">
					Average Rating: ${film.avgRating}
					</c:when>
					<c:otherwise>
		        	No ratings yet
					</c:otherwise>
				</c:choose>
			</h3>
		</div>
		<div class="col-sm-4" style="padding-left:25px;padding-top:25px;">
			<div style="width: 50%; margin: auto; text-align: center;">
				<c:choose>
					<c:when test="${real > 0}">
					<h3>You have rated this film for ${real} out of 5</h3>
					</c:when>
					<c:otherwise>
			       		<h3>Rate this film</h3>
			       	 	<select class="form-control" id="rating">
			       	 		<option>1.0</option>
			       	 		<option>1.5</option>
			       	 		<option>2.0</option>
			       	 		<option>2.5</option>
			       	 		<option selected>3.0</option>
			       	 		<option>3.5</option>
			       	 		<option>4.0</option>
			       	 		<option>4.5</option>
			       	 		<option>5.0</option>
			       	 	</select><br/>
			       	 	<button class="btn btn-success col-md-6 col-md-offset-3" onclick="rate();">Rate!</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/rateFilm.js"/>"></script>
</body>
</html>