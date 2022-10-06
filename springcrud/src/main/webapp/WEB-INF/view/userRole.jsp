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
				<div class="col-lg-4">
					<fieldset>
						<legend>${titulo}</legend>
						<p>${descripcion}</p>
						<form:form action="add_role" method="post" modelAttribute="role">
							<div class="mb-3">

								<form:label path="user.username" cssClass="form-label">Nombre de perfil<span
										class="asterix">*</span>
								</form:label>
								<form:input path="user.username" disabled="true"
									cssClass="form-control" value="${user.username}" />
								<form:errors path="user.username" cssClass="errors" />
								<form:hidden path="user.username" value="${user.username}" />
							</div>
							<div class="mb-3">
								<label for="authority" class="form-label">Rol<span
									class="asterix">*</span></label>
								<form:select path="authority" cssClass="form-control">
<%-- 									<form:option value="" label="Seleccionar permiso" /> --%>
									<c:forEach var="role" items="${rolelist}">
										<option value="${role}" label="${role}" />
									</c:forEach>
								</form:select>
								<form:errors path="authority" cssClass="errors" />
<!-- 								<div class="form-switch text-start"> -->
<!-- 									<label for="roles" class="form-check-label">Permisos<span -->
<!-- 									class="asterix">*</span></label><br/>							 -->
<%-- 									<form:checkboxes path="roles" cssClass="form-check-input mx-3" items="${rolelist}" itemValue="authority" itemLabel="authority" delimiter="<br/>"/> --%>
<%-- 									<form:errors path="roles" cssClass="errors" /> --%>
<!-- 								</div> -->
							</div>
							<button type="submit" class="btn btn-primary">Validar</button>
						</form:form>
					</fieldset>
				</div>
				<div class="col-lg-8">
					<table class="table table-striped">
						<tr>
							<th>Nombre de perfil</th>
							<td>${user.username}</td>
						</tr>
						<tr>
							<th>Nombre</th>
							<td>${user.name}</td>
						</tr>
						<tr>
							<th>Apellido</th>
							<td>${user.surname}</td>
						</tr>
						<tr>
							<th>Email</th>
							<td>${user.email}</td>
						</tr>
						<tr>
							<th>Habilitado</th>
							<td>${user.enabled==true ? "Sí" : "No"}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/templates/footer.jsp">
		<jsp:param name="web" value="ludoviclaisnez.com" />
	</jsp:include>
</body>
</html>