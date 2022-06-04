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
	<jsp:include page="../pizzaiolo_navbar.jsp"></jsp:include>


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
								placeholder="Inserire il codice" value="${ordine.codice }">
						</div>



						<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date'
							value='${ordine.data}' />
						<div class="col-md-6">
							<label for="dataPubblicazione" class="form-label">Data</label> <input
								class="form-control" id="data" type="date"
								placeholder="dd/MM/yy" title="formato : gg/mm/aaaa" name="data"
								value="${parsedDate}">
						</div>

						<div class="col-md-6">
							<label for="ordine.id">Cliente</label> <select
								class="form-select" id="ordine.id" name="ordine.id">
								<option value="" selected>-- Selezionare un cliente --
								</option>
								<c:forEach items="${clienti_attribute}" var="clienteItem">
									<option value="${clienteItem.id}"
										${ordine.cliente.id == ordineItem.id?'selected':''}>${clienteItem.nome}
										${clienteItem.cognome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6" >
							<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								id="inlineCheckbox1" value="option1" /> <label
								class="form-check-label" for="inlineCheckbox1">Margherita</label>
						</div>

						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								id="inlineCheckbox2" value="option2" /> <label
								class="form-check-label" for="inlineCheckbox2">Diavola</label>
						</div>

						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox"
								id="inlineCheckbox3" value="option3"  /> <label
								class="form-check-label" for="inlineCheckbox3">Boscaiola</label>
						</div>
						</div>



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