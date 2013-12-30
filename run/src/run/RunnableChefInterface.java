package run;

import java.util.Vector;

public interface RunnableChefInterface {
	
	public String getChefName();
	public Double getChefEfficiencyRating();
	public Double getEnduranceRating();
	public void setChefName(String chefName);
	public void setChefEfficiencyRating(Double chefEfficiencyRating);
	public void setEnduranceRating(Double enduranceRating);
	public int getCurrectPressure();
	public void addOrder(Order newOrder);
	public Boolean canTheChefTakeOrder(Order newOrder);
	public void shutDown();
	public void addManagement(Management management);
	public void addCollectionOfOrdersToDeliver(Vector<Order> collectionOfOrdersToDeliver);
	public String toString();
	public void run();
	public void setWarehouse(Warehouse warehouse);
	public  void cookOrder(Order newOrder);
	public int updateCurrectPressure();
	public Boolean isAlive();

}
