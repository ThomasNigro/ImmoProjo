import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Account;

//quand le proprio valide sont prix de vente
public class DeclarerPrixVenteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession laSession = request.getSession(true);
		
		int idAppart = (int) laSession.getAttribute("IdAppartVendu");
		Double prixVente = Double.parseDouble(request.getParameter("prixVente"));
		
		database.updateSoldAppart(prixVente, idAppart);
	}
	
}
