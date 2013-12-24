package run;


import java.util.Vector;

public class Dish implements DishInterface{
	
	private String dishName;
	private long dishExpectedCookTime;
	private Vector<Ingredient> dishIngredients;
	private Vector<KitchenTool> dishKitchenTolls;
	private int difficultyRating;
	private Double reward;
	
	
	public Dish(){
	}
	
	public Dish (String dishName,long dishExpectedCookTime,Vector<Ingredient> dishIngredients,Vector<KitchenTool> dishKitchenTolls,int difficultyRating,Double reward){
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
	
	public void setDishName(String dishName){
		this.dishName=dishName;
	}
	
	public long getdishExpectedCookTime(){
		return this.dishExpectedCookTime;
	}
	
	public int getDishDifficultyRating(){
		return this.difficultyRating;
	}
	
	public void setdishExpectedCookTime(long dishExpectedCookTime){
		this.dishExpectedCookTime=dishExpectedCookTime;
	}
	
	public void setDishDifficultyRating(int difficultyRating){
		this.difficultyRating=difficultyRating;
	}
	
	
	public boolean equals(Object o) {
	    return o.equals(getDishName()) ||
	           super.equals(o);    
	}
	
	
	public Vector<Ingredient> getDishIngredients(){
		return this.dishIngredients;
	}
	public Vector<KitchenTool> getDishKitchenTolls(){
		return this.dishKitchenTolls;
	}
	
	public void setDishIngredients(Vector<Ingredient> dishIngredients){
		this.dishIngredients=dishIngredients;
		}
	
	public void setDishKitchenTolls(Vector<KitchenTool> dishKitchenTolls){
		this.dishKitchenTolls=dishKitchenTolls;
		}
	
	
	public Double getReward(){
		return this.reward;
	}
	public void setReward(Double reward){
		this.reward=reward;
	}
}
