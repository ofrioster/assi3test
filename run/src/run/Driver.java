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
		Vector<Dish> Dishes;
		Vector<Order> Orders;
		Restaurant Restaurant ;
		
        if (args.length > 0) {
    		 Dishes = ReadXMLFile.ParseMenuFile(args[0]);
    		 Orders =  ReadXMLFile.ParseOrderListFile(args[1],Dishes);
    		 Restaurant = ReadXMLFile.ParseRestauranFilet(args[2]) ;
        }else{   
		 Dishes = ReadXMLFile.ParseMenuFile("Menu.xml");
		 Orders =  ReadXMLFile.ParseOrderListFile("OrdersList.xml",Dishes);
		Restaurant = ReadXMLFile.ParseRestauranFilet("InitialData.xml") ;
        }
        
		Warehouse warehouseTest=new Warehouse(Restaurant.getKitchenTolls(),Restaurant.getIngredients());
		CountDownLatch latchObject = new CountDownLatch (Orders.size());
		Statistics statistics=new Statistics(warehouseTest);

		for (RunnableDeliveryPerson deliveryPerson : Restaurant.getDeliveryPersonals()){
			deliveryPerson.setStatistics(statistics);
			deliveryPerson.setordersLatch(latchObject);
			deliveryPerson.setRestaurantAddres(Restaurant.getAddress());
		}
		
		Management managementTest=new Management(Orders,Restaurant.getChefs() , Restaurant.getDeliveryPersonals(), warehouseTest,statistics);
		managementTest.setReceiveAllOrders(true);
		Thread t=new Thread(managementTest);
		t.start();
		try {
			latchObject.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		managementTest.ShutDown();
		for(long i=0;i<9999;i++){
			
		}
	    logger.log(Level.INFO, statistics.toString());
	    System.out.println(" END!!!!");
	
	}

}
