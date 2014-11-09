import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import objects.Account;
import objects.Apartment;

//quand le proprio click sur déclarer vendu
public class DeclarerVenduServlet extends HttpServlet {
	//j'ajoute l'id de l'appart sur lequelle le proprio a cliquer pour pouvoir enregistrer sont prix de vente
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession laSession = request.getSession(true);
		Account compte = (Account) laSession.getAttribute("Account");
		
		int idAppart= Integer.parseInt(request.getParameter("idAppart"));
		
		laSession.setAttribute("IdAppartVendu", idAppart);
	}

}
