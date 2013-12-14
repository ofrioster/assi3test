package run;
import java.util.Vector;

public class CallableCookWholeOrder implements CallableCookWholeOrderInterface{

	//private Vector<RunnableCookOneDish> orderObject;
	private Vector<Dish> dishVector;
	private Warehouse warehouseName;
	private Boolean orderFinish;
	private RunnableChef chef;
	private Vector<Thread> threadsVector;
	
	
	
	public CallableCookWholeOrder(){
		this.orderFinish=false;
		
	}
	public CallableCookWholeOrder(Warehouse warehouse){
		this.warehouseName=warehouse;
		this.orderFinish=false;
	}
	
	public CallableCookWholeOrder(Vector<Dish> dishVector,Warehouse warehouseName){
		this.dishVector=dishVector;
		this.warehouseName=warehouseName;
		this.orderFinish=false;
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
		for (int i=0; i<dishVector.size();i++){
			RunnableCookOneDish r= new RunnableCookOneDish(this.dishVector.get(i), warehouseName, chef);
			Thread t= new Thread(r);
			t.start();
			this.threadsVector.add(t);
		}
		
	}
}
