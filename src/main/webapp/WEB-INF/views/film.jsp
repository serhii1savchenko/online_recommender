<%@include file="header.jsp"%>

<div class="container-fluid">
	<div class="row" style="padding-top:10px;">
		<div class="col-sm-5" style="text-align: center;">
			<img src="data:image/jpg;base64,${film.poster}" width="400px" height="534px"/>
		</div>
		<div class="col-sm-7" style="padding-top:25px;">
			<div style="margin: auto;">
				<hr/>
					<h2>Title: ${film.title}<br/></h2>
						<h3>Year: ${film.year}</h3>
						<hr/>
						<c:choose>
							<c:when test="${film.avgRating > 0}">
							<h3>Average Rating: </h3>
							<div class="row">
								<div class="col-sm-1" >
									(${film.avgRating})
								</div>
								<div class="col-sm-11" >
									<input id="outputAvg" name="outputAvg" value="${film.avgRating}" class="rating rating-loading" data-size="md" data-show-clear="false" data-readonly="true" data-step="0.1" />
								</div>
							</div>
							</c:when>
							<c:otherwise>
				        	<h3>No ratings yet</h3>
							</c:otherwise>
						</c:choose><br/>
						<c:choose>
						<c:when test="${film.exactUserRating > 0}">
						<h3>You have rated this film for:</h3>
						<div class="row">
							<div class="col-sm-1" >
								(${film.exactUserRating})
							</div>
							<div class="col-sm-11" >
								<input id="outputUser" name="outputUser" value="${film.exactUserRating}" class="rating rating-loading" data-size="md" data-show-clear="false" data-readonly="true" data-step="0.1" />
							</div>
						</div>
						</c:when>
						<c:otherwise>
				       		<h3>Rate this film</h3>
								<input id="input" class="rating rating-loading" data-min="0.5" data-max="5" data-step="0.5" data-size="lg" data-show-clear="false" />
				       	 		<button class="btn btn-success btn-md" onclick="rate(${film.id});">Rate!</button>
				       	 		<hr/>
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
				<hr/>
			</div>
		</div>
	</div>
</div>

<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/resources/js/pages/rateFilm.js"/>"></script>
</body>
</html>