package com.ilhaDasRabanadas.dao;

import org.mindrot.jbcrypt.BCrypt;

import com.ilhaDasRabanadas.bean.Login;

public class LoginDao extends Conexao {
	public static void insert(Login login) {
		try {
			open();
			String sql = "INSERT INTO `login`(`login`, `senha`)" + " VALUES (?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, login.getEmail());
			stmt.setString(2, login.getPassword());
			stmt.execute();

		} catch (Exception e) {
			System.out.println("erro inserção");
		}

	}

	public static Login getLogin(String email) {
		Login login = new Login();
		try {
			open();
			String sql = "SELECT * FROM `login` WHERE login=(?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			while (rs.next()) {
				login.setIdLogin(rs.getInt("idLogin"));

				login.setEmail(rs.getString("login"));
				login.setPassword(rs.getString("senha"));

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return login;

	}

	public static int Authentication(Login login) {

		int id = 0;

		try {
			open();
			String sql = "SELECT * FROM `login` WHERE login=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, login.getEmail());

			rs = stmt.executeQuery();

			while (rs.next()) {
				String tempPassword = rs.getString("senha");
				boolean teste = BCrypt.checkpw(login.getPassword(), tempPassword);

				if (teste) {
					id = rs.getInt("idLogin");
				}
			}

		} catch (Exception e) {
			// TODO: ha System.out.println("erro no servlet ><");ndle exception
			System.out.println(e.getMessage() + "sss");
		}
		return id;

	}

}
