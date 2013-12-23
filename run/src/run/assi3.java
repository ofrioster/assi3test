package run;
import java.util.concurrent.*;
import java.util.Vector;

public class assi3 {
	
	public static void main(String args[]){
	//	System.out.println("good");
	//	System.out.println("now?");
		
		Statistics statistics=new Statistics();
		
		//**warehouse
		
		Ingredient ingredient1=new Ingredient("ingredient1",99);
		KitchenTool KitchenTool1=new KitchenTool("KitchenTol",66);
		Ingredient ingredient2=new Ingredient("ingredient2",99);
		KitchenTool KitchenTool2=new KitchenTool("KitchenTo2",66);
		Ingredient ingredient3=new Ingredient("ingredient3",99);
		KitchenTool KitchenTool3=new KitchenTool("KitchenTo3",66);
		Ingredient ingredient4=new Ingredient("ingredient4",99);
		KitchenTool KitchenTool4=new KitchenTool("KitchenTo4",66);
		Ingredient ingredient5=new Ingredient("ingredient5",66);
		KitchenTool KitchenTool5=new KitchenTool("KitchenTo5",666);
		Ingredient ingredient6=new Ingredient("ingredient6",66);
		KitchenTool KitchenTool6=new KitchenTool("KitchenTo6",33);
		
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
		
		CountDownLatch latchObject = new CountDownLatch (2);
		Address restaurantAddress=new Address(3, 7);
		//***chef***//
		RunnableChef chef1=new RunnableChef("chef1", 5.0, 7.0);
		RunnableChef chef2=new RunnableChef("chef2", 4.0, 30.0);
		Vector<RunnableChef> chefVector=new Vector<RunnableChef>();
		chefVector.add(chef2);
		chefVector.add(chef1);
		///***  DeliveryPerson **// 
		RunnableDeliveryPerson deliveryP1=new RunnableDeliveryPerson("deliveryP1", restaurantAddress, 3.2, latchObject,statistics);
		RunnableDeliveryPerson deliveryP2=new RunnableDeliveryPerson("deliveryP2", restaurantAddress, 5.2, latchObject,statistics);
		Vector<RunnableDeliveryPerson> deliveryPerson=new Vector<RunnableDeliveryPerson>();
		deliveryPerson.add(deliveryP2);
		deliveryPerson.add(deliveryP1);
/*		System.out.println("chef1.getEnduranceRating() "+chef1.getEnduranceRating());
		System.out.println("chef1.getChefEfficiencyRating() " +chef1.getChefEfficiencyRating());
		System.out.println("chef1.gerChefName() "+chef1.getChefName());
		System.out.println("chef1.gerChefName() "+chef1.gerChefName());
	*/	

		////****Dish***//
		Vector<KitchenTool> dish1KitchenTools=new Vector<KitchenTool>();
//		System.out.println(" size of dish kitchen vector "+dish1KitchenTools.size());
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
		Vector<KitchenTool> dish1KitchenTools2=new Vector<KitchenTool>();
//		System.out.println(" size of dish kitchen vector "+dish1KitchenTools.size());
		Ingredient ingredient9=new Ingredient("ingredient3",3);
		Ingredient ingredient10=new Ingredient("ingredient2",3);
		dish1KitchenTools.add(KitchenTool6);
		dish1KitchenTools.add(KitchenTool5);
		Vector<Ingredient> dish1ingredients2=new Vector<Ingredient>();
		dish1ingredients.add(ingredient9);
		dish1ingredients.add(ingredient10);
		Dish dish2=new Dish("dish2", 5122, dish1ingredients2, dish1KitchenTools2, 1, 3.2);
		
		//***order***//
		Vector<OrderOfDish> orderOneDishVector=new Vector<OrderOfDish>();
		OrderOfDish orderOneDish=new OrderOfDish(dish1, 3);
		orderOneDishVector.add(orderOneDish);
		Address customerAddress=new Address(7, 16);
		Order order1=new Order("order1",45,orderOneDishVector,customerAddress);
		OrderOfDish orderOneDish2=new OrderOfDish(dish2, 2);
		orderOneDishVector.add(orderOneDish);
		Address customerAddress2=new Address(2, 6);
		Order order2=new Order("order2",15,orderOneDishVector,customerAddress);
/*		System.out.println("order1.getActualCookTime() "+order1.getActualCookTime());
		System.out.println("order1.getExpectedCookTime() "+order1.getExpectedCookTime());
		System.out.println("order1.getDifficultyRating() "+order1.getDifficultyRating());
		System.out.println("order1.getOrderID() "+order1.getOrderID());
*/	//	order2.setOrderStatus(4);
//		System.out.println("money gain stas "+managementTest.getMoneyGain());
	//	order2.setActualCookTime(5555);
	//	order2.setActualDeliveryTime(33222);
	//	order2.setExpectedDeliveryTime(1111);
	//	order2.setTotalReward();
		Vector<Order> orderVector=new Vector<Order>();
		orderVector.add(order1);
		orderVector.add(order2);
		
		
		
		
		///***management **//
		Management managementTest=new Management(orderVector, chefVector, deliveryPerson, warehouseTest, latchObject,statistics);
//		System.out.println("managementTest.getMoneyGain() "+managementTest.getMoneyGain());
		managementTest.setReceiveAllOrders(true);
		Thread t=new Thread(managementTest);
		t.start();
	//	managementTest.run();
		System.out.println("order1.getOrderStatus() "+order1.getOrderStatus());
		try {
	//		System.out.println("await");
			latchObject.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		managementTest.ShutDown();
		
		System.out.println("money gain "+managementTest.getMoneyGain());
		
		
	//	Statistics statisticsTest=new Statistics(order1);
	//	Statistics statisticsTest=new Statistics();
		System.out.println("rawerd "+order1.getTotalReward());

	//	statisticsTest.addDeliveredOrder(order2);
		System.out.println("rawerd "+order2.getTotalReward());
		System.out.println("money gain "+managementTest.getMoneyGain());
		System.out.println("end");
		
	}


}
