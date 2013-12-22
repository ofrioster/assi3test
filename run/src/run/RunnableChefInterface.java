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
	//accept new order if dish difficulty< EnduranceRating - CurrectPressure
	public Boolean addOrder(Order newOrder, Warehouse warehouse);
	public Boolean canTheChefTakeOrder(Order newOrder);
	//finish all the cooking and do not start new ones
	public void shutDown();
	public void addManagement(Management management);
	public void addCollectionOfOrdersToDeliver(Vector<Order> collectionOfOrdersToDeliver);
//	public void sendFinishOrderToDelivery(Order finishOrder);  //can be in Management
	
	//*** how the thread tell that he done?

}
