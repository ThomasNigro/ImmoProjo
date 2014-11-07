import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datarepositories.database;
import objects.Account;
import objects.Apartment;
import objects.Type;


public class MiseEnVenteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		HttpSession laSession = request.getSession(true);
		Account proprio = (Account) laSession.getAttribute("Account");
		String adresse = request.getParameter("adresse");
		Double prix = Double.parseDouble(request.getParameter("prix"));
		String desc = request.getParameter("desc");
		String RBtype = request.getParameter("type");
		Type type;
		switch (RBtype){
			case "Studio": type=  Type.STUDIO;
			case "T1" : type = Type.T1;
			case "T2" : type = Type.T2;
			case "T3" : type = Type.T3;
			default : type= null;
		}
		Apartment appart = new Apartment(type, adresse, desc, prix);
		database.addApartment(appart, proprio.getId());
		
	}
}
