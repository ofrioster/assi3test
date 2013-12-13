package run;
import java.util.Vector;

public class RunnableChef implements RunnableCookOneDishInterface{
	
	private String chefName;
	private int chefEfficiencyRating;
	private int enduranceRating;
	private int currectPressure;
	private Vector<Order> collectionOfOrders;
	private Vector<Thread> poolOfThreads;

}
