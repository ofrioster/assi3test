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
	
	public  boolean getKitchenTool(){
		Boolean res=false;
		synchronized (kitchenToolSemaphore) {
			while (!this.kitchenToolSemaphore.tryAcquire()){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(this.kitchenToolSemaphore.tryAcquire()){
				res=true;
			}
			else {
				res=false;
			}
		}
		return res;
		
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
	public String toString(){
		String res=" kitchenToolName- "+this.kitchenToolName+" amount of kitchenTool"+this.kitchenToolSemaphore.availablePermits();
		return res;
	}

}
	



