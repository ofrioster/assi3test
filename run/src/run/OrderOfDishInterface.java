package run;

public interface OrderOfDishInterface {
	
	public int getOrderStatus();
	public int getquantity();
	public Dish gestDish();
	public void setOrderStatus(int status);
	public void setOneDishIsDone();
	public int getQuantityLeft();
	public String toString();

}
