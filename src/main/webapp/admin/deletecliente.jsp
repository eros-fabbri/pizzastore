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
					<h5>Conferma eliminazione</h5>
				</div>

				<div class='card-body'>
					<dl class="row">
						<dt class="col-sm-3 text-right">Id:</dt>
						<dd class="col-sm-9">
							<c:out value="${clienteDelete.id}" />
						</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Nome:</dt>
						<dd class="col-sm-9">
							<c:out value="${clienteDelete.nome}" />
						</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Cognome:</dt>
						<dd class="col-sm-9">
							<c:out value="${clienteDelete.cognome}" />
						</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">indirzzo:</dt>
						<dd class="col-sm-9">
							<c:out value="${clienteDelete.indirizzo}" />
						</dd>
					</dl>

				</div>
				
				<div class='card-footer'>
					<a href="ExecuteListClientiServlet" class='btn btn-outline-dark'
						style='width: 100px'> <i class='fa fa-chevron-left'></i> Indietro
					</a>
					<a href="ExecuteDeleteClientiServlet?idCliente=${clienteDelete.id}" class='btn btn-outline-danger'
						style='width: 100px'> <i class='fa fa-chevron-left'></i> Conferma
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