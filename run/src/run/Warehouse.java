package run;


import java.util.Vector;

public class Warehouse implements Warehouse_Interface{
	
	private Vector<KitchenTool> kitchenCollectionTolls;
	private Vector<Ingredient> ingredientsAvailable;
	
	public Warehouse(){
		
	}
	
	public Warehouse(Vector<KitchenTool> kitchenCollectionTolls, Vector<Ingredient> ingredientsAvailable){
		
	}


	public void addKitchenTolls(KitchenTool newToll) {
	}
	
	public void addIngredient(Ingredient ingredient){
		
	}
	
	public int getNumberOfKitchenTolls(KitchenTool KitchenTolls){
		int res=-1;
		
		
		return res;
	}
	
	public int getNumberOfIngredientsAvailable(Ingredient ingredients){
		int res=-1;
		
		
		return res;
	}
	
	public boolean  getIngredient(Ingredient ingredientsName){
		boolean res=true;
		
		return res;
		
	}
	
	public boolean  getKitchenTolls(KitchenTool KitchenTollsname){
		boolean res=true;
		
		return res;
	}
	
	public synchronized void returnKitchenTolls(KitchenTool KitchenTollsname){
		Boolean found=false;
		for( int i=0;i<this.kitchenCollectionTolls.size() && !found;i++){
			if (this.kitchenCollectionTolls.get(i)==KitchenTollsname){
				this.kitchenCollectionTolls.get(i).returnKitchenTool();
				found=true;
				notifyAll();
			}
		}
		
	}

}
