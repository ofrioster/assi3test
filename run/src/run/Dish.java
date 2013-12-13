package run;

import java.awt.geom.Arc2D.Double;
import java.util.Vector;

public class Dish implements DishInterface{
	
	private String dishName;
	private Double dishCookTime;
	private Vector<String> dishIngredients;
	private Vector<KitchenTool> dishKitchenTolls;
	private int difficultyRating;
	private int reward;

}
