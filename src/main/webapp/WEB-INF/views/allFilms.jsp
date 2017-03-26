<%@include file="header.jsp"%>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<c:forEach var="film" items="${films}">
				<a href="<c:url value="/user/film/${film.id}"/>">
					<div class="col-md-3" style="text-align: center; padding-bottom: 30px;">
						<p>
							${film.title} (${film.year})
							<c:choose>
								<c:when test="${film.avgRating > 0}">
									Rating: ${film.avgRating}
								</c:when>
								<c:otherwise>
		        					No ratings yet
								</c:otherwise>
							</c:choose>
						</p>
						<img src="data:image/jpg;base64,${film.poster}" width="300px" height="400px"/><!-- <img src="data:image/jpg;base64,${film.poster}" width="85%" height="55%"/> -->
					</div>
				</a>
			</c:forEach>

		</div>
	</div>
</div>

<%@include file="footer.jsp"%>
</body>
</html>