package run;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.lang.Math;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RunnableDeliveryPerson implements RunnableDeliveryPersonInterface, Runnable{
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private String deliveryPersonName;
	private Address restaurantAddres; 
	private Double speedOfDeliveryPerson;
	private ArrayList<Order> collectionDeliverdOrders;
	private Boolean shutDown;
	private long totalDeliveryTime;
	private CountDownLatch ordersLatch;
	private Statistics statistics;
	private Boolean isAlive;
	
	public RunnableDeliveryPerson(){
		this.collectionDeliverdOrders=new ArrayList<Order>();
		this.shutDown=false;
		this.totalDeliveryTime=0;
		this.isAlive=false;
	}
	
	public RunnableDeliveryPerson(String deliveryPersonName,Address restaurantAddres,Double speedOfDeliveryPerson,CountDownLatch ordersLatch,Statistics statistics){
		this.deliveryPersonName=deliveryPersonName;
		this.restaurantAddres=restaurantAddres;
		this.speedOfDeliveryPerson=speedOfDeliveryPerson;
		this.shutDown=false;
		this.totalDeliveryTime=0;
		this.collectionDeliverdOrders=new ArrayList<Order>();
		this.ordersLatch=ordersLatch;
		this.statistics=statistics;
		this.isAlive=false;
	}
	public void setStatistics(Statistics statistics){
		this.statistics=statistics;
	}
	public String getDeliveryPersonName(){
		return this.deliveryPersonName;
	}
	public Address getRestaurantAddres(){
		return this.restaurantAddres;
	}
	public Double getSpeedOfDeliveryPerson(){
		return this.speedOfDeliveryPerson;
	}
	public void addDeliverdOrder(Order order){
		this.collectionDeliverdOrders.add(order);
	}
	public Double calculateDeliveryDistance(Address deliveryAddress){
		return this.restaurantAddres.calculateDistance(deliveryAddress);
		}
	public long calculateDeliveryTime(Double distance){
		long res;
		res=Math.round(distance/this.speedOfDeliveryPerson);
		return res;
		
		
	}
	public synchronized void deliverOrder(Order orderToDeliver){
		orderToDeliver.setActualDeliveryTime(System.currentTimeMillis());
		if (!this.collectionDeliverdOrders.isEmpty()){
			this.collectionDeliverdOrders.remove(0);
		}
		long startTime=System.currentTimeMillis();
		try {
			Thread.sleep(calculateDeliveryTime(calculateDeliveryDistance(orderToDeliver.getCustomerAddress())));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		orderToDeliver.setExpectedDeliveryTime(System.currentTimeMillis()-startTime);
		orderToDeliver.setOrderStatus(4);
		orderToDeliver.setActualDeliveryTime(System.currentTimeMillis()-orderToDeliver.getActualDeliveryTime());
		orderToDeliver.setTotalReward();
		this.statistics.addDeliveredOrder(orderToDeliver);
		this.ordersLatch.countDown();
		logger.log(Level.INFO, "Order DELIVERED:" + orderToDeliver.toStringTimes());
		
	}

	public void addOrder(Order newOrder){
		this.totalDeliveryTime=this.totalDeliveryTime+ this.calculateDeliveryTime(calculateDeliveryDistance(newOrder.getCustomerAddress()));
		newOrder.setActualDeliveryTime(System.currentTimeMillis());
		this.collectionDeliverdOrders.add(newOrder);
	}
	public void shutDown(){
		this.shutDown=true;
		logger.log(Level.INFO, "DeliveryPerson has been shut down.");
	}
	public void run(){
		this.isAlive=true;
		while (!this.shutDown&&this.isAlive){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!this.collectionDeliverdOrders.isEmpty()){
				this.deliverOrder(this.collectionDeliverdOrders.get(0));
			}
			else if(this.collectionDeliverdOrders.isEmpty()){
				this.isAlive=false;
			}
			
		}
	}
	public Boolean isAlive(){
		return this.isAlive;
	}


	/** (non-Javadoc)
	 * @totalDeliveryTime in milis
	 */
	public long getTotalDeliveryTime() {
		return this.totalDeliveryTime;
	}

	public void setDeliveryPersonName(String PersonName) {
		this.deliveryPersonName = PersonName;	

		
	}

	public void setSpeedOfDeliveryPerson(Double DeliveryPerson) {
		this.speedOfDeliveryPerson = DeliveryPerson;
	}

	public void setRestaurantAddres(Address address) {
		this.restaurantAddres = address;
	}

	public void setordersLatch(CountDownLatch latchObject) {
		this.ordersLatch = 	latchObject;
	}
	public void removeFinishOrder(Order orderHasDeliver){
		for (int i=0;i<this.collectionDeliverdOrders.size();i++){
			if (this.collectionDeliverdOrders.get(i).getOrderID().equals(orderHasDeliver.getOrderID())){
				this.collectionDeliverdOrders.remove(i);
			}
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RunnableDeliveryPerson [deliveryPersonName=");
		builder.append(deliveryPersonName);
		builder.append(", restaurantAddres=");
		builder.append(restaurantAddres);
		builder.append(", speedOfDeliveryPerson=");
		builder.append(speedOfDeliveryPerson);
		builder.append(", collectionDeliverdOrders=");
		builder.append(collectionDeliverdOrders);
		
		for ( Order order : collectionDeliverdOrders){
			builder.append("\n Order:");
			builder.append(order);
		}
		builder.append("totalDeliveryTime=");
		builder.append(totalDeliveryTime);
		builder.append(", ordersLatch=");
		builder.append(ordersLatch);
		builder.append("]");
		return builder.toString();
	}

}
