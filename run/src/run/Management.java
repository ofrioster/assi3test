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
	
	public Management(){
		
	}
	
	public Management(Vector<Order> collectionOfOrders,Vector<RunnableChef> collectionOfChefs,Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson,Warehouse warehouseName,CountDownLatch ordersLatch){
		this.collectionOfOrders=collectionOfOrders;
		this.collectionOfOrdersToDeliver=collectionOfOrders;
		this.collectionOfChefs=collectionOfChefs;
		this.collectionOfDeliveryPerson=collectionOfDeliveryPerson;
		this.warehouseName=warehouseName;
		this.receiveAllOrders=false;
		this.shutDown=false;
		this.ordersLatch=ordersLatch;
		this.statistics=new Statistics();
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
	//start all the cook and the threads
//	public void startCooking();
	/** (non-Javadoc)
	 * @add order to chef
	 */
	public synchronized void startToCookDish(Order orderToCook){
		System.out.println("this.startToCookDish()");
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
	 * @this function run when Observable get notifyObservers
	 * @ if the Order have been finish, send it to the delivery person
	 */
	public void update(Observable o, Object arg) {
		System.out.println("management update");
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
		System.out.println("1 "+this.collectionOfOrdersToDeliver.get(0).getOrderID());
		while (!this.shutDown && (this.collectionOfOrdersToDeliver.size()>0 )){
			System.out.println("what ths?? "+this.collectionOfOrdersToDeliver.size());
			if(this.collectionOfOrdersToDeliver.size()>0){
				synchronized (collectionOfOrdersToDeliver) {
					//delete
					System.out.println("what ths?? "+this.collectionOfOrdersToDeliver.size());
					Order testOrder=this.collectionOfOrders.get(0);
					System.out.println("1 2 "+testOrder.getDifficultyRating());
					this.startToCookDish(this.collectionOfOrdersToDeliver.get(0));
					this.collectionOfOrdersToDeliver.remove(0);
					System.out.println("1 this.collectionOfOrders.size() " +this.collectionOfOrders.size());
				}
			}
			this.update1();
		}
		while(!this.receiveAllOrders){
			this.update1();
		}
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
	/* (non-Javadoc)
	 * @if the observe dont work we use this
	 */
	public void update1() {
		this.receiveAllOrders=true;
		System.out.println("management update");
		for (int i=0;i<this.collectionOfOrders.size();i++){
			if(this.collectionOfOrders.get(i).getOrderStatus()==3){
				RunnableDeliveryPerson deliveryPerson=this.findUnBusyDeliveryPerson();
				deliveryPerson.addDeliverdOrder(this.collectionOfOrders.get(i));
			}
			else{
				this.receiveAllOrders=false;
			}
		}
	}

}
