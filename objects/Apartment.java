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
	
	public Apartment(Type t, String _address, String _desc, double _p){
		type = t;
		address = _address;
		desc = _desc;
		price = _p;
		isSold=false;
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
