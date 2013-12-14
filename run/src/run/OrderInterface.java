package run;

import java.util.Vector;


public interface OrderInterface {
	
	public void addDish(OrderOfDish orderDish);
	public int getOrderStatus();
	public int getOrderID();
	public int getDifficultyRating();
	public String getCustomerAddress();
	public void changeOrderDifficultyRating(int difficultyRating);
	public Vector<OrderOfDish> getOrderDish();
	
	

}
