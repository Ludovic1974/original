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
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-lg-4">
					<fieldset>
						<legend>${titulo}</legend>
						<p>${descripcion}</p>
						<form:form action="save" method="post" modelAttribute="book">
							<div class="mb-3">
								<form:label path="title" cssClass="form-label">Título<span
										class="asterix">*</span>
								</form:label>
								<form:input id="title" path="title" cssClass="form-control"
									onmouseover="pinta('title')" onmouseout="vuelve('title')" />
								<form:errors path="title" cssClass="error" />
								<form:hidden path="id" />
								<form:hidden path="bookDetails.id" />
							</div>
							<div class="mb-3">
								<form:label path="author" cssClass="form-label">Autor<span
										class="asterix">*</span>
								</form:label>
								<form:input id="author" path="author" cssClass="form-control"
									onmouseover="pinta('author')" onmouseout="vuelve('author')" />
								<form:errors path="author" cssClass="error" />
							</div>
							<div class="mb-3">
								<label for="category" class="form-label">Categoría<span
									class="asterix">*</span></label>
								<form:select id="category" path="category" cssClass="form-control" items="${categories}" itemValue="name" itemLabel="name" onmouseover="pinta('category')" onmouseout="vuelve('category')" />
								<form:errors path="category" cssClass="error" />
							</div>
							<div class="mb-3">
								<form:label path="bookDetails.publication_year"
									cssClass="form-label">Año de publicación</form:label>
								<form:input id="publication_year"
									path="bookDetails.publication_year" cssClass="form-control"
									onmouseover="pinta('publication_year')"
									onmouseout="vuelve('publication_year')" />
								<form:errors path="bookDetails.publication_year"
									cssClass="error" />
							</div>

							<div class="mb-3">
								<form:label path="bookDetails.purchase_year"
									cssClass="form-label">Año de compra</form:label>
								<form:input id="purchase_year" path="bookDetails.purchase_year"
									cssClass="form-control" onmouseover="pinta('purchase_year')"
									onmouseout="vuelve('purchase_year')" />
								<form:errors path="bookDetails.purchase_year" cssClass="error" />
							</div>

							<div class="mb-3">
								<form:label path="bookDetails.page_number" cssClass="form-label">Número de páginas</form:label>
								<form:input id="page_number" path="bookDetails.page_number"
									cssClass="form-control" onmouseover="pinta('page_number')"
									onmouseout="vuelve('page_number')" />
								<form:errors path="bookDetails.page_number" cssClass="error" />
							</div>
							<button type="submit" class="btn btn-primary">Validar</button>
						</form:form>
					</fieldset>
				</div>
				<div class="col-lg-8">

					<fieldset>
						<legend>Listado de libros</legend>
						<table class="table table-striped">
							<tr>
								<th width="22%">Título</th>
								<th width="15%">Autor</th>
								<th width="15%">Modificado</th>
								<th width="12%">Categoría</th>
								<th width="10%">Publicado</th>
								<th width="10%">Comprado</th>
								<th width="10%">Páginas</th>
								<th colspan="2">Acciones</th>

							</tr>
							<c:if test="${how_many > 0}">
								<c:forEach items="${books}" var="book">

									<c:url var="delete" value="delete">
										<c:param name="id" value="${book.id}" />
									</c:url>

									<c:url var="edit" value="edit">
										<c:param name="id" value="${book.id}" />
									</c:url>

									<tr>
										<td>${book.title}</td>
										<td>${book.author}</td>
										<td><fmt:formatDate type="both" value="${book.updatedAt}"
												dateStyle="long" timeStyle="short" /></td>
												<td>${book.category.name}</td>
										<td>${book.bookDetails.publication_year}</td>
										<td>${book.bookDetails.purchase_year}</td>
										<td>${book.bookDetails.page_number}</td>
										<td colspan="2">
										<a href="${edit}"
											title="Actualizar ${book.title} con id ${book.id}">
												<button type="submit" class="btn btn-success btn-sm">M</button></a>
										<a href="${delete}"
											title="Borrar ${book.title} con id ${book.id}">
												<button type="submit" class="btn btn-danger btn-sm"
													onclick="if(!(confirm('¿Seguro que quieres eliminar el registro?'))) return false">X</button>
										</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${how_many == 0}">
								<tr>
									<td colspan="9">La tabla no contiene registros</td>
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