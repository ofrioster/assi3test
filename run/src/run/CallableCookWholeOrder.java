package run;
import java.util.Vector;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CallableCookWholeOrder extends Observable implements CallableCookWholeOrderInterface, Runnable,Callable<Order>{
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private Vector<OrderOfDish> dishOrderVector;
	private Order order;
	private Warehouse warehouseName;
	private Boolean orderFinish;
	private RunnableChef chef;
	private Vector<Thread> threadsVector;
	private Long startTime;
	private Long endTime;
	private long totalTime;
	private Statistics statistics;
	private int totalNumberOfDishs;
	private CountDownLatch NumberOfDishesLeftToCock;
	private Vector<Order> collectionOfOrdersToDeliver;
	private Management management;
	
	
	
	
	
	public CallableCookWholeOrder(Order order,Warehouse warehouseName,RunnableChef chef,Statistics statistics,Vector<Order> collectionOfOrdersToDeliver,Management management){
		this.order=order;
		this.warehouseName=warehouseName;
		this.orderFinish=false;
		this.totalTime=-1;
		this.dishOrderVector=new Vector<OrderOfDish>();
		this.threadsVector=new Vector<Thread>();
		for(int i=0;i<order.getOrderDish().size();i++){
			this.dishOrderVector.add(order.getOrderDish().get(i));
		}
		this.chef=chef;
		this.statistics=statistics;
		this.updateTotalNumberOfDishs();
		this.NumberOfDishesLeftToCock = new CountDownLatch (this.totalNumberOfDishs);
		this.collectionOfOrdersToDeliver=collectionOfOrdersToDeliver;
		this.management=management;
	}
	
	public void addDish(OrderOfDish newDish){
		this.dishOrderVector.add(newDish);
	}
	
	public synchronized Boolean IsOrderIsDone(){
		if (!this.orderFinish){
			this.orderFinish=true;
			for (int i=0; i<this.dishOrderVector.size() ;i++){
				int k=this.dishOrderVector.get(i).getOrderStatus();
				if(k!=3){
					this.orderFinish=false;
				}
			}
		}
		return this.orderFinish;
	}

	public void run(){
		this.orderFinish=false;
		this.startTime=System.currentTimeMillis();
		for (int i=0; i<dishOrderVector.size();i++){
			for (int k=0; k<this.dishOrderVector.get(i).getquantity();k++){
				this.dishOrderVector.get(i).setOneDishIsDone();
				RunnableCookOneDish r= new RunnableCookOneDish(this.dishOrderVector.get(i), warehouseName, chef,this.NumberOfDishesLeftToCock);
				Thread t= new Thread(r);
				t.start();
				this.threadsVector.add(t);
			}
		}
		try {
			this.NumberOfDishesLeftToCock.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.update1();
		this.endTime=System.currentTimeMillis();
		this.totalTime=this.endTime-this.startTime;
		order.setOrderStatus(3);
		this.collectionOfOrdersToDeliver.add(this.order);
	}
	public Order call() throws InterruptedException{
		logger.log(Level.INFO, "[CallableCookWholeOrder][orderId="+ this.order.getOrderID()+"][numberOfMeals="+this.totalNumberOfDishs+"]");		
		this.orderFinish=false;
		this.startTime=System.currentTimeMillis();
		for (int i=0; i<dishOrderVector.size();i++){
			for (int k=0; k<this.dishOrderVector.get(i).getquantity();k++){
				this.dishOrderVector.get(i).setOneDishIsDone();
				RunnableCookOneDish r= new RunnableCookOneDish(this.dishOrderVector.get(i), warehouseName, chef,this.NumberOfDishesLeftToCock);
				Thread t= new Thread(r);
				t.start();
				this.threadsVector.add(t);
			}
		}
		logger.log(Level.INFO, "[CallableCookWholeOrder] waiting for "+ this.totalNumberOfDishs +" meals to finish simulating...");
		try {
			this.NumberOfDishesLeftToCock.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.update1();
		this.endTime=System.currentTimeMillis();
		this.totalTime=this.endTime-this.startTime;
		this.order.setActualCookTime(this.totalTime);
		order.setOrderStatus(3);
		logger.log(Level.INFO, "[CallableCookWholeOrder] All meals have been cooked!");
		logger.log(Level.INFO, "Order COMPLETE:" + order.getOrderID());
		this.collectionOfOrdersToDeliver.add(this.order);
		return this.order;
		
	}
	
	  public void update1() {
			  Boolean orderFinish=true;
			  for (int i=0; i<this.dishOrderVector.size() && orderFinish;i++){
				  if (this.dishOrderVector.get(i).getOrderStatus()!=3){
					  orderFinish=false;
				  }
			  }
			  if (orderFinish){
				  order.setOrderStatus(3);
				  notifyObservers(this.order);
			  }
	  }
	public Long getTotalCookingTime(){
		return this.totalTime;
	}
	public void addObserver1(Observer o) {
		
	}
	public void updateTotalNumberOfDishs(){
		this.totalNumberOfDishs=0;
		for (int i=0;i<this.dishOrderVector.size();i++){
			this.totalNumberOfDishs=this.totalNumberOfDishs+ this.dishOrderVector.get(i).getquantity();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CallableCookWholeOrder [dishOrderVector=");
		builder.append(dishOrderVector);
		builder.append(", order=");
		builder.append(order);
		builder.append(", warehouseName=");
		builder.append(warehouseName);
		builder.append(", orderFinish=");
		builder.append(orderFinish);
		builder.append(", chef=");
		builder.append(chef);
		builder.append(", threadsVector=");
		builder.append(threadsVector);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", totalTime=");
		builder.append(totalTime);
		builder.append(", statistics=");
		builder.append(statistics);
		builder.append(", totalNumberOfDishs=");
		builder.append(totalNumberOfDishs);
		builder.append(", NumberOfDishesLeftToCock=");
		builder.append(NumberOfDishesLeftToCock);
		builder.append(", collectionOfOrdersToDeliver=");
		builder.append(collectionOfOrdersToDeliver);
		builder.append(", management=");
		builder.append(management);
		builder.append("]");
		return builder.toString();
	}
}
