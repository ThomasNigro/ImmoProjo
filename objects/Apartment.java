package objects;

public class Apartment 
{
	private int id;
	private Type type;
	private String address;
	private String desc;
	private double price;
	
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
	}
	
}
