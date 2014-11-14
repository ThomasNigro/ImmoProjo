package objects;



public class Apartment 
{
	private int id;
	private Type type;
	private String address;
	private String desc;
	private double price;
	private double soldPrice;
	private boolean isSold;
	private String idProprio;

	
	
	/*public Apartment(Type t, String _address, String _desc, double _p){
		assert(t!=null);
		type = t;
		address = _address;
		desc = _desc;
		price = _p;
		isSold=false;
	}*/

	
	public Apartment(Type _type, String idOwner, String _desc, double _price, String _adress) {
		// TODO Auto-generated constructor stub
		assert(_type!=null);
		type = _type;
		address = _adress;
		desc = _desc;
		price = _price;
		isSold=false;
		idProprio= idOwner;
	}


	public Apartment(Type _type, int idAppart, String _idOwner, String _desc,
			double _price, double _soldPrice, boolean _isSold, String _adress) {
		// TODO Auto-generated constructor stub
		assert(type!=null);
		type = _type;
		address = _adress;
		desc = _desc;
		price = _price;
		isSold=_isSold;
		id=idAppart;
		soldPrice=_soldPrice;
		idProprio= _idOwner;
	}


	public int getId() {
		return id;
		
	}

	public void setId(int id) {
		this.id = id;
	}

	public Type getType(){
		return type;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public double getPrice(){
		return price;
	}
	
	public boolean isSold() {
		return isSold;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

	public double getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(double soldPrice) {
		this.soldPrice = soldPrice;
	}


	public String getIdProprio() {
		return idProprio;
	}


	public void setIdProprio(String idProprio) {
		this.idProprio = idProprio;
	}

	
}
