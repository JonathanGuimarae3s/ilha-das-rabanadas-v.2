<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="com.ilhaDasRabanadas.dao.*"%>
<%@page import="com.ilhaDasRabanadas.bean.*"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="javax.xml.crypto.Data"%>




<%@ page import="java.util.Date,java.text.SimpleDateFormat"%>





<!doctype html>
<html lang="pt-br">

<head>
<title>Ilha das rabanadas</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<script src="https://cdn.lordicon.com/ritcuqlt.js"></script>
<!-- Bootstrap CSS v5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
	crossorigin="anonymous">


<link rel="stylesheet" href="../public/css/style.css">
<link rel="stylesheet" href="../public/css/carrinho/carrinho.css">
<link rel="stylesheet" href="../public/css/carrinho/carrinho-tablet.css">
<link rel="stylesheet" href="../public/css/carrinho/carrinho-mobile.css">


</head>

<body>


	<%
	Integer id = (Integer) session.getAttribute("id");
	Cliente cliente = new Cliente();

	String produtoId = request.getParameter("idProduto");
	Date dataAtual = new Date();

    // Criando o formato desejado
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    // Formatando a data
    String dataFormatada = formato.format(dataAtual);
  request.setAttribute("data", dataFormatada);

	if (id == null || id == 1) {
		request.setAttribute("msg", "� necessario estar logado como cliente para efetuar a compra!");
		response.sendRedirect("../Login/login.jsp");

	} else {

		if (produtoId == null && id != null) {

			cliente = ClienteDao.getElementByIdLogin(id);
			Carrinho carrinho = CarrinhoDao.getMyProducts(cliente.getIdCliente());
			request.setAttribute("cliente", cliente);

			Produto produto = ProdutoDao.getElementById(Integer.toString(carrinho.getIdProduto()));
			request.setAttribute("produto", produto);

		} else {
			cliente = ClienteDao.getElementByIdLogin(id);
			request.setAttribute("cliente", cliente);

			Produto produto = ProdutoDao.getElementById(produtoId);
			request.setAttribute("produto", produto);
			CarrinhoDao.insert(produto.getIdProduto(), cliente.getIdCliente());
		}

	}
	%>

	<!-- Bootstrap JavaScript Libraries -->

	<jsp:include page="../Headers/headerCliente.jsp"></jsp:include>


	<main class="my-5">

		<h1 class="text-center">Seu carrinho</h1>


		<c:if test="${produto.getIdProduto()== 0}">

			<div class="text-center">
				<img src="../public/imgs/pedidos/naoHaPedidos.webp" alt="" width="100%"></img>
				<h4>Seu carrinho est� vazio!</h4>
			</div>
		</c:if>
		<c:if test="${produto.getIdProduto()!= 0}">

			<section class="container  flex-column p-4">
				<form action="../PedidoInsertServlet" method="POST">
					<input type="hidden" name="idCliente"
						value="${cliente.getIdCliente()}"> <input type="hidden"
						name="nomeProduto" value="${produto.getNomeProduto()}">
					<div
						class="card d-flex flex-sm-column flex-lg-row flex-nowrap flex-sm-wrap align-items-center mb-3">

						<div id="imgProduto">
							<img src="${produto.getImagem()}" class="" alt="..."
								height="100%">
						</div>
						<div class="">
							<div class="card-body">
								<h5 class="card-title">${produto.getNomeProduto()}</h5>
								<p class="card-text">
									Valor:<span class="fw-bold" id="preco">${produto.getPreco()}</span>
								<div class="mb-3">
									<label class=" mb-2 form-label"> <label>Quantidade</label>
										<input type="number" name="quantidadePedido" value="1"
										id="quantidadePedido" min="1" class="form-control"
										placeholder="" aria-describedby="helpId">
									</label>
								</div>
								<div>

									<p>Total:</p>
									<input type="text" value="R$5,00" id="valorPedido"
										class="border-0" name="valorPedido" readonly>

								</div>
							</div>
						</div>

					</div>


					<div class="card   mb-3">
						<div class="row g-0">
							<div class="col">
								<div class="card-body">
									<div class="mb-3">
										<label for="" class=" mb-2 form-label"> EndereÃ§o de
											entrega: </label> <input type="text" name="endereco"
											value="${cliente.getEndereco()}" id="endereco"
											class="form-control" placeholder="" aria-describedby="helpId"
											required>
									</div>
									<div class="mb-3">
									<label class=" mb-2 form-label" for="">Data
											de entrega</label> <input type="date" min='${data}'
											name="dataEntrega" id="dataEntrega" class="form-control"
											required>
									</div>
									<div class="mb-3">
										<label class=" mb-2 form-label" for="hora da entrega">Hora
											da entrega</label> <input type="time" id="" min="08:00" max="20:00"
											name="hora" class="form-control" required>
									</div>
									<div>
										<p>Forma de Pagamento</p>
										<div class="form-check">
											<input class="form-check-input" type="radio" value="cartao"
												name="flexRadioDefault" id="flexRadioDefault1"> <label
												class="form-check-label" for="flexRadioDefault1">
												CartÃ£o ou pix </label>
										</div>
										<div class="form-check">
											<input class="form-check-input" type="radio" value="dinheiro"
												name="flexRadioDefault" id="flexRadioDefault2" checked>
											<label class="form-check-label" for="flexRadioDefault2">
												Dinheiro </label>
										</div>
									</div>
									<div id="boxTroco">
										<p>PrecisarÃ¡ de troco? Para quanto?</p>
										<input type="text" value="" name="troco" class="form-control">
									</div>
								</div>

							</div>
						</div>
					</div>
					<div class="d-flex " id="buttons">
						<a href="../0"><button type="button" class="btn btn-secondary">Cancelar
								encomenda</button></a> <input type="submit" class=" btn btn-success"
							value="Fechar encomenda">
					</div>
				</form>

			</section>

		</c:if>





	</main>




	<script src="../public/js/minhasCompras.js">
		
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
		integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
		crossorigin="anonymous">
		
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
		integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
		crossorigin="anonymous">
		
	</script>
</body>

</html>
