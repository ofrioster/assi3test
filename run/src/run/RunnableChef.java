package run;
import java.util.Vector;

public class RunnableChef implements RunnableChefInterface{
	
	private String chefName;
	private Double chefEfficiencyRating;
	private Double enduranceRating;
	private int currectPressure;
	private Vector<Order> orderVector;
	private Vector<Thread> poolOfThreads;
	private Vector<CallableCookWholeOrder> CallableCookWholeOrder;
	private Boolean shutDown;
	
	public RunnableChef(){
		
	}
	public RunnableChef ( String chefName, Double chefEfficiencyRating, Double enduranceRating){
		this.chefName=chefName;
		this.chefEfficiencyRating=chefEfficiencyRating;
		this.enduranceRating=enduranceRating;
		this.currectPressure=0;
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
	public int getCurrectPressure(){
		this.currectPressure=0;
		for (int i=0; i<this.orderVector.size();i++){
			if(this.poolOfThreads.get(i).isAlive()){
				this.setCurrectPressure(this.orderVector.get(i));
			}
		}
		return this.currectPressure;
	}
	public void setCurrectPressure(Order order){
		for (int i=0; i< order.getOrderDish().size();i++){
			this.chefEfficiencyRating=this.chefEfficiencyRating+order.getOrderDish().get(i).gestDish().getDishDifficultyRating();
		}
	}
	
	
	
	/** (non-Javadoc)
	 * @ accept new order if dish difficulty< EnduranceRating - CurrectPressure
	 */
	public synchronized Boolean addOrder(Order newOrder, Warehouse warehouse){
		int dishDifficuly=newOrder.getDifficultyRating();
		if ((dishDifficuly< (enduranceRating-currectPressure))&& !shutDown){
			newOrder.setOrderStatus(2);
			this.currectPressure=this.currectPressure+dishDifficuly;
			this.orderVector.add(newOrder);
			CallableCookWholeOrder newWholeOrder=new CallableCookWholeOrder(newOrder,warehouse);
			this.CallableCookWholeOrder.add(newWholeOrder);
			Thread t=new Thread(newWholeOrder);
			this.poolOfThreads.add(t);
			t.start();
	//		this.setChefEfficiencyRating(newOrder);
			return true;
			
		}
		return false;
	}

	/** (non-Javadoc)
	 * @	finish all the cooking and do not start new ones
	 */
	public void shutDown(){
		this.shutDown=true;
	}
	public Boolean canTheChefTakeOrder(Order newOrder){
		int dishDifficuly=newOrder.getDifficultyRating();
		return ((dishDifficuly< (enduranceRating-currectPressure))&& !shutDown);
	}
	
	

}
