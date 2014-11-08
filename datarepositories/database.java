package datarepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Apartment;

public class database
{
	private String DBPath = "Chemin aux base de donnée SQLite";
	private Connection connection = null;


	public database(String dBPath)
	{
		DBPath = dBPath;
	}

	public void connect()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
			Statement statement = connection.createStatement();
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

	public void createTables()
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
		apartmentsStatement += "OldPrice REAL, ";
		apartmentsStatement += "IsSold BOOLEAN, ";
		apartmentsStatement += "Adress CHAR(50) NOT NULL,";
		apartmentsStatement += "Type INTEGER NOT NULL ";
		apartmentsStatement += ");";
		try
		{
			Statement ownStat = connection.createStatement();
			ownStat.executeUpdate(ownersStatement);
			ownStat.close();

			Statement flatStat = connection.createStatement();
			flatStat.executeUpdate(apartmentsStatement);
			flatStat.close();
		} catch (SQLException e)
		{
		}
	}

	public ResultSet query(String request)
	{
		ResultSet resultat = null;
		try
		{
			Statement statement = connection.createStatement();
			resultat = statement.executeQuery(request);
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Erreur dans la requet : " + request);
		}
		return resultat;
	}

	public void delApartment(int idAppart, String idProprio)
	{
		try
		{
			String remQuery = "DELETE from APPARTEMENTS WHERE ProprioId =\'"
					+ idProprio + "\' AND Id=\'" + idAppart + "\';";
			Statement remApStmt = connection.createStatement();
			remApStmt.execute(remQuery);
			remApStmt.close();
		} catch (SQLException e)
		{

		}
	}

	public void addApartment(Apartment apartment)
	{
		try
		{
			String req = "INSERT INTO APPARTEMENTS (Type, Adress,Price,IsSold, OwnerId) VALUES ( ";
			req += Integer.toString(apartment.getType().ordinal()) + " ,";
			req += "'LOLADRESSE'" + " ,";
			req += " 250 , 'True', 2);";
			Statement stat = connection.createStatement();
			stat.executeUpdate(req);
			stat.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getApartmentByOwner(String ownerLogin)
	{
		try
		{
			Statement stmt = connection.createStatement();
			String getQuery = "SELECT * FROM APPARTEMENTS WHERE OwnerId = \'"
					+ ownerLogin + "\';";
			ResultSet rs = stmt.executeQuery(getQuery);
			while(rs.next())
			{
		         int id = rs.getInt("Id");
		         String desc = rs.getString("Description");
		         double price = rs.getDouble("Price");
		         double soldPrice = rs.getDouble("SoldPrice");
		         boolean isSold = rs.getBoolean("IsSold");
		         String adress = rs.getString("Adress");
		         int type = rs.getInt("Type");
			}
		} catch (SQLException e)
		{

		}
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
}
