package run;
import java.util.Vector;

public class RunnableChef implements RunnableCookOneDishInterface{
	
	private String chefName;
	private Double chefEfficiencyRating;
	private Double enduranceRating;
	private int currectPressure;
	private Vector<Order> collectionOfOrders;
	private Vector<Thread> poolOfThreads;
	private Vector<CallableCookWholeOrder> CallableCookWholeOrder;
	private Boolean shutDown;
	
	
	public RunnableChef ( String chefName, Double chefEfficiencyRating, Double enduranceRating){
		this.chefName=chefName;
		this.chefEfficiencyRating=chefEfficiencyRating;
		this.enduranceRating=enduranceRating;
		this.currectPressure=0;
		this.shutDown=false;
	}

}
