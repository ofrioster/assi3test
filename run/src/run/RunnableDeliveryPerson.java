package run;
import java.util.Vector;

public class RunnableDeliveryPerson implements RunnableDeliveryPersonInterface{
	
	private String DeliveryPersonName;
	private String RestaurantAddres; //the type might change we get (x,y)
	private int speedOfDeliveryPerson;
	private Vector<Order> collectionDeliverdOrders;

}
