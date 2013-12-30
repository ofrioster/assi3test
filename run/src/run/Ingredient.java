package run;

import java.util.concurrent.Semaphore;

public class Ingredient implements Ingredient_Interface{
	
	private String ingredientName;
	private Semaphore ingredientAmount;
	private int ingredientInitialAmount;
	
	public Ingredient(String name,int amount) {
		this.ingredientAmount=new Semaphore(amount);
		this.ingredientName=name;
		this.ingredientInitialAmount=amount;
//		System.out.println(name+": "+amount);
	}
	/**
	 * @param ingredientToCopy
	 * @ copy constructor
	 */
	public Ingredient(Ingredient ingredientToCopy) {
		this.ingredientAmount=new Semaphore(ingredientToCopy.getNumberOfIngredient());
		this.ingredientName=ingredientToCopy.getIngredientName();
		this.ingredientInitialAmount=ingredientToCopy.getNumberOfIngredient();
	}


	
	public Ingredient(){
	}
	
	
	public String getIngredientName(){
		return this.ingredientName; 
	}
	
	public synchronized  boolean getIngredient(int amount){
		Boolean res=true;
//		System.out.println("amount: "+amount);
		for (int i=0;i<amount;i++){
//			System.out.println("ingredientName: "+this.ingredientName);
			if(this.ingredientAmount.tryAcquire()){
				//		System.out.println(this.ingredientName+" - "+this.ingredientAmount.availablePermits());
				}
			else {
	//				System.out.println("this.ingredientAmount.availablePermits() "+this.ingredientAmount.availablePermits());
					res= false;
				}
		}
		return res;

	}
	
	public void returnIngredient(){
		this.ingredientAmount.release();
	}
	public void returnIngredient(int amount){
		this.ingredientAmount.release(amount);
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
		builder.append(this.ingredientInitialAmount);
		builder.append("][consumed=");
		builder.append(this.ingredientInitialAmount-ingredientAmount.availablePermits());
		builder.append("][left=");
		builder.append(ingredientAmount.availablePermits());
		builder.append("]");
		return builder.toString();
	}


	
}

