package run;
import java.util.concurrent.*;
import java.util.Vector;

public class assi3 {
	
	public static void main(String args[]){
	//	System.out.println("good");
	//	System.out.println("now?");
		//**warehouse
		
		Ingredient ingredient1=new Ingredient("ingredient1",3);
		KitchenTool KitchenTool1=new KitchenTool("KitchenTol",3);
		Ingredient ingredient2=new Ingredient("ingredient2",3);
		KitchenTool KitchenTool2=new KitchenTool("KitchenTo2",3);
		Ingredient ingredient3=new Ingredient("ingredient3",9);
		KitchenTool KitchenTool3=new KitchenTool("KitchenTo3",3);
		Ingredient ingredient4=new Ingredient("ingredient4",6);
		KitchenTool KitchenTool4=new KitchenTool("KitchenTo4",3);
		Ingredient ingredient5=new Ingredient("ingredient5",66);
		KitchenTool KitchenTool5=new KitchenTool("KitchenTo5",6);
		Ingredient ingredient6=new Ingredient("ingredient6",66);
		KitchenTool KitchenTool6=new KitchenTool("KitchenTo6",3);
		
		Vector<KitchenTool> kitchenToolsVector=new Vector<KitchenTool>();
		kitchenToolsVector.add(KitchenTool6);
		kitchenToolsVector.add(KitchenTool5);
		kitchenToolsVector.add(KitchenTool4);
		kitchenToolsVector.add(KitchenTool3);
		kitchenToolsVector.add(KitchenTool2);
		kitchenToolsVector.add(KitchenTool1);
		Vector<Ingredient> ingredientsVector=new Vector<Ingredient>();
		ingredientsVector.add(ingredient6);
		ingredientsVector.add(ingredient5);
		ingredientsVector.add(ingredient4);
		ingredientsVector.add(ingredient3);
		ingredientsVector.add(ingredient2);
		ingredientsVector.add(ingredient1);
	//	System.out.println("f");
		Warehouse warehouseTest=new Warehouse(kitchenToolsVector,ingredientsVector);
//		System.out.println("23");

	//	System.out.println("number of ingrediens - "+warehouseTest.getNumberOfIngredientsAvailable(ingredient6));
	//	System.out.println("number of kitchen tools "+ warehouseTest.getNumberOfKitchenTolls(KitchenTool6));
		
		CountDownLatch latchObject = new CountDownLatch (3);
		Address restaurantAddress=new Address(3, 7);
		//***chef***//
		RunnableChef chef1=new RunnableChef("chef1", 5.0, 2.0);
		RunnableChef chef2=new RunnableChef("chef2", 4.0, 3.0);
		Vector<RunnableChef> chefVector=new Vector<RunnableChef>();
		chefVector.add(chef2);
		chefVector.add(chef1);
		///***  DeliveryPerson **// 
		RunnableDeliveryPerson deliveryP1=new RunnableDeliveryPerson("deliveryP1", restaurantAddress, 3.2, latchObject);
		RunnableDeliveryPerson deliveryP2=new RunnableDeliveryPerson("deliveryP2", restaurantAddress, 5.2, latchObject);
		Vector<RunnableDeliveryPerson> deliveryPerson=new Vector<RunnableDeliveryPerson>();
		deliveryPerson.add(deliveryP2);
		deliveryPerson.add(deliveryP1);
/*		System.out.println("chef1.getEnduranceRating() "+chef1.getEnduranceRating());
		System.out.println("chef1.getChefEfficiencyRating() " +chef1.getChefEfficiencyRating());
<<<<<<< HEAD
		System.out.println("chef1.gerChefName() "+chef1.getChefName());
		
=======
		System.out.println("chef1.gerChefName() "+chef1.gerChefName());
	*/	
>>>>>>> 605402023374a3120885181cbe4e4cc7d5fb168d
		////****Dish***//
		Vector<KitchenTool> dish1KitchenTools=new Vector<KitchenTool>();
		System.out.println(" size of dish kitchen vector "+dish1KitchenTools.size());
		Ingredient ingredient7=new Ingredient("ingredient6",3);
		Ingredient ingredient8=new Ingredient("ingredient5",3);
		dish1KitchenTools.add(KitchenTool6);
		dish1KitchenTools.add(KitchenTool5);
		Vector<Ingredient> dish1ingredients=new Vector<Ingredient>();
		dish1ingredients.add(ingredient7);
		dish1ingredients.add(ingredient8);
		Dish dish1=new Dish("dish1", 5222, dish1ingredients, kitchenToolsVector, 1, 6.7);
//		System.out.println("dish1.getDishDifficultyRating() "+dish1.getDishDifficultyRating());
//		System.out.println("dish1.getdishExpectedCookTime()" +dish1.getdishExpectedCookTime());
		
		//***order***//
		Vector<OrderOfDish> orderOneDishVector=new Vector<OrderOfDish>();
		OrderOfDish orderOneDish=new OrderOfDish(dish1, 3);
		orderOneDishVector.add(orderOneDish);
		Address customerAddress=new Address(7, 16);
		Order order1=new Order("order1",45,orderOneDishVector,customerAddress);
/*		System.out.println("order1.getActualCookTime() "+order1.getActualCookTime());
		System.out.println("order1.getExpectedCookTime() "+order1.getExpectedCookTime());
		System.out.println("order1.getDifficultyRating() "+order1.getDifficultyRating());
		System.out.println("order1.getOrderID() "+order1.getOrderID());
*/		Vector<Order> orderVector=new Vector<Order>();
		orderVector.add(order1);
		
		
		
		
		///***management **//
		Management managementTest=new Management(orderVector, chefVector, deliveryPerson, warehouseTest, latchObject);
//		System.out.println("managementTest.getMoneyGain() "+managementTest.getMoneyGain());
		managementTest.setReceiveAllOrders(true);
		Thread t=new Thread(managementTest);
		t.start();
		
		try{
			latchObject.await();
		}
		catch(InterruptedException e){
			System.out.println("thread main problam");
		}
		
		
		System.out.println("end");
		
	}


}
