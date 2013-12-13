package run;

public class KitchenTool implements KitchenTool_Interface {

	private String kitchenToolName;
	private int kitchenToolAmount;
	
	
	public KitchenTool(String name,int amount) {
		this.kitchenToolAmount=amount;
		this.kitchenToolName=name;
	}
}



