package run;
import java.util.Vector;
import java.util.Observable;
import java.util.Observer;


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
	
	
	
	
	
	public CallableCookWholeOrder(Order order,Warehouse warehouseName){
		this.order=order;
		this.warehouseName=warehouseName;
		this.orderFinish=false;
		this.totalTime=-1;
		this.dishOrderVector=new Vector<OrderOfDish>();
		this.threadsVector=new Vector<Thread>();
		for(int i=0;i<order.getOrderDish().size();i++){
			System.out.println("order.getOrderDish().size() "+order.getOrderDish().size());
			this.dishOrderVector.add(order.getOrderDish().get(i));
		}
	}
	
	public void addDish(OrderOfDish newDish){
		this.dishOrderVector.add(newDish);
	}
	
	public Boolean IsOrderIsDone(){
		if (!this.orderFinish){
			this.orderFinish=true;
			for (int i=0; i<this.threadsVector.size() && this.orderFinish;i++){
				if(this.dishOrderVector.get(i).getOrderStatus()!=3){
					this.orderFinish=false;
				}
			}
		}
		return this.orderFinish;
	}
	// start threads to cook the orders
	public void run(){
		System.out.println("run cook whole order has start");
		this.orderFinish=false;
		this.startTime=System.currentTimeMillis();
		for (int i=0; i<dishOrderVector.size();i++){
			for (int k=0; k<this.dishOrderVector.get(i).getquantity();k++){
				System.out.println(this.dishOrderVector.get(i).getquantity());
				this.dishOrderVector.get(i).setOneDishIsDone();
				RunnableCookOneDish r= new RunnableCookOneDish(this.dishOrderVector.get(i), warehouseName, chef);
				Thread t= new Thread(r);
				t.start();
				this.threadsVector.add(t);
			}
		}
		System.out.println("run cook whole order has finish");
		/*/// doing that in update
		while (!this.IsOrderIsDone());
		this.endTime=System.currentTimeMillis();
		this.totalTime=this.endTime-this.startTime;
		this.order.setActualCookTime(this.totalTime);
		order.setOrderStatus(3);
		notifyObservers(this.order);
		*/
	}
	  public void update(Observable obj, Boolean finish) {
		  System.out.println("update cook whole order has start");
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
				  notifyObservers(this.order); 
			  }
			  
		  }
	  }
	
	public Long getTotalCookingTime(){
		return this.totalTime;
	}
}
