package run;

public interface RunnableCookOneDishInterface {
	
	public void run();
	public Boolean acquireAllKitchenTools();
	public Boolean acquireKitchenTool(KitchenTool kitchenToolToacquire);
	public Boolean acquireAllIngredients();
	public Boolean acquireIngredients(Ingredient ingredientToacquire);
	public void cookDish();
	public void returnAllKitchenTools();
	public Dish getDish();
	public String toString();
	

}
