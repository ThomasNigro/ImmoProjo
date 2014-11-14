

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datarepositories.Database;
import objects.Account;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException{
		PrintWriter out = response.getWriter();
		
		HttpSession laSession = request.getSession(true);
		//Cookie cookieLog= request.getCookies();
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String requete ="SELECT * FROM PROPRIETAIRES WHERE Login= '"+login+"' AND Mdp= '"+password+"' ;";
		//out.println(requete);
		ResultSet res = Database.query(requete);
		
		//out.println(res);
		try {	
			if(res.next()){ 
				out.println("ok");
				String name = res.getString("Nom");
				out.println(name);
				Account account= new Account (name, login, password);
				laSession.setAttribute("Account", account);
				//Cookie cookieLogin= new Cookie("Login", login);
				//request.addCookie(cookieLogin);
				out.println("Compte OK");
				request.getRequestDispatcher("vosApparts.jsp").forward(request, response);
			}
			else{
				String err="Login inconnu ou Password incorrect";
				request.setAttribute("Error", err);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
