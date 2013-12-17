package run;

import java.util.Vector;


public interface OrderInterface {
	
	public void addDish(OrderOfDish orderDish);
	public int getOrderStatus();
	public String getOrderID();
	public int getDifficultyRating();
	public Address getCustomerAddress();
	public void changeOrderDifficultyRating(int difficultyRating);
	public Vector<OrderOfDish> getOrderDish();
	public void setOrderStatus(int status);
	public void setOrderStatus(String status);
	public void setExpectedDeliveryTime(long deliveryTime);
	public long getExpectedDeliveryTime();
	public void setActualCookTime(long cookTime);
	public long getActualCookTime();
	public void setActualDeliveryTime(long deliveryTime);
	public long getActualDeliveryTime();
	public void setExpectedCookTime(long cookTime);
	public long getExpectedCookTime();
	public void setTotalReward();
	public Double getTotalReward();
	public long calculateCookTime(Vector<OrderOfDish> orderDish);
	public void setDifficultyRating();
	public long calculateCookTime(OrderOfDish orderDish);
	
	
	

}
