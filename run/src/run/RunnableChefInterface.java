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
	public Boolean addOrder(Order newOrder, Warehouse warehouse);
	public Boolean canTheChefTakeOrder(Order newOrder);
	public void shutDown();
	public void addManagement(Management management);
	public void addCollectionOfOrdersToDeliver(Vector<Order> collectionOfOrdersToDeliver);
	public String toString();

}
