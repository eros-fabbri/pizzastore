<!doctype html>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />

<title>Visualizza Elemento</title>
</head>
<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div class='card'>
				<div class='card-header'>
					<h5>Visualizza dettaglio</h5>
				</div>
				<c:set var="pizza" value="${pizzaDelete}" />



				<div class='card-body'>
					<dl class="row">
						<dt class="col-sm-3 text-right">Id</dt>
						<dd class="col-sm-9">
							<c:out value="${pizzaDelete.id}" />
						</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Ingredienti:</dt>
						<dd class="col-sm-9">
							<c:out value="${pizzaDelete.listaIngredienti}" />
						</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Descrizione:</dt>
						<dd class="col-sm-9">
							<c:out value="${pizzaDelete.descrizione}" />
						</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Prezzo:</dt>
						<dd class="col-sm-9">
							<c:out value="${pizzaDelete.prezzoBase}" />
						</dd>
					</dl>

				</div>

				<div class='card-footer'>
					<a href="ExecuteListPizzeServlet" class='btn btn-outline-dark'
						style='width: 100px'> <i class='fa fa-chevron-left'></i> Indietro
					</a>
					<a href="ExecuteDeletePizzeServlet?idPizza=<c:out value="${pizzaDelete.id}"/>" class='btn btn-outline-danger'
						style='width: 100px'> <i class='fa fa-chevron-left'></i> Elimina
					</a>
				</div>
				
				<!-- end card -->
			</div>


			<!-- end container -->
		</div>

	</main>

	<!-- Footer -->
	<jsp:include page="../footer.jsp" />
</body>
</html>