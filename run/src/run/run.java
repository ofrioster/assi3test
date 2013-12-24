package run;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class run {

	public static void main(String[] args) {
		Vector<Dish> Dishes = ReadXMLFile.ParseMenu();
		Vector<Order> Orders =  ReadXMLFile.ParseOrderList(Dishes);
		Restaurant Restaurant = ReadXMLFile.ParseRestaurant() ;
		Warehouse warehouseTest=new Warehouse(Restaurant.getKitchenTolls(),Restaurant.getIngredients());
		CountDownLatch latchObject = new CountDownLatch (Orders.size());
		Statistics statistics=new Statistics();

		for (RunnableDeliveryPerson deliveryPerson : Restaurant.getDeliveryPersonals()){
			deliveryPerson.setStatistics(statistics);
			deliveryPerson.setordersLatch(latchObject);
			deliveryPerson.setRestaurantAddres(Restaurant.getAddress());
		}
		
	
	}

}
