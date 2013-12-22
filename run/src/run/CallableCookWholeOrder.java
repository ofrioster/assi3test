package run;
import java.util.Vector;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.*;

public class CallableCookWholeOrder extends Observable implements CallableCookWholeOrderInterface, Runnable{

	//private Vector<Dish> dishVector;
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
	
	
	
	
	
	public CallableCookWholeOrder(Order order,Warehouse warehouseName,RunnableChef chef,Statistics statistics){
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
	// start threads to cook the orders
	public void run(){
//		System.out.println("run cook whole order has start");
		this.orderFinish=false;
		this.startTime=System.currentTimeMillis();
		for (int i=0; i<dishOrderVector.size();i++){
			for (int k=0; k<this.dishOrderVector.get(i).getquantity();k++){

	//			System.out.println(this.dishOrderVector.get(i).getquantity());
				this.dishOrderVector.get(i).setOneDishIsDone();
	//			System.out.println("this "+ this.chef.getChefName());
	//			this.dishOrderVector.get(i).setOneDishIsDone();
				RunnableCookOneDish r= new RunnableCookOneDish(this.dishOrderVector.get(i), warehouseName, chef,this.NumberOfDishesLeftToCock);
				Thread t= new Thread(r);
				t.start();
				this.threadsVector.add(t);
			}
		}
	//	System.out.println("run cook whole order has finish");
		/*/// doing that in update
		 */
		/*
		while (!this.IsOrderIsDone()){
		try {
	//		System.out.println("wait works");
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		*/
		try {
			System.out.println("this.NumberOfDishesLeftToCock.getCount() "+this.NumberOfDishesLeftToCock.getCount());
			this.NumberOfDishesLeftToCock.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	System.out.println("await whole order has finish");
		this.update1();
		this.endTime=System.currentTimeMillis();
		this.totalTime=this.endTime-this.startTime;
		this.order.setActualCookTime(this.totalTime);
		order.setOrderStatus(3);
		notifyObservers(this.order);
//		System.out.println("run cook whole order has finish");
		
	}
	  public void update(Observable obj, Boolean finish) {
	//	  System.out.println("update cook whole order has start");
		  long endTime=System.currentTimeMillis();
		  if (finish){
			  Boolean orderFinish=true;
			  for (int i=0; i<this.dishOrderVector.size() && orderFinish;i++){
				  if (this.dishOrderVector.get(i).getOrderStatus()!=3){
					  orderFinish=false;
				  }
			  }
			  if (orderFinish){
				  this.endTime=endTime;
				  this.totalTime=this.endTime-this.startTime;
				  this.order.setActualCookTime(this.totalTime);
				  order.setOrderStatus(3);
		//		  this.statistics.addDeliveredOrder(order);
				  notifyObservers(this.order); 
			  }
			  
		  }
	  }
	
	  public void update1() {
//		  System.out.println("update1 cook whole order has start");
		  long endTime=System.currentTimeMillis();
			  Boolean orderFinish=true;
			  for (int i=0; i<this.dishOrderVector.size() && orderFinish;i++){
				  if (this.dishOrderVector.get(i).getOrderStatus()!=3){
					  orderFinish=false;
				  }
			  }
			  if (orderFinish){
				  this.endTime=endTime;
				  this.totalTime=this.endTime-this.startTime;
				  this.order.setActualCookTime(this.totalTime);
				  order.setOrderStatus(3);
			//	  System.out.println("notifyObservers(this.order);");
				  notifyObservers(this.order);
				  System.out.println("notifyObservers(this.order);");
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
}
