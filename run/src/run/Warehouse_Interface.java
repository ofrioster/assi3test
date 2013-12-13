package run;




public interface Warehouse_Interface {
	
	public void addKitchenTolls(KitchenTool newToll);
	public void addIngredient(Ingredient ingredient);
	public int getNumberOfKitchenTolls(String KitchenTolls);
	public int getNumberOfIngredientsAvailable(String ingredients);
	public boolean  getIngredient(String ingredientsName);
	public boolean  getKitchenTolls(String KitchenTollsname);

}
