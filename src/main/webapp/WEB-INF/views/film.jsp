<%@include file="header.jsp"%>

<div class="container-fluid">
	<div class="row" style="padding-top:10px;">
		<div class="col-sm-6" style="text-align: center;">
			<img src="data:image/jpg;base64,${film.poster}" width="400px" height="534px"/>
		</div>
		<div class="col-sm-6" style="padding-top:25px;">
			<div style="margin: auto;">
				<h2>Title: ${film.title}<br/><br/></h2>
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
					<c:choose>
					<c:when test="${film.exactUserRating > 0}">
					<h3>You have rated this film for ${film.exactUserRating} out of 5</h3>
					</c:when>
					<c:otherwise>
			       		<h3>Rate this film</h3>
			       		<div style="width: 100px; text-align: left;">
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
			       	 		<button class="btn btn-success" onclick="rate(${film.id});">Rate!</button>
			       	 	</div>
			       	 	<c:choose>
			       	 		<c:when test="${prediction > 0}">
								<h4>We predict: ${prediction}</h4>
							</c:when>
			       	 		<c:otherwise>
			       	 			<h4>We can't predict your rating</h4>
			       	 		</c:otherwise>
			       	 	</c:choose>
					</c:otherwise>
				</c:choose>
				</h3>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/rateFilm.js"/>"></script>
</body>
</html>