package run;

import java.awt.geom.Arc2D.Double;
import java.util.Vector;

public interface StatisticsInterface {

	public void addMoneyGain (double actualCookTime,double avtualDeliveryTime, double expectedCookTime,double expectedDeliveryTime);
	public void addDeliveredOrder(Order order);
	public void addinConsumedGredients(Ingredient ingredient);
	public Double getMoneyGain();
	public Vector<Order> getDeliveredOrders();
	public Vector<Ingredient> getIngredientsConsumed();
}
