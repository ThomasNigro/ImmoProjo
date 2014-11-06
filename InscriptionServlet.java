

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Account;

public class InscriptionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession laSession = request.getSession(true);
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		Account account = new Account(name, login, password);
		laSession.setAttribute("account", account);
	}
}
