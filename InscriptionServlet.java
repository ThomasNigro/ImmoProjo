

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datarepositories.Database;
import objects.Account;

public class InscriptionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession laSession = request.getSession(true);
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		String req="SELECT * FROM PROPRIETAIRES WHERE Login= '"+login+"' ;";
		ResultSet rs =Database.query(req);
		try {
			if(rs.next()){
				String err = "Désolé ce login est déja pris";
				request.setAttribute("Error", err);
				request.getRequestDispatcher("inscription.jsp").forward(request, response);
			}
			else{
				Account account = new Account(name, login, password);
				laSession.setAttribute("Account", account);
				Database.addCompte(account);
				request.getRequestDispatcher("vosApparts.jsp").forward(request, response);
			}
		} catch (SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
