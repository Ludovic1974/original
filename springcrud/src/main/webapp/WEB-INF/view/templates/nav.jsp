<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/index">Java SpringCrud</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a	href="${pageContext.request.contextPath}/index" class="nav-link ${menu == 'index' ? 'active' : ''}">Portada</a></li>
				<li class="nav-item"><a	href="${pageContext.request.contextPath}/book/list"	class="nav-link ${menu == 'lista_libros' ? 'active' : ''}">Gestionar libros</a></li>
				<li class="nav-item"><a	href="${pageContext.request.contextPath}/category/list"	class="nav-link ${menu == 'lista_categorias' ? 'active' : ''}">Gestionar categorías</a></li>
				<li class="nav-item"><a	href="${pageContext.request.contextPath}/user/list"	class="nav-link ${menu == 'lista_usuarios' ? 'active' : ''}"">Gestionar	usuarios</a></li>
			</ul>
			<form:form action="#" class="d-flex" method="POST">
				<input type="submit" class="btn btn-outline-danger"
					value="Cerrar sesión" />
			</form:form>
		</div>
	</div>
</nav>

