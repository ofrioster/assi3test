package run;
import java.util.Vector;

public class RunnableDeliveryPerson implements RunnableDeliveryPersonInterface{
	
	private String deliveryPersonName;
	private Double[] restaurantAddres; //the type might change we get (x,y)
	private Double speedOfDeliveryPerson;
	private Vector<Order> collectionDeliverdOrders;

}
