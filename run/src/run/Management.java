package run;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import java.util.Vector;

public class Management implements ManagementInterface,Observer,Runnable {
	
	  private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private Vector<Order> collectionOfOrders;
	private Vector<RunnableChef> collectionOfChefs;
	private ArrayList<RunnableChef> collectionOfChefsArryList;
	private Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson;
	private Vector<Order> collectionOfOrdersToDeliver;
	private Vector<Order> collectionOfOrdersToCock;
	private ArrayList<Order> collectionOfOrdersToCockArrayList;
	private Warehouse warehouseName;
	private Statistics statistics;
	private long numberOfOrderThatProcess;
	private Boolean receiveAllOrders; 
	private Boolean shutDown;
	private CountDownLatch ordersLatch;
	private int orderCount;
	
	public Management(){
		
	}
	
	public Management(Vector<Order> collectionOfOrders,Vector<RunnableChef> collectionOfChefs,Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson,Warehouse warehouseName,CountDownLatch ordersLatch,Statistics statistics){
		this.collectionOfOrders=collectionOfOrders;
		this.collectionOfOrdersToCock=this.copyOrdersVector(collectionOfOrders);
		this.collectionOfChefs=collectionOfChefs;
		this.collectionOfDeliveryPerson=collectionOfDeliveryPerson;
		this.warehouseName=warehouseName;
		this.receiveAllOrders=false;
		this.shutDown=false;
		this.ordersLatch=ordersLatch;
		this.statistics=statistics;
		this.orderCount=0;
		this.collectionOfOrdersToDeliver=new Vector<Order>();
	/**/	this.collectionOfChefsArryList=new ArrayList<RunnableChef>();
			for (int i=0; i<this.collectionOfChefs.size();i++){
				this.collectionOfChefsArryList.add(this.collectionOfChefs.get(i));
			}
			this.collectionOfOrdersToCockArrayList=new ArrayList<Order>();
	/**/	for (int i=0; i<this.collectionOfOrdersToCock.size();i++){
				this.collectionOfOrdersToCockArrayList.add(this.collectionOfOrdersToCock.get(i));
			}
	}
	
	public void addOrder(Order newOrder){
		this.collectionOfOrders.add(newOrder);
		this.collectionOfOrdersToCock.add(newOrder);
		this.collectionOfOrdersToCockArrayList.add(newOrder);
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
	/** (non-Javadoc)
	 * @ start the threads of DeliveryPerson
	 * @ need to be done once
	 */
	public void startThreadsOfDeliveryPerson (){
		for (int i=0;i<this.collectionOfDeliveryPerson.size();i++){
			Thread t=new Thread(this.collectionOfDeliveryPerson.get(i));
			t.start();
		}
	}
	public void startThreadsOfChef(){
		for (int i=0;i<this.collectionOfChefs.size();i++){
			Thread t=new Thread(this.collectionOfChefs.get(i));
			t.start();
		}
	}
	//start all the cook and the threads
//	public void startCooking();
	/** (non-Javadoc)
	 * @add order to chef
	 */
	public synchronized void startToCookDish(Order orderToCook){
		//System.out.println("managemant- startToCookDish- befor order ID-"+orderToCook.getOrderID());
		RunnableChef chef=this.findUnbusyChef(orderToCook);
	//	System.out.println("managemant- startToCookDish- befor order ID-"+orderToCook.getOrderID());
		chef.addOrder(orderToCook);
	}
	
	/** (non-Javadoc)
	 * @find a not not busy chef
	 * @ make sure that the this  collectionOfChefs is not empty
	 * @ if the collectionOfChefs is empty return empty RunnableChef
	 * @ keep looking for free chef until one is find
	 */
	public synchronized RunnableChef findUnbusyChef(Order newOrder){
		Boolean found=false;
		RunnableChef res;
		while (!found){
			try{
				res=this.collectionOfChefsArryList.get(0);
				for (int i=0; i<this.collectionOfChefsArryList.size();i++){
					int k=this.collectionOfChefsArryList.get(i).getCurrectPressure();
					int w=k;
					int e=w;
	//				System.out.println("chef name: "+this.collectionOfChefsArryList.get(i)+" currect presher: "+res.getCurrectPressure());
	//				System.out.println(this.collectionOfChefs.get(i).getCurrectPressure());
	//				System.out.println(this.collectionOfChefs.get(i).canTheChefTakeOrder(newOrder));
					Boolean testing=this.collectionOfChefs.get(i).canTheChefTakeOrder(newOrder);
					if (res.getCurrectPressure()>this.collectionOfChefsArryList.get(i).getCurrectPressure() && this.collectionOfChefs.get(i).canTheChefTakeOrder(newOrder)){
						res=this.collectionOfChefsArryList.get(i);
//						System.out.println(this.collectionOfChefsArryList.get(i).getChefName());
					}
				}
				if (res.canTheChefTakeOrder(newOrder)){
					return res;
				}
				}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		RunnableChef e=new RunnableChef();
		return e;
	}
	//add the order that has been finish to statistics
	public void addOrderToStatistics(Order finishOrder){
		this.statistics.addDeliveredOrder(finishOrder);
	}
	//return the total money the gain
	public Double getMoneyGain(){
		return this.statistics.getMoneyGain();
	}
	public void SetStatisticsToDeliveryPerson (){
		for (int i=0;i>this.collectionOfDeliveryPerson.size();i++){
			this.collectionOfDeliveryPerson.get(i).setStatistics(statistics);
		}
	}
	
	/** (non-Javadoc)
	 * @if the DeliveryPerson Vector is empty return empty RunnableDeliveryPerson 
	 */
	public RunnableDeliveryPerson findUnBusyDeliveryPerson(){
		RunnableDeliveryPerson res;
		if(this.collectionOfDeliveryPerson.size()>0){
			res=this.collectionOfDeliveryPerson.get(0);
			for (int i=1;i<this.collectionOfDeliveryPerson.size();i++){
				if (res.getTotalDeliveryTime()<this.collectionOfDeliveryPerson.get(i).getTotalDeliveryTime()){
					res=this.collectionOfDeliveryPerson.get(i);
				}
			}
			return res;
		}
		res=new RunnableDeliveryPerson();
		return res;
	}

	/** (non-Javadoc)
	 * @ this function run when Observable get notifyObservers
	 * @ if the Order have been finish, send it to the delivery person
	 */
	public void update(Observable o, Object arg) {
//		System.out.println(" update work!!!!");
		try{
			Order order=(Order)arg;
			if(order.getOrderStatus()==3){
				RunnableDeliveryPerson deliveryPerson=this.findUnBusyDeliveryPerson();
				deliveryPerson.addDeliverdOrder(order);
			}
		}
		catch(Exception e){
			//may need to add
		}
		
	}
	/* (non-Javadoc)
	 * @start the Management that will stop only when the orders has finish
	 */
	public synchronized void run(){
//		System.out.println("Dish difficultyRating: "+this.collectionOfOrdersToCockArrayList.get(0).getOrderDish().get(0).gestDish().getDishDifficultyRating());
	    logger.log(Level.INFO, "Initializing simulation process...");
	    logger.log(Level.INFO, "System contains: " +  "[chefs=" + collectionOfChefs.size() + "][deliveryPeople=" + collectionOfDeliveryPerson.size() + "][orders="+collectionOfOrders.size()+"]");
	    this.collectionOfChefsArryList=this.sortRunnableChefArray(this.collectionOfChefsArryList);
	    this.collectionOfOrdersToCockArrayList=this.sortOrderArray(this.collectionOfOrdersToCockArrayList);
		this.sendCollectionOfOrdersToDeliverToChef();
		this.startThreadsOfDeliveryPerson();
		this.startThreadsOfChef();
		this.setChefWarehouse();
		while (!this.shutDown && (this.collectionOfOrdersToCockArrayList.size()>0 )){
			logger.log(Level.INFO, "Orders To Cook:"+ this.collectionOfOrdersToCockArrayList.size());
			
	//		System.out.println(this.collectionOfOrders.size());
	//		System.out.println(this.collectionOfOrdersToCock.size());
			
			if(this.collectionOfOrdersToCockArrayList.size()>0){
				int k=this.collectionOfOrdersToCockArrayList.size();
			//	synchronized (collectionOfOrdersToCock) {
			//	System.out.println("managemant befor order ID-"+this.collectionOfOrdersToCock.get(0).getOrderID());
			//	System.out.println("managemant befor order ID-"+this.collectionOfOrdersToCock.get(1).getOrderID());
			//	System.out.println("managemant befor- "+this.collectionOfOrdersToCock.get(0).getOrderDish().get(0).gestDish().getDishName());
			//	System.out.println("managemant befor- "+this.collectionOfOrdersToCock.size());
					//Attempting to send order: [0][numberOfMeals=8]
					logger.log(Level.INFO, "Attempting to send order:"+this.collectionOfOrdersToCockArrayList.get(0).toStringForLogger());
					this.startToCookDish(this.collectionOfOrdersToCockArrayList.get(0));
					this.collectionOfOrdersToCockArrayList.remove(0);
			//		orderCount++;
			//		System.out.println("managemant after - "+this.collectionOfOrdersToCock.size());
				//}
			}
	//		System.out.println(this.collectionOfOrders.size());
	//		System.out.println(this.collectionOfOrdersToCock.size());
			this.update1();
		}
		/*
		while(!this.receiveAllOrders &&!this.shutDown){
			this.update1();
		}
		*/
		while(!this.shutDown){
			this.update1();
		}
	//	this.waitUntilAllOrdersDeliverd();
		System.out.println("management END");
	}
	
	
	public void setReceiveAllOrders(Boolean receiveAllOrders){
		this.receiveAllOrders=receiveAllOrders;
	}
	public Boolean getReceiveAllOrders(){
		return this.receiveAllOrders;
	}
	public void ShutDown( ){
	//	System.out.println("management start shoutdown");
		for (int i=0;i<this.collectionOfChefs.size();i++){
			this.collectionOfChefs.get(i).shutDown();
		}
	//	System.out.println("shef has shoutdouwn");
		for (int i=0;i<this.collectionOfDeliveryPerson.size();i++){
			this.collectionOfDeliveryPerson.get(i).shutDown();
		}
		for (int i=0;i<this.collectionOfOrders.size();i++){
			//System.out.println("order ID: "+this.collectionOfOrders.get(i).getOrderID()+" reward: "+this.collectionOfOrders.get(i).getTotalReward());
			//System.out.println("order ID: "+this.collectionOfOrders.get(i).getOrderID()+" expected reward: "+this.collectionOfOrders.get(i).calculateReward());
		}
		this.shutDown=true;
	}
	public Boolean getShutDown(){
		return this.shutDown;
	}
	/** (non-Javadoc)
	 * @if the observe dont work we use this
	 */
	public void update1() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.receiveAllOrders=true;
//		System.out.println("update1 - this.orderCount "+this.orderCount);
//		System.out.println("update1 - this.ordersLatch.getCount "+this.ordersLatch.getCount());
		for (int i=this.orderCount;i<this.collectionOfOrdersToDeliver.size();i++){
			this.orderCount++;
//			System.out.println("here");
			if(this.collectionOfOrdersToDeliver.get(i).getOrderStatus()==3){
				RunnableDeliveryPerson deliveryPerson=this.findUnBusyDeliveryPerson();
//				System.out.println("order ID to deliver "+ this.collectionOfOrdersToDeliver.get(i).getOrderID());
				deliveryPerson.addDeliverdOrder(this.collectionOfOrdersToDeliver.get(i));
//				System.out.println("order ID to deliver "+ this.collectionOfOrdersToDeliver.get(i).getOrderID());
	//			System.out.println("888 "+ this.collectionOfOrdersToDeliver.size());
	//			System.out.println("888 orderCount "+ this.orderCount);
			}
			else{
				this.receiveAllOrders=false;
			}
		}
	}

	public void waitUntilAllOrdersDeliverd(){
	//	System.out.println("waitUntilAllOrdersDeliverd");
		Boolean allOrdersDeliverd=false;
		while (!allOrdersDeliverd){
			allOrdersDeliverd=true;
			for (int  i=0; i<this.collectionOfOrders.size();i++){
				if (this.collectionOfOrders.get(i).getOrderStatus()!=4){
					allOrdersDeliverd=false;
				}
			}
		}
		
	}
	public Vector<Order> copyOrdersVector(Vector<Order> vectorToCopy) {
		Vector<Order> res= new Vector<Order>();
		for (int i=0;i<vectorToCopy.size();i++){
			//Order newOrder=new Order(vectorToCopy.get(i));
			res.add(vectorToCopy.get(i));
		}
		return res;
	}
	public void sendCollectionOfOrdersToDeliverToChef(){
		for (int i=0; i<this.collectionOfChefs.size();i++){
			this.collectionOfChefs.get(i).addCollectionOfOrdersToDeliver(this.collectionOfOrdersToDeliver);
		}
	}
	public void setChefWarehouse(){
		for (int i=0;i<this.collectionOfChefs.size();i++){
			this.collectionOfChefs.get(i).setWarehouse(this.warehouseName);
		}
	}
	public ArrayList<Order> sortOrderArray(ArrayList<Order> arryToSort){
		ArrayList<Order> res=new ArrayList<Order>();
		while (!arryToSort.isEmpty()){
			int k=0;
			for (int i=0; i<arryToSort.size();i++){
				if (arryToSort.get(k).getDifficultyRating()>arryToSort.get(i).getDifficultyRating()){
					k=i;
				}
			}
			res.add(arryToSort.get(k));
			arryToSort.remove(k);
		}
		/*
		for(int i=0;i<res.size();i++){
			System.out.println("sort order ID: "+res.get(i).getOrderID()+" dish diffculty: "+res.get(i).getDifficultyRating());
		}
		*/
		return res;
		
	}
	public ArrayList<RunnableChef> sortRunnableChefArray(ArrayList<RunnableChef> arryToSort){
		ArrayList<RunnableChef> res=new ArrayList<RunnableChef>();
		while (!arryToSort.isEmpty()){
			int k=0;
			for (int i=0;i<arryToSort.size();i++){
				if( arryToSort.get(k).getChefEfficiencyRating()>arryToSort.get(i).getChefEfficiencyRating()){
					k=i;
				}
			}
			res.add(arryToSort.get(k));
			arryToSort.remove(k);
		}
		return res;
	}

}
