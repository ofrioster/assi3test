package run;
import java.util.Vector;

import javax.swing.plaf.SliderUI;

public class RunnableCookOneDish implements RunnableCookOneDishInterface,Runnable{
	
	private Dish dishName;
	private Warehouse warehouseName;
	private RunnableChef Chef;
	private Boolean allKitchenToolsAcquire;
	private Boolean allIngredientsAcquire;
	private Vector<KitchenTool> kitchenToolsInUse;
	
	public RunnableCookOneDish(Dish runnableCookOneDish,Warehouse warehouseName,RunnableChef Chef){
		this.dishName=runnableCookOneDish;
		this.warehouseName=warehouseName;
		this.Chef=Chef;
		this.allKitchenToolsAcquire=false;
		this.allIngredientsAcquire=false;
		
	}
	
//	public void startCookOneDish();
	
	
	//acquire all the kitchen tools
	public Boolean acquireAllKitchenTools(){
		Boolean res=true;
		Vector <KitchenTool> kitchenToolsForThisDish=dishName.getDishKitchenTolls();
		for (int i=0; i<kitchenToolsForThisDish.size();i++){
			while (!acquireKitchenTool(kitchenToolsForThisDish.get(i))){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			kitchenToolsInUse.add(kitchenToolsForThisDish.get(i));
		}
		allKitchenToolsAcquire=true;
		return res;
	}
	
	
	//acquire one kitchen tool
	public synchronized Boolean acquireKitchenTool(KitchenTool kitchenToolToacquire){
		Boolean res=false;
		while(!res){
			try{
				if (warehouseName.getKitchenTolls(kitchenToolToacquire)){
					return true;
				}
				else{
					this.wait();
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		return res;
	}
		
	//acquire all the ingredients
	public Boolean acquireAllIngredients(){
		Boolean res=true;
		Vector <Ingredient> ingredientsForThisDish=dishName.getDishIngredients();
		for (int i=0; i<ingredientsForThisDish.size();i++){
			if (!acquireIngredients(ingredientsForThisDish.get(i))){
				System.out.println("EROR!!! ingredient " +ingredientsForThisDish.get(i).getIngredientName()+ "missing");
			}
		}
		allIngredientsAcquire=true;
		return res;
	}
	//acquire one ingredients
	public synchronized Boolean acquireIngredients(Ingredient ingredientToacquire){
		Boolean res=false;
		if (this.warehouseName.getIngredient(ingredientToacquire)){
			System.out.println("EROR!!! ingredient " +ingredientToacquire.getIngredientName()+ "missing");
		}
		
		return res;
	}
	
	//put to sleep while cooking, multiplay Chef Efficiency Factor by  dish cooking time
	public void cookDish(){
		Long cookingTime=Math.round(this.Chef.getChefEfficiencyRating()); 
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
	
	public void run(){
		try{
			acquireAllIngredients();
			acquireAllKitchenTools();
			cookDish();
			returnAllKitchenTools();
		}
		catch (Exception e){
			System.out.println("EROR IN COOK ONE DISH");
		}
		
		}
	
	public Dish getDish(){
		return this.dishName;
	}

}
