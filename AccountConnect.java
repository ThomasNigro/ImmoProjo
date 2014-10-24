import objects.Account;
import objects.Type;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountConnect extends HttpServlet{
	static final long serialVersionUID=1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		HttpSession laSession = request.getSession(true);
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		
		Account leUser= Account.get(login, password);
		laSession.setAttribute("user", leUser);
		out.println("Authentification Enregistrée");
		out.println("<a href = \"verification\"> Cliquer ici pour verifier </a>");
	}
	
}
