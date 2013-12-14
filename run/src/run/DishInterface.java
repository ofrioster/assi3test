package run;

import java.awt.geom.Arc2D.Double;
import java.util.Vector;

public interface DishInterface {
	
	public String getDishName();
	public Double getdishExpectedCookTime();
	public int getDishDifficultyRating();
	public Vector<Ingredient> getDishIngredients();
	public Vector<KitchenTool> getDishKitchenTolls();

}
