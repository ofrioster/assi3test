package run;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Observer;
import java.util.concurrent.*;

public class RunnableChef implements RunnableChefInterface,Runnable{
	
	private String chefName;
	private Double chefEfficiencyRating;
	private Double enduranceRating;
	private int currectPressure;
	private Vector<Order> orderVector;
	private Vector<Thread> poolOfThreads;
	private ArrayList<CallableCookWholeOrder> CallableCookWholeOrder;
	private Boolean shutDown;
	private Management management;
	private Statistics statistics;
	private Vector<Order> collectionOfOrdersToDeliver;
//	private ArrayList<Order> CallableCookWholeOrder2;
	private ExecutorService executorService1; 
	private ArrayList<Future<Order>> CallableCookWholeOrder2;
	private ArrayList<Order> chefOrders;
	private Warehouse warehouse;
	
	public RunnableChef(){
		this.currectPressure=0;
		this.shutDown=false;
		this.orderVector=new Vector<Order>();
		this.poolOfThreads=new Vector<Thread>();
		this.CallableCookWholeOrder=new ArrayList<CallableCookWholeOrder>();
		this.statistics=new Statistics();
		this.CallableCookWholeOrder2=new ArrayList<Future<Order>>();
		this.executorService1=Executors.newCachedThreadPool();
		this.chefOrders=new ArrayList<Order>();
	}
	public RunnableChef( String chefName, Double chefEfficiencyRating, Double enduranceRating,Warehouse warehouse){
		this.chefName=chefName;
		this.chefEfficiencyRating=chefEfficiencyRating;
		this.enduranceRating=enduranceRating;
		this.currectPressure=0;
		this.shutDown=false;
		this.orderVector=new Vector<Order>();
		this.poolOfThreads=new Vector<Thread>();
		this.CallableCookWholeOrder=new ArrayList<CallableCookWholeOrder>();
		this.statistics=new Statistics();
		this.CallableCookWholeOrder2=new ArrayList<Future<Order>>();
		this.executorService1=Executors.newCachedThreadPool();
		this.chefOrders=new ArrayList<Order>();
		this.warehouse=warehouse;
	}
	public void setWarehouse(Warehouse warehouse){
		this.warehouse=warehouse;
	}
	public String getChefName(){
		return this.chefName;
	}
	public Double getChefEfficiencyRating(){
	//	System.out.println("time "+this.chefEfficiencyRating);
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
		//System.out.println("time "+this.chefEfficiencyRating);
		 this.chefEfficiencyRating=chefEfficiencyRating;
	}
	public void setEnduranceRating(Double enduranceRating){
		 this.enduranceRating= enduranceRating;
	}
	
	
	/*public int getCurrectPressure(){
		this.currectPressure=0;
		for (int i=0; i<this.orderVector.size();i++){
			if(this.poolOfThreads.get(i).isAlive()){
				this.setCurrectPressure(this.orderVector.get(i));
			}
		}
		return this.currectPressure;
	}*/
	public int getCurrectPressure(){
		this.currectPressure=0;
		for (int i=0; i<this.CallableCookWholeOrder2.size();i++){
			if(!this.CallableCookWholeOrder2.get(i).isDone()){
				try {
					this.setCurrectPressure(this.CallableCookWholeOrder2.get(i).get());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
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
		this.chefOrders.add(newOrder);
	}
	/*//	System.out.println(" 3333 ");
	//	System.out.println(" chef start addOrder dish name- "+ newOrder.getOrderID());
	//	System.out.println("start addOrder orderID- "+ newOrder.getOrderID());
		int dishDifficuly=newOrder.getDifficultyRating();
	//	System.out.println("here");
		if ((dishDifficuly<= (enduranceRating-currectPressure))&& !shutDown){
		//	System.out.println("here");
			newOrder.setOrderStatus(2);
			this.currectPressure=this.currectPressure+dishDifficuly;
			this.orderVector.add(newOrder);
			CallableCookWholeOrder newWholeOrder=new CallableCookWholeOrder(newOrder,warehouse,this,this.statistics,this.collectionOfOrdersToDeliver,this.management);
			Callable<Order> newWholeOrder2=new CallableCookWholeOrder(newOrder,warehouse,this,this.statistics,this.collectionOfOrdersToDeliver,this.management);
			this.CallableCookWholeOrder.add(newWholeOrder);
	// to active the run function		Thread t=new Thread(newWholeOrder);
	//	to active the run function	this.poolOfThreads.add(t);
	//	to active the run function	t.start();
			Future<Order> newThread=executorService1.submit(newWholeOrder2);
			this.CallableCookWholeOrder2.add(newThread);
			return true;
			
		}
		return false;
		
	}
	*/
	public synchronized void cookOrder(Order newOrder){
		if(!this.chefOrders.isEmpty()){
			this.chefOrders.remove(0);
		}
		//	System.out.println(" 3333 ");
		//	System.out.println(" chef start addOrder dish name- "+ newOrder.getOrderID());
			System.out.println("start addOrder orderID- "+ newOrder.getOrderID());
			int dishDifficuly=newOrder.getDifficultyRating();
		//	System.out.println("here");
			if ((dishDifficuly<= (enduranceRating-currectPressure))&& !shutDown){
			//	System.out.println("here");
				newOrder.setOrderStatus(2);
				this.currectPressure=this.currectPressure+dishDifficuly;
				this.orderVector.add(newOrder);
				CallableCookWholeOrder newWholeOrder=new CallableCookWholeOrder(newOrder,this.warehouse,this,this.statistics,this.collectionOfOrdersToDeliver,this.management);
				Callable<Order> newWholeOrder2=new CallableCookWholeOrder(newOrder,this.warehouse,this,this.statistics,this.collectionOfOrdersToDeliver,this.management);
				this.CallableCookWholeOrder.add(newWholeOrder);
		// to active the run function		Thread t=new Thread(newWholeOrder);
		//	to active the run function	this.poolOfThreads.add(t);
		//	to active the run function	t.start();
				Future<Order> newThread=executorService1.submit(newWholeOrder2);
				this.CallableCookWholeOrder2.add(newThread);
			}
		}

	/** (non-Javadoc)
	 * @	finish all the cooking and do not start new ones
	 */
	public void shutDown(){
		this.executorService1.shutdown();
		this.shutDown=true;
	}
	public Boolean canTheChefTakeOrder(Order newOrder){
		int dishDifficuly=newOrder.getDifficultyRating();
		return ((dishDifficuly<= (enduranceRating-currectPressure))&& !shutDown);
	}
	
	public void addManagement(Management management){
		this.management=management;
	}
	public String toString(){
		String res=" chefName- "+this.chefName+" chefEfficiencyRating- "+this.chefEfficiencyRating+" enduranceRating- "+this.enduranceRating+" currectPressure- "+this.currectPressure+" orderVector- "+this.orderVector.toString();
		return res;
		
	}
	public void run(){
		while (!this.shutDown){
		/*	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			if (!this.chefOrders.isEmpty()){
				this.cookOrder(this.chefOrders.get(0));
			}
			
		}
	}
}
