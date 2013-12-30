package run;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Vector;


public class Management implements ManagementInterface,Runnable {
	
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
	private Boolean receiveAllOrders; 
	private Boolean shutDown;
	private int orderCount;
	
	public Management(){
		
	}
	
	public Management(Vector<Order> collectionOfOrders,Vector<RunnableChef> collectionOfChefs,Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson,Warehouse warehouseName,Statistics statistics){
		this.collectionOfOrders=collectionOfOrders;
		this.collectionOfOrdersToCock=this.copyOrdersVector(collectionOfOrders);
		this.collectionOfChefs=collectionOfChefs;
		this.collectionOfDeliveryPerson=collectionOfDeliveryPerson;
		this.warehouseName=warehouseName;
		this.receiveAllOrders=false;
		this.shutDown=false;
		this.statistics=statistics;
		this.orderCount=0;
		this.collectionOfOrdersToDeliver=new Vector<Order>();
		this.collectionOfChefsArryList=new ArrayList<RunnableChef>();
			for (int i=0; i<this.collectionOfChefs.size();i++){
				this.collectionOfChefsArryList.add(this.collectionOfChefs.get(i));
			}
			this.collectionOfOrdersToCockArrayList=new ArrayList<Order>();
		for (int i=0; i<this.collectionOfOrdersToCock.size();i++){
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
	/** (non-Javadoc)
	 * @add order to chef
	 */
	public synchronized void startToCookDish(Order orderToCook){
		//System.out.println("managemant- startToCookDish- befor order ID-"+orderToCook.getOrderID());
		RunnableChef chef=this.findUnbusyChef(orderToCook);
	//	System.out.println("managemant- startToCookDish- befor order ID-"+orderToCook.getOrderID());
		chef.addOrder(orderToCook);
		if(!chef.isAlive()){
			Thread t=new Thread(chef);
			t.start();
		}
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
					if (res.getCurrectPressure()>this.collectionOfChefsArryList.get(i).getCurrectPressure() && this.collectionOfChefs.get(i).canTheChefTakeOrder(newOrder)){
						res=this.collectionOfChefsArryList.get(i);
					}
				}
				if (res.canTheChefTakeOrder(newOrder)){
					return res;
				}
			}
			catch (Exception e) {
			}
		}
		RunnableChef e=new RunnableChef();
		return e;
	}
	/** (non-Javadoc)
	 * @ add the order that has been finish to statistics
	 */
	public void addOrderToStatistics(Order finishOrder){
		this.statistics.addDeliveredOrder(finishOrder);
	}
	/** (non-Javadoc)
	 * @return the total money the gain
	 */
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
	 * @start the Management that will stop only when the orders has finish
	 */
	public synchronized void run(){
	    logger.log(Level.INFO, "Initializing simulation process...");
	    logger.log(Level.INFO, "System contains: " +  "[chefs=" + collectionOfChefs.size() + "][deliveryPeople=" + collectionOfDeliveryPerson.size() + "][orders="+collectionOfOrders.size()+"]");
	    this.collectionOfChefsArryList=this.sortRunnableChefArray(this.collectionOfChefsArryList);
	    this.collectionOfOrdersToCockArrayList=this.sortOrderArray(this.collectionOfOrdersToCockArrayList);
		this.sendCollectionOfOrdersToDeliverToChef();
		this.setChefWarehouse();
		while (!this.shutDown && (this.collectionOfOrdersToCockArrayList.size()>0 )){
			logger.log(Level.INFO, "Orders To Cook:"+ this.collectionOfOrdersToCockArrayList.size());
			if(this.collectionOfOrdersToCockArrayList.size()>0){
					logger.log(Level.INFO, "Attempting to send order:"+this.collectionOfOrdersToCockArrayList.get(0).toStringForLogger());
					this.startToCookDish(this.collectionOfOrdersToCockArrayList.get(0));
					this.collectionOfOrdersToCockArrayList.remove(0);
			}
			this.update1();
		}
		while(!this.shutDown){
			this.update1();
		}
	}
	
	
	public void setReceiveAllOrders(Boolean receiveAllOrders){
		this.receiveAllOrders=receiveAllOrders;
	}
	public Boolean getReceiveAllOrders(){
		return this.receiveAllOrders;
	}
	public void ShutDown( ){
		for (int i=0;i<this.collectionOfChefs.size();i++){
			this.collectionOfChefs.get(i).shutDown();
		}
		for (int i=0;i<this.collectionOfDeliveryPerson.size();i++){
			this.collectionOfDeliveryPerson.get(i).shutDown();
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
			e.printStackTrace();
		}
		this.receiveAllOrders=true;
		for (int i=this.orderCount;i<this.collectionOfOrdersToDeliver.size();i++){
			this.orderCount++;
			if(this.collectionOfOrdersToDeliver.get(i).getOrderStatus()==3){
				RunnableDeliveryPerson deliveryPerson=this.findUnBusyDeliveryPerson();
				deliveryPerson.addDeliverdOrder(this.collectionOfOrdersToDeliver.get(i));
				if (!deliveryPerson.isAlive()){
					Thread t=new Thread(deliveryPerson);
					t.start();
				}
			}
			else{
				this.receiveAllOrders=false;
			}
		}
	}

	public void waitUntilAllOrdersDeliverd(){
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
