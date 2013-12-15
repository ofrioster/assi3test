package run;
import java.util.Vector;

public class CallableCookWholeOrder implements CallableCookWholeOrderInterface, Runnable{

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
		for(int i=0;i<order.getOrderDish().size();i++){
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
		this.orderFinish=false;
		this.startTime=System.currentTimeMillis();
		for (int i=0; i<dishOrderVector.size();i++){
			for (int k=0; k<this.dishOrderVector.get(i).getquantity();k++){
				this.dishOrderVector.get(i).setOneDishIsDone();
				RunnableCookOneDish r= new RunnableCookOneDish(this.dishOrderVector.get(i), warehouseName, chef);
				Thread t= new Thread(r);
				t.start();
				this.threadsVector.add(t);
			}
		}
		while (!this.IsOrderIsDone());
		this.endTime=System.currentTimeMillis();
		this.totalTime=this.endTime-this.startTime;
		this.order.setActualCookTime(this.totalTime);
		order.setOrderStatus(3);
		///*** deliver order***////
		
		
	}
	
	public Long getTotalCookingTime(){
		return this.totalTime;
	}
}
