package run;

import java.util.Vector;

public class Restaurant implements RestaurantInterface {

	private Address restaurantAddres;
	private Vector<Ingredient> Ingredients;
	private Vector<KitchenTool> KitchenTools;
	private Vector<RunnableChef> Chefs;
	private Vector<RunnableDeliveryPerson> DeliveryPersonals; 
	
	public Restaurant(){
	}
	
	public Address getAddress() {
		return this.restaurantAddres;
	}

	public Vector<Ingredient> getIngredients() {
		return this.Ingredients;
	}

	public Vector<KitchenTool> getKitchenTolls() {
		return this.KitchenTools;
	}


	public Vector<RunnableChef> getChefs() {
		return this.Chefs;
	}
	
	public Vector<RunnableDeliveryPerson> getDeliveryPersonals() {
		return this.DeliveryPersonals;
	}
	
	public void setRunnableDeliveryPerson(Vector<RunnableDeliveryPerson> RunnableDeliveryPersons) {
		this.DeliveryPersonals = RunnableDeliveryPersons;
	}

	public void setAddress(Address addr) {
		this.restaurantAddres = addr;
	}

	public void setIngredients(Vector<Ingredient> Ingredients) {
		this.Ingredients = Ingredients;

	}

	public void setKitchenTolls(Vector<KitchenTool> KitchenTools) {
		this.KitchenTools = KitchenTools;
		
	}

	public void setChefs(Vector<RunnableChef> Chefs) {
		this.Chefs = Chefs;
		
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Restaurant [restaurantAddres=");
		builder.append(restaurantAddres);
		builder.append(", Ingredients=");
		builder.append(Ingredients);
		builder.append(", KitchenTools=");
		builder.append(KitchenTools);
		builder.append(", Chefs=");
		builder.append(Chefs);
		builder.append(", DeliveryPersonals=");
		builder.append(DeliveryPersonals);
		builder.append("]");
		return builder.toString();
	}

}
