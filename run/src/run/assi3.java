package run;

public class assi3 {
	
	public static void main1(String args[]){
		System.out.print("good");
		System.out.print("now?");
		//**warehouse
		Warehouse test2=new Warehouse();
		for (int i=3; i<5;i++){
			String ingredient= "ingredient"+i;
			Ingredient test2arry=new Ingredient(ingredient,5);
			test2.addIngredient(test2arry);
			String KitchenTolls="KitchenTolls"+i;
			KitchenTool KitchenTollsarry=new KitchenTool(KitchenTolls,5);
			test2.addKitchenTolls(KitchenTollsarry);
		}
		
		Address restaurantAddress=new Address(3, 7);
		RunnableChef =new 
	}


}
