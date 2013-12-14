package run;
import java.util.Vector;

public class CallableCookWholeOrder implements CallableCookWholeOrderInterface{

	private Vector<RunnableCookOneDish> orderObject;
	private Warehouse warehouseName;
	private Boolean orderFinish;
	
	
	
	public CallableCookWholeOrder(){
		this.orderFinish=false;
		
	}
	public CallableCookWholeOrder(Warehouse warehouse){
		this.warehouseName=warehouse;
		this.orderFinish=false;
	}
	
	public CallableCookWholeOrder(Vector<RunnableCookOneDish> orderObject,Warehouse warehouseName){
		this.orderObject=orderObject;
		this.warehouseName=warehouseName;
		this.orderFinish=false;
	}
}
