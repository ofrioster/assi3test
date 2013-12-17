package run;

public class Address implements AddressInterface{
	private int x;
	private int y;
	
	public Address(){
		
	}
	
	public Address(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void setAddress(int x,int y){
		this.x=x;
		this.y=y;
	}
	public int getXAddress(){
		return this.x;
	}
	public int getYAddress(){
		return this.y;
	}
	public Double calculateDistance(Address address){
		Double x1=(double) (this.x-address.getXAddress());
		Double y1=(double) (this.y-address.getYAddress());
		x1=x1*x1;
		y1=y1*y1;
		Double res=Math.sqrt(x1+y1);
		return res;
	}

}
