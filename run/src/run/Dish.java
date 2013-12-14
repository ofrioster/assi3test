package run;

import java.awt.geom.Arc2D.Double;
import java.util.Vector;

public class Dish implements DishInterface{
	
	private String dishName;
	private Double dishExpectedCookTime;
	private Vector<String> dishIngredients;
	private Vector<KitchenTool> dishKitchenTolls;
	private int difficultyRating;
	private int reward;
	
	
	public Dish(){
		
	}
	
	public Dish (String dishName,Double dishExpectedCookTime,Vector<String> dishIngredients,Vector<KitchenTool> dishKitchenTolls,int difficultyRating,int reward){
		this.dishName=dishName;
		this.dishExpectedCookTime=dishExpectedCookTime;
		this.dishIngredients=dishIngredients;
		this.dishKitchenTolls=dishKitchenTolls;
		this.difficultyRating=difficultyRating;
		this.reward=reward;
	}
	
	public String getDishName(){
		return this.dishName;
	}
	
	public Double getdishExpectedCookTime(){
		return this.dishExpectedCookTime;
	}
	
	public int getDishDifficultyRating(){
		return this.difficultyRating;
	}

}
