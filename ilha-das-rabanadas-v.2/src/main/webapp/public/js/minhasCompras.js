const inputQuantidade = document.querySelector("#quantidadePedido");
const precoElemento = document.querySelector("#preco");
const inputValorTotal = document.querySelector("#valorPedido");
inputQuantidade.addEventListener("click", () => {

	let quantidade = inputQuantidade.value;
	let preco = precoElemento.textContent;
	preco = preco.replace("R$", "");
	preco = preco.replace(",", ".");

	let valorTotalAtual = (Number(preco) * quantidade).toLocaleString("pt-br", {
		style: "currency",
		currency: "BRL",
	});
	inputValorTotal.value = valorTotalAtual

});
//=========================forma de pagamento=======================
let inputRadio = document.getElementsByName('flexRadioDefault')
let boxTroco = document.getElementById('boxTroco');

inputRadio.forEach(radios => {

	radios.addEventListener('click', () => {
		boxTroco.style.visibility = '';

		let radiosValue = radios.value;


		if (radiosValue == "cartao") {


			boxTroco.style.visibility = 'hidden';

		} else {

			boxTroco.style.visibility = 'visible';
		}




	})
});


