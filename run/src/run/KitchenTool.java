package run;
import java.util.concurrent.*;

public class KitchenTool implements KitchenTool_Interface {

	private String kitchenToolName;
//	private int kitchenToolAmount;
	private Semaphore kitchenToolSemaphore;
	
	
	public KitchenTool(String name,int amount) {
	//	this.kitchenToolAmount=amount;
		this.kitchenToolName=name;
		this.kitchenToolSemaphore=new Semaphore(amount);
	}
	
	public KitchenTool(){
		
	}
	
	
	public String getKitchenToolName(){
		return this.kitchenToolName; 
	}
	
	public synchronized  boolean getKitchenTool(){
		if(this.kitchenToolSemaphore.tryAcquire()){
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void returnKitchenTool(){
		this.kitchenToolSemaphore.release();
	}
	
	public int numberOfKitchenTools(){
		return this.kitchenToolSemaphore.availablePermits();
	}
}
	



