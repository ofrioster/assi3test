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
	
	/** (non-Javadoc)
	 * @ sub 1 kitchen tool from the warehouse
	 * @ if the kitchen tool is not in the warehouse the thread wait
	 */
	public synchronized boolean getKitchenTool(){
		Boolean res=false;
			while (!this.kitchenToolSemaphore.tryAcquire()){
				try {
					this.wait();
				} catch (InterruptedException e) {
					System.out.println("getKitchenTool fail");
					e.printStackTrace();
				}
			}
			res=true;
		return res;
	}
	/**
	 * @return Boolean of if all the tools can acquire
	 */
	public synchronized boolean getKitchenToolUnsynchronized(){
		Boolean res=false;
			if(this.kitchenToolSemaphore.tryAcquire()){
				res=true;
			}
		return res;
	}
	
	public synchronized void returnKitchenTool(){
		this.kitchenToolSemaphore.release();
		this.notifyAll();
	}
	/** (non-Javadoc)
	 * @ return only not use kitchen tools!!
	 * @ dose not have this.notifyAll(); method
	 */
	public synchronized void returnUnuseKitchenTool(){
			this.kitchenToolSemaphore.release();
		}
	
	public int numberOfKitchenTools(){
		return this.kitchenToolSemaphore.availablePermits();
	}
	public int getNumberOfKitchenTool(){
		return this.kitchenToolSemaphore.availablePermits();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KitchenTool [kitchenToolName=");
		builder.append(kitchenToolName);
		builder.append(", kitchenToolSemaphore=");
		builder.append(kitchenToolSemaphore.availablePermits());
		builder.append("]");
		return builder.toString();
	}

}
