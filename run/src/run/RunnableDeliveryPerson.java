package run;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.lang.Math;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RunnableDeliveryPerson implements RunnableDeliveryPersonInterface, Runnable{
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private String deliveryPersonName;
	private Address restaurantAddres; //the type might change we get (x,y)
	private Double speedOfDeliveryPerson;
	private ArrayList<Order> collectionDeliverdOrders;
	private Boolean shutDown;
	private long totalDeliveryTime;
	CountDownLatch ordersLatch;
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
	//	System.out.println("8888 Order add- "+order.getOrderID());
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
	//	System.out.println("size "+this.collectionDeliverdOrders.size());
		if (!this.collectionDeliverdOrders.isEmpty()){
			this.collectionDeliverdOrders.remove(0);
		}
		
		//System.out.println("000 deliverOrder ID: "+orderToDeliver.getOrderID());
		long startTime=System.currentTimeMillis();
		try {
			Thread.sleep(calculateDeliveryTime(calculateDeliveryDistance(orderToDeliver.getCustomerAddress())));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orderToDeliver.setExpectedDeliveryTime(System.currentTimeMillis()-startTime);
		orderToDeliver.setOrderStatus(4);
		orderToDeliver.setActualDeliveryTime(System.currentTimeMillis()-orderToDeliver.getActualDeliveryTime());
		orderToDeliver.setTotalReward();
		this.statistics.addDeliveredOrder(orderToDeliver);
//		this.collectionDeliverdOrders.remove(orderToDeliver);
//		System.out.println("count down count "+this.ordersLatch.getCount());
//		removeFinishOrder(orderToDeliver);
		this.ordersLatch.countDown();
		System.out.println("Order ID: "+ orderToDeliver.getOrderID()+" count down count "+this.ordersLatch.getCount());
		
		logger.log(Level.INFO, "Order DELIVERED:" + orderToDeliver.toStringTimes());
		
	}

	public void addOrder(Order newOrder){
	//	System.out.println("addOrder(Order newOrder)");
		this.totalDeliveryTime=this.totalDeliveryTime+ this.calculateDeliveryTime(calculateDeliveryDistance(newOrder.getCustomerAddress()));
		newOrder.setActualDeliveryTime(System.currentTimeMillis());
		this.collectionDeliverdOrders.add(newOrder);
	}
	public void shutDown(){
		this.shutDown=true;
		logger.log(Level.INFO, "DeliveryPerson has been shut down.");
		
//		System.out.println("deliveryPersonName: "+ this.deliveryPersonName+ " shutdown");
	}
	public void run(){
	//	System.out.println("run");
		this.isAlive=true;
		while (!this.shutDown&&this.isAlive){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
//	public String toString(){
//		String res=" deliveryPersonName- "+this.deliveryPersonName+" restaurantAddres- "+this.restaurantAddres.toString()+" speedOfDeliveryPerson- "+this.speedOfDeliveryPerson+" collectionDeliverdOrders- "+this.collectionDeliverdOrders.toString()+" shutDown- "+this.shutDown+" totalDeliveryTime- "+this.totalDeliveryTime+" ordersLatch- "+this.ordersLatch;
//		return res;
//	}
//	
	public void removeFinishOrder(Order orderHasDeliver){
		for (int i=0;i<this.collectionDeliverdOrders.size();i++){
			if (this.collectionDeliverdOrders.get(i).getOrderID().equals(orderHasDeliver.getOrderID())){
				this.collectionDeliverdOrders.remove(i);
			}
		}
	}

	@Override
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
