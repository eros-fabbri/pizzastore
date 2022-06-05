<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />

<title>Inserisci Nuovo Ordine</title>
</head>
<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div
				class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }"
				role="alert">
				${errorMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>

			<div class='card'>
				<div class='card-header'>
					<h5>Inserisci nuovo ordine</h5>
				</div>
				<div class='card-body'>

					<h6 class="card-title">
						I campi con <span class="text-danger">*</span> sono obbligatori
					</h6>


					<form method="post" action="ExecuteInsertOrdineServlet"
						class="row g-3" novalidate="novalidate">


						<div class="col-md-6">
							<label for="codice" class="form-label">Codice</label> <input
								type="text" name="codice" id="codice" class="form-control"
								placeholder="Inserire il codice" value="${ordine.codice}">
						</div>

						<div class="col-md-6">
							<label for="idcliente">Cliente</label> <select
								class="form-select" id="idcliente" name="idcliente">
								<option value="" selected>-- Selezionare un cliente --
								</option>
								<c:forEach items="${clienti_attribute}" var="clienteItem">
									<option value="${clienteItem.id}"
										${ordine.cliente.id == ordineItem.id?'selected':''}>${clienteItem.nome}
										${clienteItem.cognome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6">
							<label for="idutente">Utente</label> <select class="form-select"
								id="idutente" name="idutente">
								<option value="" selected>-- Selezionare un utente --</option>
								<c:forEach items="${utenti_attribute}" var="utenteItem">
									<option value="${utenteItem.id}"
										${ordine.utente.id == ordineItem.id?'selected':''}>${utenteItem.nome}
										${utenteItem.cognome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6">
							<label for="data" class="form-label">Data</label> <input
								class="form-control" id="data" type="date"
								placeholder="dd/MM/yy" title="formato : gg/mm/aaaa" name="data">
						</div>

						<c:forEach items="${pizze_attribute}" var="pizzaItem">
							<c:if test="${pizzaItem.active}">
								<div class="col-md-6">
									<c:if test="${pizze_descrizioni_attribute.contains(pizzaItem.descrizione)}">
										<div class="form-check form-check-inline">
											<input  class="form-check-input" type="checkbox"
												id="inlineCheckbox1" name="pizzascelta"
												value="<c:out value="${pizzaItem.id}"/>" /> <label
												class="form-check-label" for="inlineCheckbox1"><c:out
													value="${pizzaItem.descrizione}" /></label>
										</div>
									</c:if>
									<c:if test="${!pizze_descrizioni_attribute.contains(pizzaItem.descrizione)}">
										<div class="form-check form-check-inline">
											<input checked class="form-check-input" type="checkbox"
												id="inlineCheckbox1" name="pizzascelta"
												value="<c:out value="${pizzaItem.id}"/>" /> <label
												class="form-check-label" for="inlineCheckbox1"><c:out
													value="${pizzaItem.descrizione}" /></label>
										</div>
									</c:if>
								</div>
							</c:if>
						</c:forEach>




						<div class="col-12">
							<button type="submit" name="insertSubmit" value="insertSubmit"
								id="insertSubmit" class="btn btn-dark">Conferma</button>
						</div>

					</form>



					<!-- end card-body -->
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