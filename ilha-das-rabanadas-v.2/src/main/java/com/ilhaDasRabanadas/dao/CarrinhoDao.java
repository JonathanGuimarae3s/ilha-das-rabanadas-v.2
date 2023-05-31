package com.ilhaDasRabanadas.dao;

import java.sql.SQLException;

import com.ilhaDasRabanadas.bean.Carrinho;

public class CarrinhoDao extends Conexao {

	public static Carrinho getMyProducts(int idCliente) {

		String clienteId = Integer.toString(idCliente);
		// pegar pelo idLogin
		Carrinho carrinho = new Carrinho();

		try {
			open();

			String sql = "SELECT * FROM `carrinho` WHERE idCliente=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, clienteId);
			rs = stmt.executeQuery();

			while (rs.next()) {

				carrinho.setIdProduto(Integer.parseInt(rs.getString("idProduto")));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return carrinho;
	}

	public static void insert(int idProduto, int idCliente) {
		String stringIdCliente = Integer.toString(idCliente);
		String stringIdProduto = Integer.toString(idProduto);
		try {
			open();
			String sql = "INSERT INTO `carrinho`(idProduto, idCliente) VALUES (?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, stringIdProduto);
			stmt.setString(2, stringIdCliente);
			stmt.execute();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(stringIdProduto);
			System.out.println(stringIdCliente);
		}
	}

}
