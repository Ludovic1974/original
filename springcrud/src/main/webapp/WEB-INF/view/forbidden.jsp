<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/templates/head.jsp"></jsp:include>
</head>
<body class="d-flex flex-column h-100 text-center">
	<jsp:include page="/WEB-INF/view/templates/nav.jsp"></jsp:include>
	<div class="flex-shrink-0">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<h1 class="text-center">${titulo}</h1>
					<p class="text-center">${descripcion}</p>
					<p class="text-center"><security:authorize access="isAuthenticated()">
						Te saludamos querido
						<security:authentication property="principal.username" /></security:authorize>
						Por desgracia, no tienes acceso a todos nuestra secretos de la
						página web.
					${lorem}
					<a href="${pageContext.request.contextPath}/index"
						title="Volver al inicio">Inicio</a>


				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/templates/footer.jsp">
		<jsp:param name="web" value="campus2b.com" />
	</jsp:include>
</body>
</html>