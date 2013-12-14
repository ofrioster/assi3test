package run;

public interface RunnableCookOneDishInterface {
	
	public void startCookOneDish();
	//acquire all the kitchen tools
	abstract Boolean acquireAllKitchenTools();
	//acquire one kitchen tool
	abstract Boolean acquireKitchenTool(KitchenTool kitchenToolToacquire);
	//acquire all the ingredients
	abstract Boolean acquireAllIngredients();
	//acquire one ingredients
	abstract Boolean acquireIngredients(Ingredient ingredientToacquire);
	//put to sleep while cooking, multiplay Chef Efficiency Factor by  dish cooking time
	public void cookDish(int ChefEfficiencyFactor, Double dishCookingTime);
	public void returnAllKitchenTools();
	

}
