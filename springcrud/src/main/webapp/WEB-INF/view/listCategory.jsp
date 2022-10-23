<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<form:form action="save" method="post" modelAttribute="category">
							<div class="mb-3">
								<form:label path="name" cssClass="form-label">Nombre<span
										class="asterix">*</span>
								</form:label>
								<form:input id="name" path="name" cssClass="form-control"
									onmouseover="pinta('name')" onmouseout="vuelve('name')" />
								<form:errors path="name" cssClass="error" />
								<c:if test="${categoryExist != null }">
									<span class="error">${categoryExist}</span>
								</c:if>
								<form:hidden path="id" />
							</div>
							<button type="submit" class="btn btn-primary">Validar</button>
						</form:form>
					</fieldset>
				</div>
				<div class="col-lg-8">

					<fieldset>
						<legend>Listado de categorías</legend>
						<table class="table table-striped">
							<tr>
								<th>Nombre</th>
								<th>Modificado</th>
								<th colspan="2">Acciones</th>

							</tr>
							<c:if test="${how_many > 0}">
								<c:forEach items="${categories}" var="category">

									<c:url var="delete" value="delete">
										<c:param name="id" value="${category.id}" />
									</c:url>

									<c:url var="edit" value="edit">
										<c:param name="id" value="${category.id}" />
									</c:url>

									<tr>
										<td>${category.name}</td>
										<td><fmt:formatDate type="both"
												value="${category.updatedAt}" dateStyle="long"
												timeStyle="short" /></td>
										<td><a href="${edit}"
											title="Actualizar ${category.name} con id ${category.id}">
												<button type="submit" class="btn btn-success btn-sm">M</button>
										</a></td>
										<td><a href="${delete}"
											title="Borrar ${category.name} con id ${category.id}">
												<button type="submit" class="btn btn-danger btn-sm"
													onclick="if(!(confirm('¿Seguro que quieres eliminar el registro?'))) return false">X</button>
										</a></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${how_many == 0}">
								<tr>
									<td colspan="4">La tabla no contiene registros</td>
								</tr>
							</c:if>

						</table>
					</fieldset>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function pinta(id) {
			document.getElementById(id).style.backgroundColor = "#d2d2d2";
		}

		function vuelve(id) {
			document.getElementById(id).style.backgroundColor = "WHITE";
		}
	</script>
	<jsp:include page="/WEB-INF/view/templates/footer.jsp">
		<jsp:param name="web" value="ludoviclaisnez.com" />
	</jsp:include>
</body>
</html>