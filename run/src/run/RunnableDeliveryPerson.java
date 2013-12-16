package run;
import java.util.Vector;
import java.lang.Math;

public class RunnableDeliveryPerson implements RunnableDeliveryPersonInterface, Runnable{
	
	private String deliveryPersonName;
	private Double[] restaurantAddres; //the type might change we get (x,y)
	private Double speedOfDeliveryPerson;
	private Vector<Order> collectionDeliverdOrders;
	private Boolean shutDown;
	private long totalDeliveryTime;
	
	public RunnableDeliveryPerson(){
		
	}
	
	public RunnableDeliveryPerson(String deliveryPersonName,Double[] restaurantAddres,Double speedOfDeliveryPerson){
		this.deliveryPersonName=deliveryPersonName;
		this.restaurantAddres=restaurantAddres;
		this.speedOfDeliveryPerson=speedOfDeliveryPerson;
		this.shutDown=false;
		this.totalDeliveryTime=0;
	}
	
	public String getDeliveryPersonName(){
		return this.deliveryPersonName;
	}
	public Double[] getRestaurantAddres(){
		return this.restaurantAddres;
	}
	public Double getSpeedOfDeliveryPerson(){
		return this.speedOfDeliveryPerson;
	}
	public void addDeliverdOrder(Order order){
		this.collectionDeliverdOrders.add(order);
	}
	public Double calculateDeliveryDistance(Double[] deliveryAddress){
		Double x=this.restaurantAddres[0]-deliveryAddress[0];
		Double y=this.restaurantAddres[1]-deliveryAddress[1];
		x=x*x;
		y=y*y;
		Double res=Math.sqrt(x+y);
		return res;
	}
	public long calculateDeliveryTime(Double distance){
		long res;
		res=Math.round(distance/this.speedOfDeliveryPerson);
		return res;
		
		
	}
	public void deliverOrder(Order orderToDeliver){
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
		
		
		
	}

	public void addOrder(Order newOrder){
		this.totalDeliveryTime=this.totalDeliveryTime+ this.calculateDeliveryTime(calculateDeliveryDistance(newOrder.getCustomerAddress()));
		newOrder.setActualDeliveryTime(System.currentTimeMillis());
		this.collectionDeliverdOrders.add(newOrder);
	}
	public void shutDown(){
		this.shutDown=true;
	}
	public void run(){
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
