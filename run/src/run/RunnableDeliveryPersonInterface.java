package run;

public interface RunnableDeliveryPersonInterface {
	
	public String getDeliveryPersonName();
	public Double getSpeedOfDeliveryPerson();
	
	public  void setDeliveryPersonName(String PersonName);
	public  void setSpeedOfDeliveryPerson(Double DeliveryPerson);

	
	public Address getRestaurantAddres();
	public void addDeliverdOrder(Order order);
	public Double calculateDeliveryDistance(Address deliveryAddress);
	public long calculateDeliveryTime(Double distance);
	public void deliverOrder(Order orderToDeliver);
	public void addOrder(Order newOrder);
	public void shutDown();
	public void run();
	public long getTotalDeliveryTime();
	public void setStatistics(Statistics statistics);
	public String toString();
	public void removeFinishOrder(Order orderHasDeliver);
	public Boolean isAlive();

}
