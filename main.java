import objects.Apartment;
import objects.Type;
import datarepositories.database;


public class main {

	public static void main(String[] args) {
		Apartment nimportequoi = new Apartment(Type.STUDIO, "TESTST", "Bp,kpir", 2.1);
		
		database db = new database("truc.bd");
		db.connect();
		db.createTables();
		db.addApartment(nimportequoi);
	}
}
