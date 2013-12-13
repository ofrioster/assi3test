package run;

import java.util.Vector;


public interface Warehouse_Interface {
	
	public void addKitchenTolls(KitchenTool newToll);
	public void addIngredient(Ingredient ingredient);
	public int getNumberOfKitchenTolls(String KitchenTolls);
	public int getNumberOfIngredientsAvailable(String ingredients);
	public void getIngredient(String ingredientsName);
	public void getKitchenTolls(String KitchenTollsname);

}
