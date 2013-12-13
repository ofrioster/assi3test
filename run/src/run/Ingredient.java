package run;

public class Ingredient implements Ingredient_Interface{
	
	private String ingredientName;
	private int ingredientAmount;
	
	public Ingredient(String name,int amount) {
		this.ingredientAmount=amount;
		this.ingredientName=name;
	}

}
