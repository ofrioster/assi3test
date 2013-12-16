package run;

public interface RunnableChefInterface {
	
	public String gerChefName();
	public Double getChefEfficiencyRating();
	public Double getEnduranceRating();
	public int getCurrectPressure();
	//accept new order if dish difficulty< EnduranceRating - CurrectPressure
	public Boolean addOrder(Order newOrder, Warehouse warehouse);
	public Boolean canTheChefTakeOrder(Order newOrder);
	//finish all the cooking and do not start new ones
	public void shutDown();
//	public void sendFinishOrderToDelivery(Order finishOrder);  //can be in Management
	
	//*** how the thread tell that he done?

}
