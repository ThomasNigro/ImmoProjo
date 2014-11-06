

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datarepositories.database;

public class RechAppartParNumServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		
		PrintWriter out = response.getWriter();
		
		int numRech = Integer.parseInt(request.getParameter("numRech"));

		String requete ="SELECT * FROM APPARTEMENTS WHERE id="+numRech;
		if(database.query(requete) != null){
			out.println("l'appart existe");
			out.println(database.query(requete));
		}
	}
}
