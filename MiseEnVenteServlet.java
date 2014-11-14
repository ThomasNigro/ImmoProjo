import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datarepositories.Database;
import objects.Account;
import objects.Apartment;
import objects.Type;


public class MiseEnVenteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession laSession = request.getSession(true);
		Account proprio = (Account) laSession.getAttribute("Account");
		String adresse = request.getParameter("adresse");
		Double prix = Double.parseDouble(request.getParameter("prix"));
		
		String desc = request.getParameter("desc");
		String RBtype = request.getParameter("type");
		Type type = null;
		if (RBtype.equals("Studio"))
			type=  Type.STUDIO;
		else if(RBtype.equals("T1"))
				type = Type.T1;
		else if(RBtype.equals("T2"))
				type = Type.T2;
		else if(RBtype.equals("T3"))
				type = Type.T3;
		
		Apartment appart = new Apartment(type, proprio.getId(), desc,  prix,  adresse);
		Database.addApartment(appart);
		request.getRequestDispatcher("vosApparts.jsp").forward(request, response);
	}
}
