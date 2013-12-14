package run;

public interface ManagementInterface {

	
	public void addOrder(Order newOrder);
	public void addNewChef(RunnableChef newChef);
	public void addDeliveryPerson (RunnableDeliveryPerson newDeliveryPerson);
	public void addWarehouse(Warehouse newWarehouse);
	//start all the cook and the threads
	public void startCooking();
	//send order to the chef
	abstract void startToCookDish(Order orderToCook, RunnableChef chef);
	//find a not not busy chef
	abstract RunnableChef findUnbusyChef();
	//update the statistics
	abstract void updateStatistics(double actualCookTime,double avtualDeliveryTime);
	//add the order that has been finish to statistics
	abstract void addOrderToStatistics(Order finishOrder);
	//return the total money the gain
	public Double getMoneyGain();
}
