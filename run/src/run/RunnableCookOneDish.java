package run;
import java.util.Vector;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;


public class RunnableCookOneDish extends Observable implements RunnableCookOneDishInterface,Runnable{
	
	private OrderOfDish dishName;
	private Warehouse warehouseName;
	private RunnableChef Chef;
	private Boolean allKitchenToolsAcquire;
	private Boolean allIngredientsAcquire;
	private Vector<KitchenTool> kitchenToolsInUse;
	private CountDownLatch NumberOfDishesLeftToCock;
	
	public RunnableCookOneDish(OrderOfDish dishName,Warehouse warehouseName,RunnableChef Chef,CountDownLatch NumberOfDishesLeftToCock){
		this.dishName=dishName;
		this.warehouseName=warehouseName;
		this.Chef=Chef;
		this.allKitchenToolsAcquire=false;
		this.allIngredientsAcquire=false;
		this.kitchenToolsInUse=new Vector<KitchenTool>();
		this.NumberOfDishesLeftToCock=NumberOfDishesLeftToCock;
		
	}
	

	
	
	//acquire all the kitchen tools
	public Boolean acquireAllKitchenTools(){
	//	System.out.println("dish name "+this.dishName.gestDish().getDishName()+" acquireAllKitchenTools");
		Boolean res=true;
		Vector <KitchenTool> kitchenToolsForThisDish=dishName.gestDish().getDishKitchenTolls();
	//	System.out.println("dish name "+this.dishName.gestDish().getDishName());
		while(!this.warehouseName.getKitchenTolls(dishName.gestDish().getDishKitchenTolls())){
			
		}
		for (int i=0; i<kitchenToolsForThisDish.size();i++){
		//	acquireKitchenTool(kitchenToolsForThisDish.get(i));
			kitchenToolsInUse.add(kitchenToolsForThisDish.get(i));
		}
		
		allKitchenToolsAcquire=true;
	//	System.out.println("dish name "+this.dishName.gestDish().getDishName()+" acquireAllKitchenTools - done");
		return res;
	}
	
	
	//acquire one kitchen tool
	public  Boolean acquireKitchenTool(KitchenTool kitchenToolToacquire){
		Boolean res=false;
			if (this.warehouseName.getKitchenTolls(kitchenToolToacquire)){
				return true;
				}
		return res;
	}
		
	//acquire all the ingredients
	public Boolean acquireAllIngredients(){
		Boolean res=true;
		Vector <Ingredient> ingredientsForThisDish=dishName.gestDish().getDishIngredients();
		for (int i=0; i<ingredientsForThisDish.size();i++){
	//		System.out.println("ingredientsForThisDish.size() "+ingredientsForThisDish.size());
	//		System.out.println("ingredient to find: "+ingredientsForThisDish.get(i).getIngredientName());
			if (!acquireIngredients(ingredientsForThisDish.get(i))){
				System.out.println("EROR!!! ingredient " +ingredientsForThisDish.get(i).getIngredientName()+ " missing");
			}
	//		System.out.println(" ingredients name: "+ingredientsForThisDish.get(i).getIngredientName());
		}
		allIngredientsAcquire=true;
		return res;
	}
	//acquire one ingredients
	public Boolean acquireIngredients(Ingredient ingredientToacquire){
		Boolean res=true;
	//	System.out.println("warehouseName: "+this.warehouseName);
		if (!this.warehouseName.getIngredient(ingredientToacquire)){
//			System.out.println("number of ingredients " + ingredientToacquire.getIngredientName()+ " " +this.warehouseName.getNumberOfIngredientsAvailable(ingredientToacquire));
			System.out.println("EROR!!! ingredient " +ingredientToacquire.getIngredientName()+ " is missing");
			res=false;
		}
		
		return res;
	}
	
	//put to sleep while cooking, multiplay Chef Efficiency Factor by  dish cooking time
	public void cookDish(){
		Long cookingTime=Math.round(this.Chef.getChefEfficiencyRating())*this.dishName.gestDish().getdishExpectedCookTime(); 
		try{
	//		System.out.println("dish name "+this.dishName.gestDish().getDishName()+" cocking time; "+cookingTime+" sleep");
			Thread.sleep(cookingTime);
		}
		catch (InterruptedException e){
			
		}
	//	System.out.println("dish name "+this.dishName.gestDish().getDishName()+" awak");
	}
	public void returnAllKitchenTools(){
		for(int i=0; i<this.kitchenToolsInUse.size();i++){
			this.warehouseName.returnKitchenTolls(this.kitchenToolsInUse.get(i));
		}
	}
	
	/* (non-Javadoc)
	 * @ the start of a thread to cook the dish
	 */
	public void run(){
//		System.out.println("dish name "+this.dishName.gestDish().getDishName()+" start");
//		System.out.println("run cook one dish has start");
//		int k=this.dishName.getQuantityLeft();		//delete
//		String j=this.dishName.gestDish().getDishName();  //delete
//		int w=this.dishName.getquantity();  //delete
//		if (this.dishName.getQuantityLeft()>0){
			this.dishName.setOrderStatus(2);
			acquireAllIngredients();
			acquireAllKitchenTools();
			this.cookDish();
			returnAllKitchenTools();
		//	System.out.println("dish name "+this.dishName.gestDish().getDishName()+" returnAllKitchenTools");
			this.dishName.setOneDishIsDone();
		//	System.out.println(" cock one dish befor-this.NumberOfDishesLeftToCock.getCount() "+this.NumberOfDishesLeftToCock.getCount());
			this.NumberOfDishesLeftToCock.countDown();
		//	System.out.println(" cock one dish after-this.NumberOfDishesLeftToCock.getCount() "+this.NumberOfDishesLeftToCock.getCount());
			if (this.dishName.getQuantityLeft()==0){
				this.dishName.setOrderStatus(3);
			}
//			System.out.println("1this.dishName.getOrderStatus() "+this.dishName.getOrderStatus());
		
			try{
		/*		this.dishName.setOrderStatus(2);
				acquireAllIngredients();
				acquireAllKitchenTools();
				this.cookDish();
				returnAllKitchenTools();
				this.dishName.setOrderStatus(3);
				System.out.println("1this.dishName.getOrderStatus() "+this.dishName.getOrderStatus());
			*///	notifyObservers(true);
				
			}
			catch (Exception e){
				System.out.println("EROR IN COOK ONE DISH");
			}
	//		System.out.println("dish name "+this.dishName.gestDish().getDishName()+" done");
	//	}
//		System.out.println("run cook one dish has finish");
	//	System.out.println("this.dishName.getOrderStatus() "+this.dishName.getOrderStatus());
	//	this.notifyAll();
	}
	public Dish getDish(){
		return this.dishName.gestDish();
	}
	public String toString(){
		String res=" dishName- "+this.dishName.toString()+" warehouseName- "+this.warehouseName.toString()+" kitchenToolsInUse- "+this.kitchenToolsInUse.toString()+" NumberOfDishesLeftToCock- "+this.NumberOfDishesLeftToCock.getCount();
		return res;
	}

}
