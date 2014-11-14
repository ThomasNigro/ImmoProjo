import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
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
		
		
		try {
			PrintWriter out = response.getWriter();
			String id= (String) request.getAttribute("idAppart");
			System.out.println(id);
			request.setAttribute("idAppart", id);
			request.getRequestDispatcher("vendu.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
