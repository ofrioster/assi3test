package run;

import java.util.Vector;

public class Restaurant implements RestaurantInterface {

	private Address restaurantAddres; //the type might change we get (x,y)
	private Vector<Ingredient> Ingredients;
	private Vector<KitchenTool> KitchenTools;
	private Vector<RunnableChef> Chefs;
	private Vector<RunnableDeliveryPerson> DeliveryPersonals; 
	
	public Restaurant(){
	}
	
	public Address getAddress() {
		// TODO Auto-generated method stub
		return this.restaurantAddres;
	}

	public Vector<Ingredient> getIngredients() {
		// TODO Auto-generated method stub
		return this.Ingredients;
	}

	@Override
	public Vector<KitchenTool> getKitchenTolls() {
		// TODO Auto-generated method stub
		return this.KitchenTools;
	}

	@Override
	public Vector<RunnableChef> getChefs() {
		// TODO Auto-generated method stub
		return this.Chefs;
	}

	@Override
	public Vector<RunnableDeliveryPerson> setDeliveryPersonals() {
		// TODO Auto-generated method stub
		return this.DeliveryPersonals;
	}

	@Override
	public void setAddress(Address addr) {
		// TODO Auto-generated method stub
		this.restaurantAddres = addr;
	}

	@Override
	public void setIngredients(Vector<Ingredient> Ingredients) {
		// TODO Auto-generated method stub
		this.Ingredients = Ingredients;

	}

	@Override
	public void setKitchenTolls(Vector<KitchenTool> KitchenTools) {
		// TODO Auto-generated method stub
		this.KitchenTools = KitchenTools;
		
	}

	@Override
	public void setChefs(Vector<RunnableChef> Chefs) {
		// TODO Auto-generated method stub
		this.Chefs = Chefs;
		
	}

	@Override
	public void RunnableDeliveryPerson(
			Vector<RunnableDeliveryPerson> DeliveryPersonals) {
		// TODO Auto-generated method stub
		this.DeliveryPersonals = DeliveryPersonals;
	}



}
