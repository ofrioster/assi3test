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
	private Statistics statistics;
	
	public RunnableDeliveryPerson(){
		this.collectionDeliverdOrders=new Vector<Order>();
		this.shutDown=false;
		this.totalDeliveryTime=0;
	}
	
	public RunnableDeliveryPerson(String deliveryPersonName,Address restaurantAddres,Double speedOfDeliveryPerson,CountDownLatch ordersLatch,Statistics statistics){
		this.deliveryPersonName=deliveryPersonName;
		this.restaurantAddres=restaurantAddres;
		this.speedOfDeliveryPerson=speedOfDeliveryPerson;
		this.shutDown=false;
		this.totalDeliveryTime=0;
		this.collectionDeliverdOrders=new Vector<Order>();
		this.ordersLatch=ordersLatch;
		this.statistics=statistics;
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
	//	System.out.println("size "+this.collectionDeliverdOrders.size());
		if (!this.collectionDeliverdOrders.isEmpty()){
			this.collectionDeliverdOrders.remove(0);
		}
//		System.out.println("000 deliverOrder "+orderToDeliver.getOrderID());
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
		this.statistics.addDeliveredOrder(orderToDeliver);
//		this.collectionDeliverdOrders.remove(orderToDeliver);
//		System.out.println("count down count "+this.ordersLatch.getCount());
		this.ordersLatch.countDown();
		System.out.println("count down count "+this.ordersLatch.getCount());
		
		
		
	}

	public void addOrder(Order newOrder){
	//	System.out.println("addOrder(Order newOrder)");
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
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!this.collectionDeliverdOrders.isEmpty()){
				this.deliverOrder(this.collectionDeliverdOrders.get(0));
			}
			
		}
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
	public String toString(){
		String res=" deliveryPersonName- "+this.deliveryPersonName+" restaurantAddres- "+this.restaurantAddres.toString()+" speedOfDeliveryPerson- "+this.speedOfDeliveryPerson+" collectionDeliverdOrders- "+this.collectionDeliverdOrders.toString()+" shutDown- "+this.shutDown+" totalDeliveryTime- "+this.totalDeliveryTime+" ordersLatch- "+this.ordersLatch.getCount();
		return res;
	}

}
