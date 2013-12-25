package run;
import java.util.Vector;

public interface ManagementInterface {

	
	public void addOrder(Order newOrder);
	public void addNewChef(RunnableChef newChef);
	public void addDeliveryPerson (RunnableDeliveryPerson newDeliveryPerson);
	public void addWarehouse(Warehouse newWarehouse);
	public void startToCookDish(Order orderToCook);
	public RunnableChef findUnbusyChef(Order newOrder);
	public void addOrderToStatistics(Order finishOrder);
	public Double getMoneyGain();
	public RunnableDeliveryPerson findUnBusyDeliveryPerson();
	public void run();
	public void setReceiveAllOrders(Boolean receiveAllOrders);
	public Boolean getReceiveAllOrders();
	public void ShutDown();
	public Boolean getShutDown();
	public Vector<Order> copyOrdersVector(Vector<Order> vectorToCopy);
	public void startThreadsOfDeliveryPerson ();
	public void startThreadsOfChef();
	public void SetStatisticsToDeliveryPerson ();
	public void sendCollectionOfOrdersToDeliverToChef();
	public void setChefWarehouse();
}