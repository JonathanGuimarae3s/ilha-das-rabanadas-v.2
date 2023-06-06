
<%
Integer id = (Integer) session.getAttribute("id");

if (id == null) {
	response.sendRedirect("../Login/login.jsp");
	// Remove o atributo da sessão

} else {
	session.removeAttribute("id");
	response.sendRedirect("../Login/login.jsp");

}
%>