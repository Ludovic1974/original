<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/view/templates/head.jsp"></jsp:include>

<link href="${pageContext.request.contextPath}/assets/css/login.css" rel="stylesheet" type="text/css" />
<!-- google login bootstrap https://mdbootstrap.com/docs/standard/extended/login/ -->

</head>
<body class="d-flex flex-column h-100 text-center">
	<div class="flex-shrink-0">
		<section class="h-100 gradient-form" style="background-color: #eee;">
			<div class="container py-5 h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-xl-10">
						<div class="card rounded-3 text-black">
							<div class="row g-0">
								<div class="col-lg-6">
									<div class="card-body p-md-5 mx-md-4">
										<div class="text-center">
											<img
												src="${pageContext.request.contextPath}/assets/img/lotus.webp"
												style="width: 185px;" alt="lotus">
											<h4 class="mt-1 mb-5 pb-1">${titulo}</h4>
										</div>
										<form:form
											action="${pageContext.request.contextPath}/autorizado"
											method="post">
											<p>Puedes conectarte para acceder a tu cuenta.</p>

											<div class="form-outline mb-3">
												<label class="form-label" for="form2Example11">Usuario</label><input
													type="text" name="username" id="form2Example11"
													class="form-control"
													placeholder="Nombre de usuario que usaste al crear la cuenta" />
											</div>

											<div class="form-outline mb-3">
												<label class="form-label" for="form2Example22">Contraseña</label><input
													type="password" name="password" id="form2Example22"
													class="form-control" />
											</div>

											<div class="text-center pt-1 mb-3 pb-1">
												<input type="submit"
													class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3"
													value="Validar" /><br />
													
													<c:if test="${param.error != null}">
													<span class="errors">Usuario o Contraseña incorrectos</span>
												</c:if>
												<c:if test="${param.logout != null}">
													<span class="information">¡¡Hasta pronto!!</span>
												</c:if>
												<c:if test="${param.error == null}">
													<br />
												</c:if>
												
											</div>
											
										</form:form>
									</div>
								</div>
								<div
									class="col-lg-6 d-flex align-items-center gradient-custom-2">
									<div class="text-white px-3 py-4 p-md-5 mx-md-4">
										<h4 class="mb-4">Estamos viendo como crear/instalar
											funciones de inicio de sesión</h4>
										<p class="small mb-0">${descripcion}. Lorem ipsum
											dolor sit amet, consectetur adipisicing elit, sed do eiusmod
											tempor incididunt ut labore et dolore magna aliqua. Ut enim
											ad minim veniam, quis nostrud exercitation ullamco laboris
											nisi ut aliquip ex ea commodo consequat.
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>	
</body>
</html>