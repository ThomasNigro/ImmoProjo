

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String requete ="SELECT * FROM PROPRIETAIRES WHERE login="+login
				+"AND password="+password;
		ResultSet res=database.query(requete);
		try {
			if(res != null || res.getString(1).isEmpty()){
				String name = res.getString("name");
				Account account= new Account (name, login, password);
				laSession.setAttribute("Account", account);
				out.println("Compte OK");
			}
			else{
				out.println("Login inconnu ou Password incorrect");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
