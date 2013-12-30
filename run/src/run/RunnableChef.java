package run;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RunnableChef implements RunnableChefInterface,Runnable{
	  private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private String chefName;
	private Double chefEfficiencyRating;
	private Double enduranceRating;
	private int currectPressure;
	private Vector<Order> orderVector;
	private ArrayList<CallableCookWholeOrder> CallableCookWholeOrder;
	private Boolean shutDown;
	private Management management;
	private Statistics statistics;
	private Vector<Order> collectionOfOrdersToDeliver;
	private ExecutorService executorService1; 
	private ArrayList<Future<Order>> CallableCookWholeOrder2;
	private ArrayList<Order> chefOrders;
	private Warehouse warehouse;
	private Boolean isAlive;
	
	public RunnableChef(){
		this.currectPressure=0;
		this.shutDown=false;
		this.orderVector=new Vector<Order>();
		this.CallableCookWholeOrder=new ArrayList<CallableCookWholeOrder>();
		this.statistics=new Statistics(this.warehouse);
		this.CallableCookWholeOrder2=new ArrayList<Future<Order>>();
		this.executorService1=Executors.newCachedThreadPool();
		this.chefOrders=new ArrayList<Order>();
		this.isAlive=false;
	}
	public RunnableChef( String chefName, Double chefEfficiencyRating, Double enduranceRating,Warehouse warehouse){
		this.chefName=chefName;
		this.chefEfficiencyRating=chefEfficiencyRating;
		this.enduranceRating=enduranceRating;
		this.currectPressure=0;
		this.shutDown=false;
		this.orderVector=new Vector<Order>();
		this.CallableCookWholeOrder=new ArrayList<CallableCookWholeOrder>();
		this.statistics=new Statistics(this.warehouse);
		this.CallableCookWholeOrder2=new ArrayList<Future<Order>>();
		this.executorService1=Executors.newCachedThreadPool();
		this.chefOrders=new ArrayList<Order>();
		this.warehouse=warehouse;
		this.isAlive=false;
	}
	public void setWarehouse(Warehouse warehouse){
		this.warehouse=warehouse;
	}
	public String getChefName(){
		return this.chefName;
	}
	public Double getChefEfficiencyRating(){
		return this.chefEfficiencyRating;
	}
	public void addCollectionOfOrdersToDeliver(Vector<Order> collectionOfOrdersToDeliver){
		this.collectionOfOrdersToDeliver=collectionOfOrdersToDeliver;
	}
	public Double getEnduranceRating(){
		return this.enduranceRating;
	}
	public void setChefName(String chefName){
		 this.chefName =chefName;
	}
	public void setChefEfficiencyRating(Double chefEfficiencyRating){
		 this.chefEfficiencyRating=chefEfficiencyRating;
	}
	public void setEnduranceRating(Double enduranceRating){
		 this.enduranceRating= enduranceRating;
	}
	
	
	public int updateCurrectPressure(){
		this.currectPressure=0;
		for (int i=0; i<this.CallableCookWholeOrder2.size();i++){
			if(!this.CallableCookWholeOrder2.get(i).isDone()){
				try {
					this.setCurrectPressure(this.CallableCookWholeOrder2.get(i).get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
		return this.currectPressure;
	}
	public void setCurrectPressure(Order order){
		for (int i=0; i< order.getOrderDish().size();i++){
			this.currectPressure=this.currectPressure+order.getOrderDish().get(i).gestDish().getDishDifficultyRating();
		}
	}
	
	
	
	/** (non-Javadoc)
	 * @ accept new order if dish difficulty< EnduranceRating - CurrectPressure
	 */
	public synchronized void addOrder(Order newOrder){
		this.setCurrectPressure(newOrder);
		this.chefOrders.add(newOrder);
		this.notifyAll();
		logger.log(Level.INFO, "Order ACCEPTED:" + newOrder.getOrderID());
	}
	
	public void cookOrder(Order newOrder){
		if(!this.chefOrders.isEmpty()){
			this.chefOrders.remove(0);
		}
			int dishDifficuly=newOrder.getDifficultyRating();
				newOrder.setOrderStatus(2);
				this.currectPressure=this.currectPressure+dishDifficuly;
				this.orderVector.add(newOrder);
				CallableCookWholeOrder newWholeOrder=new CallableCookWholeOrder(newOrder,this.warehouse,this,this.statistics,this.collectionOfOrdersToDeliver,this.management);
				Callable<Order> newWholeOrder2=new CallableCookWholeOrder(newOrder,this.warehouse,this,this.statistics,this.collectionOfOrdersToDeliver,this.management);
				this.CallableCookWholeOrder.add(newWholeOrder);
				Future<Order> newThread=executorService1.submit(newWholeOrder2);
				this.CallableCookWholeOrder2.add(newThread);
			}

	/** (non-Javadoc)
	 * @	finish all the cooking and do not start new ones
	 */
	public void shutDown(){
		this.isAlive=false;
		this.executorService1.shutdown();
		this.shutDown=true;
		logger.log(Level.INFO, "Chef has been shut down.");
	}
	public Boolean isAlive(){
		return this.isAlive;
	}
	public Boolean canTheChefTakeOrder(Order newOrder){
		int dishDifficuly=newOrder.getDifficultyRating();
		if ((dishDifficuly<= (enduranceRating-currectPressure))){
		    logger.log(Level.INFO, "Order REFUSED: "+newOrder.getOrderID() +" [difficulty="+ dishDifficuly +"][availableEndurance="+(enduranceRating-currectPressure)+"]");
			
		}
		return ((dishDifficuly<= (enduranceRating-currectPressure))&& !shutDown);
	}
	
	public void addManagement(Management management){
		this.management=management;
	}

	public void run(){
		this.isAlive=true;
		while (!this.shutDown&& this.isAlive){
			this.updateCurrectPressure();
			if (!this.chefOrders.isEmpty()){
				this.cookOrder(this.chefOrders.get(0));
			}
			else if (this.chefOrders.isEmpty()){
				this.isAlive=false;
			}
		}
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chef [Name=");
		builder.append(chefName);
		builder.append(", EfficiencyRating=");
		builder.append(chefEfficiencyRating);
		builder.append(", enduranceRating=");
		builder.append(enduranceRating);
		builder.append(", currectPressure=");
		builder.append(currectPressure);
		for ( Order order : orderVector){
			builder.append("\n Order:");
			builder.append(order);
		}
		
		builder.append("]");
		return builder.toString();
	}
	public int getCurrectPressure() {
		return this.currectPressure;
	}
}
