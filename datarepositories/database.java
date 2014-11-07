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
        private static String DBPath = "Chemin aux base de donnée SQLite";
        private static Connection connection = null;
        private static Statement statement = null;
 
        public void init()
        {
                database db = new database("//Database.db");
                db.connect();
        }
 
        public database(String dBPath)
        {
                DBPath = dBPath;
        }
 
        public static void connect()
        {
            try
            {
                    Class.forName("org.sqlite.JDBC");
                    connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
                    statement = connection.createStatement();
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
 
        public static ResultSet query(String request)
        {
                ResultSet resultat = null;
                try
                {
                        resultat = statement.executeQuery(request);
                } catch (SQLException e)
                {
                        e.printStackTrace();
                        System.out.println("Erreur dans la requet : " + request);
                }
                return resultat;
        }
 
        public static void delApartment(int idAppart, String idProprio)
        {
                try
                {
                        String remQuery = "DELETE from APPARTEMENTS WHERE ProprioId =\'" + idProprio + "\' AND Id=\'" + idAppart + "\';";
                        Statement remApStmt = connection.createStatement();
                        remApStmt.execute(remQuery);
                        remApStmt.close();
                } catch (SQLException e)
                {
 
                }
        }
 
        public static void addApartment(Apartment apartment)
        {
                try
                {
                        PreparedStatement preparedStatement = connection
                                        .prepareStatement("INSERT INTO APARTMENT VALUES(?,?,?,?,?,?)");
                        preparedStatement.setString(1, Integer.toString(apartment.getId()));
                        preparedStatement.setString(2,
                                        Integer.toString(apartment.getType().ordinal()));
                        preparedStatement.setString(3, apartment.getAddress());
                        preparedStatement.setString(4,
                                        Double.toString(apartment.getPrice()));
                        preparedStatement.setBoolean(5, apartment.isSold());
                } catch (SQLException e)
                {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
 
        public static void close()
        {
                try
                {
                        connection.close();
                        statement.close();
                } catch (SQLException e)
                {
                        e.printStackTrace();
                }
        }
}
