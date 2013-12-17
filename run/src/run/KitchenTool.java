package run;
import java.util.concurrent.*;

public class KitchenTool implements KitchenTool_Interface {

	private String kitchenToolName;
	private Semaphore kitchenToolSemaphore;
	
	
	public KitchenTool(String name,int amount) {
		this.kitchenToolName=name;
		this.kitchenToolSemaphore=new Semaphore(amount);
	}
	
<<<<<<< HEAD
	
	
	public KitchenTool(){
		
=======
	public KitchenTool(){	
	}
	public KitchenTool(KitchenTool kitchenTool){
		this.kitchenToolName=kitchenTool.getKitchenToolName();
		this.kitchenToolSemaphore=new Semaphore(kitchenTool.getNumberOfKitchenTool());
>>>>>>> 49131add2ce93285c7ab667196365bf42faa2997
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
	



