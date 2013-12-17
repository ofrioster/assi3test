package run;
import java.util.concurrent.*;

public class KitchenTool implements KitchenTool_Interface {

	private String kitchenToolName;
	private Semaphore kitchenToolSemaphore;
	
	
	public KitchenTool(String name,int amount) {
		this.kitchenToolName=name;
		this.kitchenToolSemaphore=new Semaphore(amount);
	}
	
	public KitchenTool(){	
	}
	public KitchenTool(KitchenTool kitchenTool){
		this.kitchenToolName=kitchenTool.getKitchenToolName();
		this.kitchenToolSemaphore=new Semaphore(kitchenTool.getNumberOfKitchenTool());
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
	public int getNumberOfKitchenTool(){
		return this.kitchenToolSemaphore.availablePermits();
	}

}
	



