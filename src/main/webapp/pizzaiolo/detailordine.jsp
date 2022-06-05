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



				<div class='card-body'>
					<dl class="row">
						<dt class="col-sm-3 text-right">Id</dt>
						<dd class="col-sm-9">
							<c:out value="${ordineDetail.id}" />
						</dd>
					</dl>

					<dl class="row">
						<dt class="col-sm-3 text-right">Codice:</dt>
						<dd class="col-sm-9">
							<c:out value="${ordineDetail.codice}" />
						</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Prezzo totale:</dt>
						<dd class="col-sm-9">
							<c:out value="${ordineDetail.prezzoTotale}" />
						</dd>
					</dl>
					<dl class="row">
						<dt class="col-sm-3 text-right">Utente:</dt>
						<dd class="col-sm-9">
							<c:out value="${ordineDetail.utente.nome}" />
							<c:out value="${ordineDetail.utente.cognome}" />
						</dd>
					</dl>
					
					<dl class="row">
						<dt class="col-sm-3 text-right">Pizze:</dt>
						<dd class="col-sm-9">
							<c:forEach items="${ordineDetail.pizze}" var="pizzaItem">	 
							<c:out value="${pizzaItem.descrizione}" />
							</c:forEach>
						</dd>
					</dl>

				</div>

				<div class='card-footer'>
					<a href="ExecuteListOrdineServlet" class='btn btn-outline-dark'
						style='width: 100px'> <i class='fa fa-chevron-left'></i> Indietro
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