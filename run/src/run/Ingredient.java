package run;

import java.util.concurrent.Semaphore;

public class Ingredient implements Ingredient_Interface{
	
	private String ingredientName;
	private Semaphore ingredientAmount;
	
	public Ingredient(String name,int amount) {
		this.ingredientAmount=new Semaphore(amount);
		this.ingredientName=name;
	}


	
	public Ingredient(){
		
	}
	
	
	public String getIngredientName(){
		return this.ingredientName; 
	}
	
	public synchronized  boolean getIngredient(){
		if(this.ingredientAmount.tryAcquire()){
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void returnIngredient(){
		this.ingredientAmount.release();
	}
	
	public int numberOfIngredient(){
		return this.ingredientAmount.availablePermits();
	}
}

