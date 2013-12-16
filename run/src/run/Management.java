package run;
import java.util.Vector;

public class Management implements ManagementInterface {
	
	private Vector<Order> collectionOfOrders;
	private Vector<RunnableChef> collectionOfChefs;
	private Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson;
	private Warehouse warehouseName;
	private Statistics statistics;
	private long numberOfOrderThatProcess;
	
	public Management(){
		
	}
	
	public Management(Vector<Order> collectionOfOrders,Vector<RunnableChef> collectionOfChefs,Vector<RunnableDeliveryPerson> collectionOfDeliveryPerson,Warehouse warehouseName){
		this.collectionOfOrders=collectionOfOrders;
		this.collectionOfChefs=collectionOfChefs;
		this.collectionOfDeliveryPerson=collectionOfDeliveryPerson;
		this.warehouseName=warehouseName;
	}
	
	public void addOrder(Order newOrder){
		this.collectionOfOrders.add(newOrder);
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
	public void startCooking();
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
	 */
	public synchronized RunnableChef findUnbusyChef(Order newOrder){
		try{
			RunnableChef res=this.collectionOfChefs.get(0);
			for (int i=0; i<this.collectionOfChefs.size();i++){
				if (res.getCurrectPressure()<this.collectionOfChefs.get(i).getCurrectPressure() && this.collectionOfChefs.get(i).canTheChefTakeOrder(newOrder)){
					res=this.collectionOfChefs.get(0);
				}
			}
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


}
