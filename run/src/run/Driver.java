package run;

import java.util.Vector;
import java.util.concurrent.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

public class Driver {
	  private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) {
		


	    
	    try {
	      MyLogger.setup();
	    } catch (IOException e) {
	      e.printStackTrace();
	      throw new RuntimeException("Problems with creating the log files");
	    }

	    logger.log(Level.INFO, "message 1");
	    logger.log(Level.SEVERE, "message 2");
	    logger.log(Level.FINE, "message 3");
		
		
		
		Vector<Dish> Dishes = ReadXMLFile.ParseMenuFile("Menu.xml");
		Vector<Order> Orders =  ReadXMLFile.ParseOrderListFile("OrdersList.xml",Dishes);
		Restaurant Restaurant = ReadXMLFile.ParseRestauranFilet("InitialData.xml") ;
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
	    logger.log(Level.INFO, statistics.toString());
		System.out.println(" END!!!!");
	
	}

}
