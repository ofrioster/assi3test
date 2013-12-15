package run;

import java.util.Vector;

public interface DishInterface {
	
	public String getDishName();
	public Double getdishExpectedCookTime();
	public int getDishDifficultyRating();
	public Vector<Ingredient> getDishIngredients();
	public Vector<KitchenTool> getDishKitchenTolls();
	public Double getReward();

}
