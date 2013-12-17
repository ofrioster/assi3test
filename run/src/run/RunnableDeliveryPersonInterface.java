package run;

public interface RunnableDeliveryPersonInterface {
	
	public String getDeliveryPersonName();
	public Address getRestaurantAddres();
	public Double getSpeedOfDeliveryPerson();
	public void addDeliverdOrder(Order order);
	public Double calculateDeliveryDistance(Address deliveryAddress);
	public long calculateDeliveryTime(Double distance);
	public void deliverOrder(Order orderToDeliver);
	public void addOrder(Order newOrder);
	public void shutDown();
	public void run();
	public long getTotalDeliveryTime();

}
