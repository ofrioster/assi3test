package run;

public interface AddressInterface {

	public void setAddress(int x,int y);
	public int getXAddress();
	public int getYAddress();
	public Double calculateDistance(Address address);
	public String toString();
}
