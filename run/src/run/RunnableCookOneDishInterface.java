package run;

public interface RunnableCookOneDishInterface {
	
	public void run();
	//acquire all the kitchen tools
	public Boolean acquireAllKitchenTools();
	//acquire one kitchen tool
	public Boolean acquireKitchenTool(KitchenTool kitchenToolToacquire);
	//acquire all the ingredients
	public Boolean acquireAllIngredients();
	//acquire one ingredients
	public Boolean acquireIngredients(Ingredient ingredientToacquire);
	//put to sleep while cooking, multiplay Chef Efficiency Factor by  dish cooking time
	public void cookDish();
	public void returnAllKitchenTools();
	public Dish getDish();
	

}
