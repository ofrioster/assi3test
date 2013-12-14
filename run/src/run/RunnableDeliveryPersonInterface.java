package run;

public interface RunnableDeliveryPersonInterface {
	
	public String getDeliveryPersonName();
	public  Double[] getRestaurantAddres();
	public Double getspeedOfDeliveryPerson();
	public void addDeliverdOrder(Order order);
	public Double calculateDeliveryDistance(Double[] personAddres);
	public Double calculateDeliveryTime(Double distance);
	public void deliverOrder();
	public Double calculateReward();//may be in other object
	public void shutDown();

}
