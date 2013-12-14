package run;
import java.util.Vector;

public class Management implements ManagementInterface {
	
	private Vector<Order> collectionOfOrders;
	private Vector<RunnableChef> collectionOfChefs;
	private Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson;
	private Warehouse warehouseName;
	private Statistics statistics;
	private long numberOfOrderThatProcess;
	
	public Management(){
		
	}
	
	public Management(Vector<Order> collectionOfOrders,Vector<RunnableChef> collectionOfChefs,Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson,Warehouse warehouseName){
		this.collectionOfOrders=collectionOfOrders;
		this.collectionOfChefs=collectionOfChefs;
		this.collectionOfDeliveryPerson=collectionOfDeliveryPerson;
		this.warehouseName=warehouseName;
	}
	
	public void addOrder(Order newOrder){
		this.collectionOfOrders.add(newOrder);
	}
	
	public void addNewChef(RunnableChef newChef){
		this.collectionOfChefs.add(newChef);
	}
	public void addDeliveryPerson (RunnableDeliveryPerson newDeliveryPerson){
		this.collectionOfDeliveryPerson.add(newDeliveryPerson);
	}
	public void addWarehouse(Warehouse newWarehouse){
		this.warehouseName=newWarehouse;
	}

}
