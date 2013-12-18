package run;
import java.util.Vector;
public interface RestaurantInterface{
	public Address getAddress();
	
	public Vector<Ingredient> getIngredients();
	public Vector<KitchenTool> getKitchenTolls();
	
	
	public Vector<RunnableChef> getChefs();
	public Vector<RunnableDeliveryPerson> getDeliveryPersonals();

	
	public void setAddress(Address addr);

	public void setIngredients(Vector<Ingredient> Ingredients);
	public  void setKitchenTolls(Vector<KitchenTool> KitchenTools);
	
	
	public  void setChefs(Vector<RunnableChef> Chefs);
	public  void setRunnableDeliveryPerson(Vector<RunnableDeliveryPerson> DeliveryPersonals);

}
