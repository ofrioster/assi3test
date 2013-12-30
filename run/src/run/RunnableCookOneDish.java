package run;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;


public class RunnableCookOneDish implements RunnableCookOneDishInterface,Runnable{
	
	private OrderOfDish dishName;
	private Warehouse warehouseName;
	private RunnableChef Chef;
	private Vector<KitchenTool> kitchenToolsInUse;
	private CountDownLatch NumberOfDishesLeftToCock;
	
	public RunnableCookOneDish(OrderOfDish dishName,Warehouse warehouseName,RunnableChef Chef,CountDownLatch NumberOfDishesLeftToCock){
		this.dishName=dishName;
		this.warehouseName=warehouseName;
		this.Chef=Chef;
		this.kitchenToolsInUse=new Vector<KitchenTool>();
		this.NumberOfDishesLeftToCock=NumberOfDishesLeftToCock;
		
	}

	/** (non-Javadoc)
	 * @ acquire all the kitchen tools
	 */
	public Boolean acquireAllKitchenTools(){
		Boolean res=true;
		Vector <KitchenTool> kitchenToolsForThisDish=dishName.gestDish().getDishKitchenTolls();
		while(!this.warehouseName.getKitchenTolls(dishName.gestDish().getDishKitchenTolls())){
			
		}
		for (int i=0; i<kitchenToolsForThisDish.size();i++){
			kitchenToolsInUse.add(kitchenToolsForThisDish.get(i));
		}
		return res;
	}
	
	
	
	/** (non-Javadoc)
	 * @ acquire one kitchen tool
	 * @param KitchenTool to acquire  
	 */
	public  Boolean acquireKitchenTool(KitchenTool kitchenToolToacquire){
		Boolean res=false;
			if (this.warehouseName.getKitchenTolls(kitchenToolToacquire)){
				return true;
				}
		return res;
	}
		
	/** (non-Javadoc)
	 * @ acquire all the ingredients
	 */
	public Boolean acquireAllIngredients(){
		Boolean res=true;
		Vector <Ingredient> ingredientsForThisDish=dishName.gestDish().getDishIngredients();
		for (int i=0; i<ingredientsForThisDish.size();i++){
			if (!acquireIngredients(ingredientsForThisDish.get(i))){
				System.out.println("EROR!!! ingredient " +ingredientsForThisDish.get(i).getIngredientName()+ " missing");
			}
		}
		return res;
	}

	/** (non-Javadoc)
	 * @acquire one ingredients
	 */
	public Boolean acquireIngredients(Ingredient ingredientToacquire){
		Boolean res=true;
		if (!this.warehouseName.getIngredient(ingredientToacquire)){
			System.out.println("EROR!!! ingredient " +ingredientToacquire.getIngredientName()+ " is missing");
			res=false;
		}
		
		return res;
	}

	public void cookDish(){
		Long cookingTime=Math.round(this.Chef.getChefEfficiencyRating())*this.dishName.gestDish().getdishExpectedCookTime(); 
		try{
			Thread.sleep(cookingTime);
		}
		catch (InterruptedException e){
			
		}
	}
	public void returnAllKitchenTools(){
		for(int i=0; i<this.kitchenToolsInUse.size();i++){
			this.warehouseName.returnKitchenTolls(this.kitchenToolsInUse.get(i));
		}
	}
	
	/** (non-Javadoc)
	 * @ the start of a thread to cook the dish
	 */
	public void run(){
			this.dishName.setOrderStatus(2);
			acquireAllIngredients();
			acquireAllKitchenTools();
			this.cookDish();
			returnAllKitchenTools();
			this.dishName.setOneDishIsDone();
			this.NumberOfDishesLeftToCock.countDown();
			if (this.dishName.getQuantityLeft()==0){
				this.dishName.setOrderStatus(3);
			}
		
	}
	public Dish getDish(){
		return this.dishName.gestDish();
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RunnableCookOneDish [dishName=");
		builder.append(dishName);
		builder.append(", warehouseName=");
		builder.append(warehouseName);
		builder.append(", kitchenToolsInUse=");
		builder.append(kitchenToolsInUse);
		builder.append(", NumberOfDishesLeftToCock=");
		builder.append(NumberOfDishesLeftToCock);
		builder.append("]");
		return builder.toString();
	}

}
