<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<div class="col-8  mx-auto">
					<h1 class="text-center mb-4">${titulo}</h1>
					<p class="text-start mb-4">${descripcion}</p>
					<p class="text-start mb-4">${descripcion_larga}</p>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/templates/footer_no_fixed.jsp">
		<jsp:param name="web" value="ludoviclaisnez.com" />
	</jsp:include>
</body>
</html>