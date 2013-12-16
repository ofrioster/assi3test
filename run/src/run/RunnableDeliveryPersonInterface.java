package run;

public interface RunnableDeliveryPersonInterface {
	
	public String getDeliveryPersonName();
	public Double[] getRestaurantAddres();
	public Double getSpeedOfDeliveryPerson();
	public void addDeliverdOrder(Order order);
	public Double calculateDeliveryDistance(Double[] deliveryAddress);
	public long calculateDeliveryTime(Double distance);
	public void deliverOrder(Order orderToDeliver);
	public void addOrder(Order newOrder);
	public void shutDown();
	public void run();
	public long getTotalDeliveryTime();

}
