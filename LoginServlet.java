

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datarepositories.database;
import objects.Account;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		PrintWriter out = response.getWriter();
		
		HttpSession laSession = request.getSession(true);
		Account account = (Account) laSession.getAttribute("Account");
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		//if(account.getLogin()!=login || account.getPassword() != password)
		String requete ="SELECT * FROM PROPRIETAIRES WHERE login="+login
				+"AND password="+password;
		if(database.query(requete) != null){
			out.println("Compte OK");
			//est qu'il faut creer un proprio par session ? 
			//comment accéder un proprio existant ?
		}
	}
}
