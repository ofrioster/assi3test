package run;
import java.util.Vector;

public class CallableCookWholeOrder implements CallableCookWholeOrderInterface, Runnable{

	private Vector<Dish> dishVector;
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
		for(int i=0;i<order.getOrderDish().size();i++){
			this.dishVector.add(order.getOrderDish().get(i).gestDish());
		}
	}
	
	public void addDish(Dish newDish){
		this.dishVector.add(newDish);
	}
	
	public Boolean IsOrderIsDone(){
		if (this.orderFinish){
			this.orderFinish=true;
			for (int i=0; i<this.threadsVector.size() && this.orderFinish;i++){
				if(!this.threadsVector.get(i).isAlive()){
					this.orderFinish=false;
				}
			}
		}
		return this.orderFinish;
	}
	// start threads to cook the orders
	public void run(){
		this.orderFinish=false;
		this.startTime=System.currentTimeMillis();
		for (int i=0; i<dishVector.size();i++){
			RunnableCookOneDish r= new RunnableCookOneDish(this.dishVector.get(i), warehouseName, chef);
			Thread t= new Thread(r);
			t.start();
			this.threadsVector.add(t);
		}
		while (!this.IsOrderIsDone());
		this.endTime=System.currentTimeMillis();
		this.totalTime=this.endTime-this.startTime;
		
	}
	
	public Long getTotalCookingTime(){
		return this.totalTime;
	}
}
