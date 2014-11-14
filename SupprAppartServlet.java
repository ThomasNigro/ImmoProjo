import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datarepositories.Database;
import objects.Account;


public class SupprAppartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int idAppart = Integer.parseInt(request.getParameter("idAppart"));
		HttpSession laSession = request.getSession(true);
		Account compte = (Account) laSession.getAttribute("Account");
		if(compte != null){
			Database.delApartment(idAppart);
			request.getRequestDispatcher("vosApparts.jsp").forward(request, response);
		}
	}
}
