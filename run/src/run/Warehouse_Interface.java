package run;

import java.util.Vector;




public interface Warehouse_Interface {
	
	public void addKitchenTolls(KitchenTool newToll);
	public void addIngredient(Ingredient ingredient);
	public int getNumberOfKitchenTolls(KitchenTool KitchenTolls);
	public int getNumberOfIngredientsAvailable(Ingredient ingredients);
	public boolean getIngredient(Ingredient ingredientsName);
	public boolean getKitchenTolls(KitchenTool KitchenTollsname);
	public void returnKitchenTolls(KitchenTool KitchenTollsname);
	public String toString();
	public boolean  getKitchenTolls(Vector <KitchenTool> KitchenTools);
	public void returnUnuseKitchenTolls(KitchenTool KitchenTollsname);
	public boolean  getKitchenTollsUnsynchronized(KitchenTool KitchenToolsname);
	public Ingredient  getTheIngredient(Ingredient ingredientsName);

}
