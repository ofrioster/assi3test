package run;

public interface ManagementInterface {

	
	public void addOrder(Order newOrder);
	public void addNewChef(RunnableChef newChef);
	public void addDeliveryPerson (RunnableDeliveryPerson newDeliveryPerson);
	public void addWarehouse(Warehouse newWarehouse);
	//start all the cook and the threads
//	public void startCooking();
	//send order to the chef
	public void startToCookDish(Order orderToCook);
	//find a not not busy chef
	public RunnableChef findUnbusyChef(Order newOrder);
	//add the order that has been finish to statistics
	public void addOrderToStatistics(Order finishOrder);
	//return the total money the gain
	public Double getMoneyGain();
	public RunnableDeliveryPerson findUnBusyDeliveryPerson();
	public void run();
	public void setReceiveAllOrders(Boolean receiveAllOrders);
	public Boolean getReceiveAllOrders();
	public void setShutDown(Boolean shutDown);
	public Boolean getShutDown();
}
