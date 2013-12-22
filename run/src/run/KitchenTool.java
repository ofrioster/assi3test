package run;
import java.util.concurrent.*;

public class KitchenTool implements KitchenTool_Interface {

	private String kitchenToolName;
	private Semaphore kitchenToolSemaphore;
	
	
	public KitchenTool(String name,int amount) {
		this.kitchenToolName=name;
		this.kitchenToolSemaphore=new Semaphore(amount);
	}
	

	
	
	public String getKitchenToolName(){
		return this.kitchenToolName; 
	}
	
	public synchronized  boolean getKitchenTool(){
		while (!this.kitchenToolSemaphore.tryAcquire()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(this.kitchenToolSemaphore.tryAcquire()){
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public synchronized void returnKitchenTool(){
		this.kitchenToolSemaphore.release();
		this.notifyAll();
	}
	
	public int numberOfKitchenTools(){
		return this.kitchenToolSemaphore.availablePermits();
	}
	public int getNumberOfKitchenTool(){
		return this.kitchenToolSemaphore.availablePermits();
	}

}
	



