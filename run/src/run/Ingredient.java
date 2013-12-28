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
	
	public synchronized  boolean getIngredient(int amount){
		Boolean res=true;
		for (int i=0;i<amount;i++){
			if(this.ingredientAmount.tryAcquire()){
				//		System.out.println(this.ingredientName+" - "+this.ingredientAmount.availablePermits());
				}
			else {
					System.out.println("this.ingredientAmount.availablePermits() "+this.ingredientAmount.availablePermits());
					res= false;
				}
		}
		return res;

	}
	
	public void returnIngredient(){
		this.ingredientAmount.release();
	}
	
	public int getNumberOfIngredient(){
		return this.ingredientAmount.availablePermits();
	}
//	public String toString(){
//		String res=" ingredientName- "+this.ingredientName+" ingredientAmount- "+this.ingredientAmount.availablePermits();
//		return res;
//	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ingredient [ingredientName=");
		builder.append(ingredientName);
		builder.append("][ingredientAmount=");
		builder.append(ingredientAmount.availablePermits());
		builder.append("]");
		return builder.toString();
	}
	
	
}

