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
	
	
	public Apartment(Type t, String _address, String _desc, double _p){
		assert(t!=null);
		type = t;
		address = _address;
		desc = _desc;
		price = _p;
		isSold=false;
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

	
}
