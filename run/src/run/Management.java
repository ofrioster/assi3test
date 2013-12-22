package run;
import java.util.concurrent.*;
import java.util.Observer;
import java.util.Observable;
import java.util.Vector;

public class Management implements ManagementInterface,Observer,Runnable {
	
	private Vector<Order> collectionOfOrders;
	private Vector<RunnableChef> collectionOfChefs;
	private Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson;
	private Vector<Order> collectionOfOrdersToDeliver;
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
		this.collectionOfOrdersToDeliver=this.copyOrdersVector(collectionOfOrders);
		this.collectionOfChefs=collectionOfChefs;
		this.collectionOfDeliveryPerson=collectionOfDeliveryPerson;
		this.warehouseName=warehouseName;
		this.receiveAllOrders=false;
		this.shutDown=false;
		this.ordersLatch=ordersLatch;
		this.statistics=statistics;
		this.orderCount=0;
	}
	
	public void addOrder(Order newOrder){
		this.collectionOfOrders.add(newOrder);
		this.collectionOfOrdersToDeliver.add(newOrder);
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
	//start all the cook and the threads
//	public void startCooking();
	/** (non-Javadoc)
	 * @add order to chef
	 */
	public synchronized void startToCookDish(Order orderToCook){
		RunnableChef chef=this.findUnbusyChef(orderToCook);
		chef.addOrder(orderToCook, this.warehouseName);
	}
	
	/** (non-Javadoc)
	 * @find a not not busy chef
	 * @ make sure that the this  collectionOfChefs is not empty
	 * @ if the collectionOfChefs is empty return empty RunnableChef
	 * @ keep looking for free chef until one is find
	 */
	public synchronized RunnableChef findUnbusyChef(Order newOrder){
		try{
			RunnableChef res=this.collectionOfChefs.get(0);
			for (int i=0; i<this.collectionOfChefs.size();i++){
				this.collectionOfChefs.get(i).getCurrectPressure();
				if (res.getCurrectPressure()<this.collectionOfChefs.get(i).getCurrectPressure() && this.collectionOfChefs.get(i).canTheChefTakeOrder(newOrder)){
					res=this.collectionOfChefs.get(i);
				}
			}
			if(!res.canTheChefTakeOrder(newOrder));
			return res;
		}
		catch (Exception e) {
			// TODO: handle exception
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
		System.out.println(" update work!!!!");
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
		
		this.startThreadsOfDeliveryPerson();
		while (!this.shutDown && (this.collectionOfOrdersToDeliver.size()>0 )){
	//		System.out.println(this.collectionOfOrders.size());
	//		System.out.println(this.collectionOfOrdersToDeliver.size());
			if(this.collectionOfOrdersToDeliver.size()>0){
				synchronized (collectionOfOrdersToDeliver) {
					this.startToCookDish(this.collectionOfOrdersToDeliver.get(0));
					this.collectionOfOrdersToDeliver.remove(0);
				}
			}
	//		System.out.println(this.collectionOfOrders.size());
	//		System.out.println(this.collectionOfOrdersToDeliver.size());
			this.update1();
		}
		while(!this.receiveAllOrders){
			this.update1();
		}
	//	this.waitUntilAllOrdersDeliverd();
	//	System.out.println("management END");
	}
	
	
	public void setReceiveAllOrders(Boolean receiveAllOrders){
		this.receiveAllOrders=receiveAllOrders;
	}
	public Boolean getReceiveAllOrders(){
		return this.receiveAllOrders;
	}
	public synchronized void ShutDown( ){
		this.shutDown=true;
		for (int i=0;i<this.collectionOfChefs.size();i++){
			this.collectionOfChefs.get(i).shutDown();
		}
		for (int i=0;i<this.collectionOfDeliveryPerson.size();i++){
			this.collectionOfDeliveryPerson.get(i).shutDown();
		}
	}
	public Boolean getShutDown(){
		return this.shutDown;
	}
	/** (non-Javadoc)
	 * @if the observe dont work we use this
	 */
	public void update1() {
		this.receiveAllOrders=true;
		System.out.println(this.orderCount);
//		System.out.println("management update");
//		System.out.println(this.collectionOfOrders.size());
//		System.out.println(this.collectionOfOrders.get(0).getOrderStatus());
		for (int i=this.orderCount;i<this.collectionOfOrders.size();i++){
			this.orderCount++;
			if(this.collectionOfOrders.get(i).getOrderStatus()==3){
				RunnableDeliveryPerson deliveryPerson=this.findUnBusyDeliveryPerson();
				deliveryPerson.addDeliverdOrder(this.collectionOfOrders.get(i));
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
	

}
