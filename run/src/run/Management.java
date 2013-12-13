package run;
import java.util.Vector;

public class Management implements ManagementInterface {
	
	private Vector<Order> collectionOfOrders;
	private Vector<RunnableChef> collectionOfChefs;
	private Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson;
	private Warehouse warehouseName;

}
