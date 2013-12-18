package run;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.lang.Math;

public class RunnableDeliveryPerson implements RunnableDeliveryPersonInterface, Runnable{
	
	private String deliveryPersonName;
	private Address restaurantAddres; //the type might change we get (x,y)
	private Double speedOfDeliveryPerson;
	private Vector<Order> collectionDeliverdOrders;
	private Boolean shutDown;
	private long totalDeliveryTime;
	CountDownLatch ordersLatch;
	
	public RunnableDeliveryPerson(){
		
	}
	
	public RunnableDeliveryPerson(String deliveryPersonName,Address restaurantAddres,Double speedOfDeliveryPerson,CountDownLatch ordersLatch){
		this.deliveryPersonName=deliveryPersonName;
		this.restaurantAddres=restaurantAddres;
		this.speedOfDeliveryPerson=speedOfDeliveryPerson;
		this.shutDown=false;
		this.totalDeliveryTime=0;
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
	public void deliverOrder(Order orderToDeliver){
		System.out.println("deliverOrder(Order orderToDeliver)");
		long startTime=System.currentTimeMillis();
		try {
			Thread.sleep(calculateDeliveryTime(calculateDeliveryDistance(orderToDeliver.getCustomerAddress())));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		orderToDeliver.setExpectedDeliveryTime(System.currentTimeMillis()-startTime);
		orderToDeliver.setOrderStatus(4);
		orderToDeliver.setActualCookTime(System.currentTimeMillis()-orderToDeliver.getActualDeliveryTime());
		orderToDeliver.setTotalReward();
		this.collectionDeliverdOrders.remove(orderToDeliver);
		this.ordersLatch.countDown();
		
		
		
	}

	public void addOrder(Order newOrder){
		System.out.println("addOrder(Order newOrder)");
		this.totalDeliveryTime=this.totalDeliveryTime+ this.calculateDeliveryTime(calculateDeliveryDistance(newOrder.getCustomerAddress()));
		newOrder.setActualDeliveryTime(System.currentTimeMillis());
		this.collectionDeliverdOrders.add(newOrder);
	}
	public void shutDown(){
		this.shutDown=true;
	}
	public void run(){
		System.out.println("run");
		while (!this.shutDown){
			if (!this.collectionDeliverdOrders.isEmpty()){
				this.deliverOrder(this.collectionDeliverdOrders.get(0));
			}
			
		}
	}


	/* (non-Javadoc)
	 * @totalDeliveryTime in milis
	 */
	public long getTotalDeliveryTime() {
		return this.totalDeliveryTime;
	}

}
