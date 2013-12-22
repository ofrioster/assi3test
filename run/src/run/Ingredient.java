package run;

import java.util.concurrent.Semaphore;

public class Ingredient implements Ingredient_Interface{
	
	private String ingredientName;
	private Semaphore ingredientAmount;
	
	public Ingredient(String name,int amount) {
		this.ingredientAmount=new Semaphore(amount);
		this.ingredientName=name;
	}
	/**
	 * @param ingredientToCopy
	 * @ copy constructor
	 */
	public Ingredient(Ingredient ingredientToCopy) {
		this.ingredientAmount=new Semaphore(ingredientToCopy.getNumberOfIngredient());
		this.ingredientName=ingredientToCopy.getIngredientName();
	}


	
	public Ingredient(){
	}
	
	
	public String getIngredientName(){
		return this.ingredientName; 
	}
	
	public synchronized  boolean getIngredient(){
		if(this.ingredientAmount.tryAcquire()){
	//		System.out.println(this.ingredientName+" - "+this.ingredientAmount.availablePermits());
			return true;
		}
		else {
			System.out.println("this.ingredientAmount.availablePermits() "+this.ingredientAmount.availablePermits());
			return false;
		}

	}
	
	public void returnIngredient(){
		this.ingredientAmount.release();
	}
	
	public int getNumberOfIngredient(){
		return this.ingredientAmount.availablePermits();
	}
}

