<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<p>${descripcion}</p>
					<form:form action="save" method="post" modelAttribute="user">
						<fieldset>
							<legend>${titulo}</legend>
							<div class="mb-3">

								<c:choose>
									<c:when test="${param.username == null}">
										<form:label path="username" cssClass="form-label">Nombre de perfil<span
												class="asterix">*</span>
										</form:label>
										<form:input path="username" cssClass="form-control" />

									</c:when>
									<c:otherwise>
										<form:label path="username" cssClass="form-label">Nombre de perfil</form:label>
										<form:input path="username" cssClass="form-control"
											disabled="true" />
										<form:hidden path="username" />
									</c:otherwise>
								</c:choose>
								<form:errors path="username" cssClass="error" />
								<form:hidden path="enabled" />
							</div>
							<div class="mb-3">
								<form:label path="name" cssClass="form-label">Nombre</form:label>
								<form:input path="name" cssClass="form-control" />
								<form:errors path="name" cssClass="error" />
							</div>
							<div class="mb-3">
								<form:label path="surname" cssClass="form-label">Apellido</form:label>
								<form:input path="surname" cssClass="form-control" />
								<form:errors path="surname" cssClass="error" />
							</div>
							<div class="mb-3">
								<form:label path="email" cssClass="form-label">Email<span
										class="asterix">*</span>
								</form:label>
								<form:input path="email" cssClass="form-control" />
								<form:errors path="email" cssClass="error" />
								<c:if test="${emailExist != null }">
									<span class="error">${emailExist}</span>
								</c:if>
							</div>
							<div class="mb-3">
								<form:label path="password" cssClass="form-label">Contraseña<span
										class="asterix">*</span>
								</form:label>
								<form:password path="password" cssClass="form-control" />
								<form:errors path="password" cssClass="error" />
								<c:if test="${equalPasswords != null }">
									<span class="error">${equalPasswords}</span>
								</c:if>
							</div>
							<div class="mb-3">
								<form:label path="confirmPassword" cssClass="form-label">Repetir contraseña<span
										class="asterix">*</span>
								</form:label>
								<form:password path="confirmPassword" cssClass="form-control" />
								<form:errors path="confirmPassword" cssClass="error" />
							</div>
							<button type="submit" class="btn btn-primary">Validar</button>
						</fieldset>
					</form:form>
				</div>
				<div class="col-lg-8">
					<div class="accordion accordion-flush" id="accordionPages">
						<c:if test="${how_many > 0}">
							<c:forEach items="${users}" var="user">
								<div class="accordion-item">
									<h2 class="accordion-header" id="heading${user.username}">
										<button class="accordion-button" type="button"
											data-bs-toggle="collapse"
											data-bs-target="#collapse${user.username}"
											aria-expanded="true" aria-controls="collapse${user.username}">
											${user.username}</button>
									</h2>
								</div>
								<div id="collapse${user.username}"
									class="accordion-collapse collapse"
									aria-labelledby="heading${user.username}"
									data-bs-parent="#accordionPages">
									<div class="accordion-body">
										<table class="table table-striped">
											<tr>
												<th>Nombre</th>
												<th>Apellido</th>
												<th>Email</th>
												<th>Modificado</th>
												<th>Permisos</th>
												<th>Activado</th>
												<th colspan="4">Acciones</th>
											</tr>
											<c:url var="loan_books" value="loan_books">
												<c:param name="username" value="${user.username}" />
											</c:url>

											<c:url var="delete" value="delete">
												<c:param name="username" value="${user.username}" />
											</c:url>
											<c:url var="edit" value="edit">
												<c:param name="username" value="${user.username}" />
											</c:url>
											<c:url var="role" value="role">
												<c:param name="username" value="${user.username}" />
											</c:url>

											<tr>
												<td>${user.name}</td>
												<td>${user.surname}</td>
												<td>${user.email}</td>
												<td><fmt:formatDate type="both"
														value="${user.updatedAt}" dateStyle="long"
														timeStyle="short" /></td>
												<td><c:if test="${user.roles!=null}">
														<c:forEach items="${user.roles}" var="r">
														${r.authority}
														<c:if test="${user.roles.size()>1}"><br/></c:if>
													</c:forEach>
													</c:if> <c:if test="${user.roles.isEmpty()}">
													Sin permisos
												</c:if></td>

												<td>${user.enabled==true ? "Activado":"Desactivado"}</td>
												<td><a href="${loan_books}"
													title="Consultar listado de libros prestados a ${user.name}">
														<button type="submit" class="btn btn-primary btn-sm">Gestionar</button>
												</a></td>
												<td><a href="${edit}" title="Actualizar ${user.name}">
														<button type="submit" class="btn btn-success btn-sm">Modificar</button>
												</a></td>
												<td><a href="${delete}" title="Borrar ${user.name}">
														<button type="submit" class="btn btn-danger btn-sm"
															onclick="if(!(confirm('¿Seguro que quieres eliminar el registro?'))) return false">Borrar</button>
												</a></td>
												<td><a href="${role}"
													title="Gestionar permisos de ${user.name}">
														<button type="submit" class="btn btn-info btn-sm">Permisos</button>
												</a></td>
											</tr>
										</table>
										<c:if test="${user.books.size() > 0}">
											<table class="table table-striped table-dark table-hover">
												<thead>
													<tr>
														<th scope="col" class="text-center">Id</th>
														<th scope="col" class="text-center">Libro</th>
														<th scope="col" class="text-center">Autor</th>
														<th scope="col" class="text-center">Año de
															adquisición</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${user.books}" var="book">
														<tr>
															<th class="text-center">${book.id}</th>
															<th class="text-center">${book.title}</th>
															<th class="text-center">${book.author}</th>
															<th class="text-center">${book.bookDetails.purchase_year}</th>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</c:if>
									</div>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${how_many == 0}">
							<div class="accordion-item">
								<h2 class="accordion-header">
									<button class="accordion-button" type="button">No hay
										ningun usuario registrado</button>
								</h2>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/view/templates/footer.jsp">
		<jsp:param name="web" value="ludoviclaisnez.com" />
	</jsp:include>
</body>
</html>