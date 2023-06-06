package com.ilhaDasRabanadas.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Pedido {
	protected int idPedido;
	protected int idCliente;
	public String nomeProduto;
	public String dataEntrega;
	public String hora;
	public String valorPedido;
	public String quantidadePedido;
	public String endereco;
	public String troco;
	public String formaPagamento;

	public String getTroco() {
		return troco;
	}

	public void setTroco(String troco) {
		this.troco = troco;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataProduto) {
		this.dataEntrega = dataProduto;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(String valorPedido) {
		this.valorPedido = valorPedido;
	}

	public String getQuantidadePedido() {
		return quantidadePedido;
	}

	public void setQuantidadePedido(String quantidadePedido) {
		this.quantidadePedido = quantidadePedido;
	}

	public String getDataFormatada() {
		String dataString = getDataEntrega();

		// Define o padrão de formatação do formulário (formato internacional)
		SimpleDateFormat formatoFormulario = new SimpleDateFormat("yyyy-MM-dd");

		try {
			// Converte a string em um objeto Date no formato internacional
			Date data = formatoFormulario.parse(dataString);

			// Define o padrão de formatação desejado (formato brasileiro)
			SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");

			// Formata a data conforme o padrão brasileiro
			 dataString = formatoBrasileiro.format(data);

			// Imprime a data formatada no formato brasileiro
			
		} catch (ParseException e) {
			System.out.println("Formato de data inválido.");
		}
		return dataString;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
