package run;

public interface StatisticsInterface {

	public void addMoneyGain (double actualCookTime,double avtualDeliveryTime, double expectedCookTime,double expectedDeliveryTime);
	public void addDeliveredOrder(Order order);
	public void addinConsumedGredients(Ingredient ingredient);
}
