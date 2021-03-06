package run;


import java.util.Vector;

public class Warehouse implements Warehouse_Interface{
	
	private Vector<KitchenTool> kitchenCollectionTolls;
	private Vector<Ingredient> ingredientsAvailable;
	
	public Warehouse(){
		Vector<KitchenTool> kitchenCollectionTolls=new Vector<KitchenTool>();
		Vector<Ingredient> ingredientsAvailable=new Vector<Ingredient>();
	}
	
	public Warehouse(Vector<KitchenTool> kitchenCollectionTolls, Vector<Ingredient> ingredientsAvailable){
		this.kitchenCollectionTolls=kitchenCollectionTolls;
		this.ingredientsAvailable=ingredientsAvailable;
	}


	public synchronized void addKitchenTolls(KitchenTool newToll) {
		this.kitchenCollectionTolls.add(newToll);
	}
	
	public synchronized void addIngredient(Ingredient ingredient){
		this.ingredientsAvailable.add(ingredient);
		
	}
	
	public synchronized int getNumberOfKitchenTolls(KitchenTool KitchenTools){
		int res=-1;
		for (int i=0; i<this.kitchenCollectionTolls.size();i++){
			//if (this.kitchenCollectionTolls.get(i).getKitchenToolName()==KitchenTools.getKitchenToolName()){
			if (this.kitchenCollectionTolls.get(i).getKitchenToolName().equals(KitchenTools.getKitchenToolName())){	
				return this.kitchenCollectionTolls.get(i).getNumberOfKitchenTool();
			}
		}
		
		return res;
	}
	
	/** (non-Javadoc)
	 * @ Assume that we have enough ingredients
	 */
	public int getNumberOfIngredientsAvailable(Ingredient ingredients){
		int res=-1;
		for (int i=0;i<this.ingredientsAvailable.size();i++){
			if (this.ingredientsAvailable.get(i).getIngredientName().equals(ingredients.getIngredientName())){
				return this.ingredientsAvailable.get(i).getNumberOfIngredient();
			}
		}
		return res;
	}
	
	/** (non-Javadoc)
	 * @return true if you can have the ingredient
	 */
	public boolean  getIngredient(Ingredient ingredientsName){
		boolean res=false;
		for (int i=0; i<this.ingredientsAvailable.size();i++){
			if(this.ingredientsAvailable.get(i).getIngredientName().equals(ingredientsName.getIngredientName())){
				if(this.ingredientsAvailable.get(i).getIngredient(ingredientsName.getNumberOfIngredient())){
					return true;
				}
		}
		}
		return res;
		
	}
	public Ingredient  getTheIngredient(Ingredient ingredientsName){
		Ingredient res=new Ingredient();
		for (int i=0; i<this.ingredientsAvailable.size();i++){
			if(this.ingredientsAvailable.get(i).getIngredientName().equals(ingredientsName.getIngredientName())){
				return this.ingredientsAvailable.get(i);
		}
		}
		return res;
		
	}
	
	/** (non-Javadoc)
	 * @return true/false if you can have the kitchen tool
	 */
	public boolean  getKitchenTolls(KitchenTool KitchenToolsname){
		boolean res=true;
		for (int i=0;i<this.kitchenCollectionTolls.size();i++){
			if (this.kitchenCollectionTolls.get(i).getKitchenToolName().equals(KitchenToolsname.getKitchenToolName())){	
				return this.kitchenCollectionTolls.get(i).getKitchenTool();
			}
		}
		
		return res;
	}
	public boolean  getKitchenTollsUnsynchronized(KitchenTool KitchenToolsname){
		boolean res=true;
		for (int i=0;i<this.kitchenCollectionTolls.size();i++){
			if (this.kitchenCollectionTolls.get(i).getKitchenToolName().equals(KitchenToolsname.getKitchenToolName())){	
				return this.kitchenCollectionTolls.get(i).getKitchenToolUnsynchronized();
			}
		}
		
		return res;
	}
	
	public void returnKitchenTolls(KitchenTool KitchenTollsname){
		Boolean found=false;
		for( int i=0;i<this.kitchenCollectionTolls.size() && !found;i++){
			if (this.kitchenCollectionTolls.get(i).getKitchenToolName().equals(KitchenTollsname.getKitchenToolName())){
				this.kitchenCollectionTolls.get(i).returnKitchenTool();
				found=true;
			}
		}
		
	}
	public String toString(){
		String res=" kitchenCollectionTolls- "+this.kitchenCollectionTolls.toString()+" ingredientsAvailable- "+this.ingredientsAvailable.toString();
		return res;		
	}
	public synchronized boolean  getKitchenTolls(Vector <KitchenTool> KitchenTools){
		Boolean res=true;
		for (int i=0; i<KitchenTools.size() &&res;i++){
			if(!this.getKitchenTolls(KitchenTools.get(i))){
				res=false;
			}
		}
		return res;
	}
	public void returnUnuseKitchenTolls(KitchenTool KitchenTollsname){
		Boolean found=false;
		for( int i=0;i<this.kitchenCollectionTolls.size() && !found;i++){
			if (this.kitchenCollectionTolls.get(i).getKitchenToolName().equals(KitchenTollsname.getKitchenToolName())){
				this.kitchenCollectionTolls.get(i).returnUnuseKitchenTool();
				found=true;
			}
		}	
	}
}
