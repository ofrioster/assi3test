package run;
import java.util.Vector;

public class RunnableCookOneDish implements RunnableCookOneDishInterface{
	
	private Dish dishName;
	private Warehouse warehouseName;
	private RunnableChef Chef;
	private Boolean allKitchenToolsAcquire;
	private Vector<KitchenTool> kitchenToolsInUse;
	
	public RunnableCookOneDish(Dish dishName,Warehouse warehouseName,RunnableChef Chef){
		this.dishName=dishName;
		this.warehouseName=warehouseName;
		this.Chef=Chef;
		this.allKitchenToolsAcquire=false;
		
	}
	

}
