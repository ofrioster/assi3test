package run;

import java.util.Vector;




public interface Warehouse_Interface {
	
	public void addKitchenTolls(KitchenTool newToll);
	public void addIngredient(Ingredient ingredient);
	public int getNumberOfKitchenTolls(KitchenTool KitchenTolls);
	public int getNumberOfIngredientsAvailable(Ingredient ingredients);
	//take one ingredient from the warehouse
	public boolean getIngredient(Ingredient ingredientsName);
	//take one kitchen tool from the warehouse
	public boolean getKitchenTolls(KitchenTool KitchenTollsname);
	public void returnKitchenTolls(KitchenTool KitchenTollsname);
	public String toString();
	public boolean  getKitchenTolls(Vector <KitchenTool> KitchenTools);
	public void returnUnuseKitchenTolls(KitchenTool KitchenTollsname);
	public boolean  getKitchenTollsUnsynchronized(KitchenTool KitchenToolsname);
	

}
