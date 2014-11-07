import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datarepositories.database;
import objects.Account;


public class SupprAppartServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		HttpSession laSession = request.getSession(true);
		Account proprio = (Account) laSession.getAttribute("Account");
		int idAppart = Integer.parseInt(request.getParameter("numSupp"));
		database.delApartment(idAppart, proprio.getId());
	}
}
