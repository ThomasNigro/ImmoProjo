import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datarepositories.Database;

//quand le proprio valide sont prix de vente
public class DeclarerPrixVenteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession laSession = request.getSession(true);
			Account compte = (Account) laSession.getAttribute("Account");
			if(compte != null){
				int idAppart = Integer.parseInt((String) laSession.getAttribute("idAppart"));
				Double prixVente = Double.parseDouble(request.getParameter("prixVente"));
				System.out.println(prixVente);
				Database.updateSoldAppart(prixVente, idAppart);
				request.getRequestDispatcher("vosApparts.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
