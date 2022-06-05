<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />

<title>Modifica Pizza</title>
</head>
<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>


	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">
			<c:set var="cliente" value="${clienteEdit}"/>
			<div
				class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }"
				role="alert">
				${errorMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>

			<div class='card'>
				<div class='card-header'>
					<h5>Inserisci nuovo cliente</h5>
				</div>
				<div class='card-body'>

				


					<form method="post" action="ExecuteUpdateClienteServlet"
						class="row g-3" novalidate="novalidate">


						<div class="col-md-6">
							<label for="nome" class="form-label">Nome</label> <input
								type="text" name="nome" id="nome" class="form-control"
								placeholder="Descrizione" value="${cliente.nome}">
						</div>
						<div class="col-md-6">
							<label for="cognome" class="form-label">Cognome</label> <input
								type="text" name="cognome" id="cognome" class="form-control"
								placeholder="cognome" value="${cliente.cognome}">
						</div>
						
						<div class="col-md-6">
							<label for="indirizzo" class="form-label">Indirizzo</label> <input
								type="text" name="indirizzo" id="indirizzo" class="form-control"
								placeholder="0" value="${cliente.indirizzo}">
						</div>

						
						<input type="hidden"  name="id" value="${cliente.id}"> 

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