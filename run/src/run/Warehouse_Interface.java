package run;




public interface Warehouse_Interface {
	
	public void addKitchenTolls(KitchenTool newToll);
	public void addIngredient(Ingredient ingredient);
	public int getNumberOfKitchenTolls(String KitchenTolls);
	public int getNumberOfIngredientsAvailable(String ingredients);
	//take one ingredient from the warehouse
	public boolean getIngredient(Ingredient ingredientsName);
	//take one kitchen tool from the warehouse
	public boolean getKitchenTolls(KitchenTool KitchenTollsname);
	public void returnKitchenTolls(KitchenTool KitchenTollsname);

}
