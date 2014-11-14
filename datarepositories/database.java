package datarepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import objects.Account;
import objects.Type;
import objects.Apartment;


public class Database
{
	public static  String DBPath = "Chemin aux base de donnée SQLite";
	private static Connection connection = null;
	/*private static ArrayList<Statement> stats = new ArrayList<Statement>();
	static {
		try {
			Statement stat= connection.createStatement();
			stats.add(stat);
			
		} catch (SQLException e) {
			// problème, on arrête le serveur
			System.out.println(e);
			System.exit(1);
		}
	}*/


	public Database(String dBPath)
	{
		Database.connect(dBPath);
		Database.createTables();
	}

	public static void connect(String dBPath)
	{
		DBPath=dBPath;
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
			//Statement statement = connection.createStatement();
			System.out.println("Connexion a " + DBPath + " avec succès");
		} catch (ClassNotFoundException notFoundException)
		{
			notFoundException.printStackTrace();
			System.out.println("Erreur de connexion");
		} catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
			System.out.println("Erreur de connexion");
		}
	}

	public static void createTables()
	{
		String ownersStatement = "";
		ownersStatement += "CREATE TABLE PROPRIETAIRES ( ";
		ownersStatement += "Nom TEXT NOT NULL, ";
		ownersStatement += "Login TEXT UNIQUE, ";
		ownersStatement += "Mdp TEXT NOT NULL ";
		ownersStatement += ");";

		String apartmentsStatement = "";
		apartmentsStatement += "CREATE TABLE APPARTEMENTS ( ";
		apartmentsStatement += "Id INTEGER PRIMARY KEY AUTOINCREMENT, ";
		apartmentsStatement += "OwnerId TEXT NOT NULL, ";
		apartmentsStatement += "Description TEXT, ";
		apartmentsStatement += "Price REAL NOT NULL, ";
		apartmentsStatement += "SoldPrice REAL, ";
		apartmentsStatement += "IsSold INTEGER, ";
		apartmentsStatement += "Adress CHAR(50) NOT NULL,";
		apartmentsStatement += "Type INTEGER NOT NULL ";
		apartmentsStatement += ");";
		try{
			Statement ownStat = connection.createStatement();
			synchronized(ownStat){
				ownStat.executeUpdate(ownersStatement);
				ownStat.close();
			}

			Statement flatStat = connection.createStatement();
			synchronized(flatStat){
				flatStat.executeUpdate(apartmentsStatement);
				System.out.println("table appart créé");
				flatStat.close();
			}
		} catch (SQLException e){
			System.out.println("Erreur creation de table");
		}
	}

	public static ResultSet query(String request)
	{
		ResultSet resultat = null;
		try
		{
			Statement statement = connection.createStatement();
			synchronized(statement){
				resultat = statement.executeQuery(request);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Erreur dans la requete : " + request);
		}
		return resultat;
	}
	
	public static void update(String update){
		try
		{
			Statement statement = connection.createStatement();
			synchronized(statement){
				statement.executeUpdate(update);
				statement.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Erreur dans l'update: " + update);
		}
	}

	public static void delApartment(int idAppart)
	{
		String remQuery = "DELETE FROM APPARTEMENTS WHERE Id=\'" + idAppart + "\';";
		Database.update(remQuery);
	}

	public static void addApartment(Apartment appart)
	{
		String req = "INSERT INTO APPARTEMENTS (Type, Adress, Price, Description, IsSold, OwnerId) VALUES ('";
		req += Integer.toString(appart.getType().ordinal()) + "', '";
		req += appart.getAddress() + "' ,'";
		req += Double.toString(appart.getPrice())+"', '";
		req += appart.getDesc()+"', '";
		int isSold = (appart.isSold()) ? 1 : 0;
		req += isSold +"', '";
		req += appart.getIdProprio()+"') ;";
		update(req);
	}

	public static ArrayList<Apartment> getApartmentByOwner(String ownerLogin)
	{
		String getQuery = "SELECT * FROM APPARTEMENTS WHERE OwnerId = \'"+ ownerLogin+ "\';";
		ResultSet rs = query(getQuery);
		ArrayList<Apartment> apparts= new ArrayList<Apartment>();
		try {
			while(rs.next())
			{
				
		         int id = rs.getInt("Id");
		         String desc = rs.getString("Description");
		         double price = rs.getDouble("Price");
		         double soldPrice = rs.getDouble("SoldPrice");
		         int sold = rs.getInt("IsSold");
		         boolean isSold =false;
		         if(sold==0) 
		        	 isSold=false ;
		         else 
		        	 isSold=true;
		         String adress = rs.getString("Adress");
		         int t= rs.getInt("Type");
		         Type type = Type.values()[t];
		         Apartment appart = new Apartment(type, id, ownerLogin, desc, price, soldPrice, isSold, adress);
		         apparts.add(appart);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return apparts;
	}

	public void close()
	{
		try
		{
			connection.close();
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Apartment getAppart(int idAppart) {
		// TODO Auto-generated method stub
		
		try {
			Statement stmt = connection.createStatement();
			synchronized (stmt){
				String getQuery = "SELECT * FROM APPARTEMENTS WHERE Id = \'"+ idAppart + "\';";
				ResultSet rs;
				rs = stmt.executeQuery(getQuery);
				if(rs!=null){
			         String idOwner = rs.getString("OwnerId");
			         String desc = rs.getString("Description");
			         double price = rs.getDouble("Price");
			         double soldPrice = rs.getDouble("SoldPrice");
			         int sold = rs.getInt("IsSold");
			         boolean isSold =false;
			         if(sold==0) 
			        	 isSold=false ;
			         else 
			        	 isSold=true;
			         String adress = rs.getString("Adress");
			         int t = rs.getInt("Type");
			         Type type = Type.values()[t];
			         Apartment appart = new Apartment(type, idAppart, idOwner, desc, price, soldPrice, isSold, adress);
			         stmt.close();
			         return appart;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void updateSoldAppart(Double prix, int idAppart) {
			String req1 = "UPDATE APPARTEMENTS SET SoldPrice = '";
			req1 += prix.toString();
			req1 += "' WHERE id= '"+idAppart+"';";
			System.out.println(req1);
			String req2 = "UPDATE APPARTEMENTS SET IsSold = '1'";
			req2 += " WHERE id= '"+idAppart+"';";
			update(req1);
			update(req2);
			System.out.println("Mise a jour prixVente OK");
	}
	
	private static void addProprio(String name, String login, String password){
		try
		{
			Statement statement = connection.createStatement();
			synchronized(statement){
				String update = "INSERT INTO PROPRIETAIRES (Nom, Login, Mdp) VALUES ('"+ name +"' ,'"+login+"' ,'"+password+"');";
				statement.executeUpdate(update);
				statement.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}	
	}

	public static void addCompte(Account account) {
		addProprio(account.getName(), account.getLogin(), account.getPassword());
	}
	
	public static ArrayList<Apartment> getAppartsByType(Type t){
		String getQuery = "SELECT * FROM APPARTEMENTS WHERE NOT IsSold AND Type= "+Integer.toString(t.ordinal())+";";
		ResultSet rs = query(getQuery);
		ArrayList<Apartment> apparts= new ArrayList<Apartment>();
		try {
			while(rs.next()){
				 int idAppart=rs.getInt("Id");
		         String idOwner = rs.getString("OwnerId");
		         String desc = rs.getString("Description");
		         double price = rs.getDouble("Price");
		         double soldPrice = rs.getDouble("SoldPrice");
		         int sold = rs.getInt("IsSold");
		         boolean isSold =false;
		         if(sold==0) 
		        	 isSold=false ;
		         else 
		        	 isSold=true;
		         String adress = rs.getString("Adress");
		         Apartment appart = new Apartment(t, idAppart, idOwner, desc, price, soldPrice, isSold, adress);
		         apparts.add(appart);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apparts;
	}
}
