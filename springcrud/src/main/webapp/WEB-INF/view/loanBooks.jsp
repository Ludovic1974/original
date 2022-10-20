<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/templates/head.jsp"></jsp:include>
</head>
<body class="d-flex flex-column h-100 text-center">
	<jsp:include page="/WEB-INF/view/templates/nav.jsp"></jsp:include>
	<div class="flex-shrink-0">
		<div class="container">
			<div class="row mt-4">
				<div class="col-6 mx-auto">
					<p>${descripcion}</p>
					<form:form action="confirm_loan" method="post"	modelAttribute="user">
						<fieldset>
							<legend>${titulo}</legend>
							<div class="mb-3">
								<form:label path="name" cssClass="form-label">Nombre</form:label>
								<form:input disabled="true" path="name" cssClass="form-control" />
								<form:hidden path="username" />						
							</div>
							<div class="mb-3">
								<form:label path="email" cssClass="form-label">Email</form:label>
								<form:input disabled="true" path="email" cssClass="form-control" />								
							</div>
							<div class="mb-3">
								<div class="form-switch text-start">
									<label for="books" class="form-check-label">Libros prestados</label><br/>							
									<form:checkboxes path="books" cssClass="form-check-input mx-3" items="${booklist}" itemValue="title" itemLabel="title" delimiter="<br/>"/>
									<form:errors path="books" cssClass="error" />
								</div>
							</div>
							<button type="submit" class="btn btn-primary">Validar</button>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/templates/footer.jsp">
		<jsp:param name="web" value="ludoviclaisnez.com" />
	</jsp:include>
</body>
</html>