package com.ilhaDasRabanadas.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ilhaDasRabanadas.bean.Cliente;
import com.ilhaDasRabanadas.bean.Login;
import com.ilhaDasRabanadas.dao.LoginDao;

/**
 * Servlet implementation class LoginAuthentication
 */
@WebServlet("/LoginAuthentication")
public class LoginAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAuthentication() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		// Obtém o nome de usuário e senha enviados pelo formulário
		String email = request.getParameter("email");
		String password = request.getParameter("senha");
		// ====
		Login login = new Login();
		login.setEmail(email);
		login.setPassword(password);
		// ====
		LoginDao loginDao = new LoginDao();

		try {

			int id = LoginDao.Authentication(login);
			if (id == 1) {
				session.removeAttribute("id");
				response.sendRedirect("./Adm/Dashboard.jsp");

				session.setAttribute("boasVindas", "Bem-vindo de volta");
				session.setAttribute("id", id);

			}
			if (id > 1) {
				session.removeAttribute("id");
				session.setAttribute("boasVindas", "Bem-vindo de volta");
				response.sendRedirect("./Cliente/Dashboard.jsp");
				session.setAttribute("id", id);
			}
			response.sendRedirect("./Login/login.jsp");
			session.setAttribute("msg", "Email ou senha invalido!!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
