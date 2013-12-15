package run;


import java.util.Vector;

public class Dish implements DishInterface{
	
	private String dishName;
	private Double dishExpectedCookTime;
	private Vector<Ingredient> dishIngredients;
	private Vector<KitchenTool> dishKitchenTolls;
	private int difficultyRating;
	private Double reward;
	
	
	public Dish(){
		
	}
	
	public Dish (String dishName,Double dishExpectedCookTime,Vector<Ingredient> dishIngredients,Vector<KitchenTool> dishKitchenTolls,int difficultyRating,Double reward){
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
	public Vector<Ingredient> getDishIngredients(){
		return this.dishIngredients;
	}
	public Vector<KitchenTool> getDishKitchenTolls(){
		return this.dishKitchenTolls;
	}
	public Double getReward(){
		return this.reward;
	}

}
