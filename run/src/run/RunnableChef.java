package run;
import java.util.Vector;

public class RunnableChef implements RunnableCookOneDishInterface{
	
	private String chefName;
	private Double chefEfficiencyRating;
	private Double enduranceRating;
	private Double currectPressure;
	private Vector<Order> collectionOfOrders;
	private Vector<Thread> poolOfThreads;
	private Vector<CallableCookWholeOrder> CallableCookWholeOrder;
	private Boolean shutDown;
	
	
	public RunnableChef ( String chefName, Double chefEfficiencyRating, Double enduranceRating){
		this.chefName=chefName;
		this.chefEfficiencyRating=chefEfficiencyRating;
		this.enduranceRating=enduranceRating;
		this.currectPressure=0.0;
		this.shutDown=false;
	}
	
	public String gerChefName(){
		return this.chefName;
	}
	public Double getChefEfficiencyRating(){
		return this.chefEfficiencyRating;
	}
	public Double getEnduranceRating(){
		return this.enduranceRating;
	}
	public Double getCurrectPressure(){
		return this.currectPressure;
	}
	//accept new order if dish difficuly< EnduranceRating - CurrectPressure
	public synchronized Boolean addOrder(Order newOrder, Warehouse warehouse){
		double dishDifficuly=newOrder.getDifficultyRating();
		if (dishDifficuly< (enduranceRating-currectPressure)){
			this.currectPressure=this.currectPressure+dishDifficuly;
			this.collectionOfOrders.add(newOrder);
			CallableCookWholeOrder newWholeOrder=new CallableCookWholeOrder();///**** edit
			this.CallableCookWholeOrder.add(newWholeOrder);
			return true;
		}
		return false;
	}
//	public Boolean addCookWholeOrder(CallableCookWholeOrder newCookWholeOrder);
	//finish all the cooking and do not start new ones
	public void shutDown(){
		
	}

}
