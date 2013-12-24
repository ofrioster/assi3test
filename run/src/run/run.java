package run;

import java.util.Vector;
import java.util.concurrent.*;

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
		
		Management managementTest=new Management(Orders,Restaurant.getChefs() , Restaurant.getDeliveryPersonals(), warehouseTest, latchObject,statistics);
		managementTest.setReceiveAllOrders(true);
		Thread t=new Thread(managementTest);
		t.start();
		
		//System.out.println("order1.getOrderStatus() "+order1.getOrderStatus());
		try {
	//		System.out.println("await");
			latchObject.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		managementTest.ShutDown();
		
	
	}

}
