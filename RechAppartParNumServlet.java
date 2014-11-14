

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datarepositories.Database;

public class RechAppartParNumServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		PrintWriter out = response.getWriter();
		int numRech = Integer.parseInt(request.getParameter("numRech"));

		String requete ="SELECT * FROM APPARTEMENTS WHERE id="+numRech;
		if(Database.query(requete) != null){
			out.println("l'appart existe");
			out.println(Database.query(requete));
		}
		else{
			out.println("Id Appart inconnu");
		}*/
		int numRech = Integer.parseInt(request.getParameter("numRech"));
		request.setAttribute("idAppart", numRech);
		request.getRequestDispatcher("annonce.jsp").forward(request, response);
	}
}
