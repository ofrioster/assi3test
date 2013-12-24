package run;


import java.util.Vector;

public interface StatisticsInterface {

//	public void addMoneyGain (double actualCookTime,double avtualDeliveryTime, double expectedCookTime,double expectedDeliveryTime);
	public void addDeliveredOrder(Order order);
	public void upDateMoneyGain(Order order);
	public void addinConsumedIgredients(Order order);
	public Double getMoneyGain();
	public Vector<Order> getDeliveredOrders();
	public Vector<Ingredient> getIngredientsConsumed();
	public String toString();
}
