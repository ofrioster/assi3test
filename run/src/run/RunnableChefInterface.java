package run;

public interface RunnableChefInterface {
	
	public String gerChefName();
	public Double getChefEfficiencyRating();
	public Double getEnduranceRating();
	public Double getCurrectPressure();
	//accept new order if dish difficuly< EnduranceRating - CurrectPressure
	public Boolean addOrder(Order newOrder, Warehouse warehouse);
//	public Boolean addCookWholeOrder(CallableCookWholeOrder newCookWholeOrder);
	//finish all the cooking and do not start new ones
	public void shutDown();
//	public void sendFinishOrderToDelivery(Order finishOrder);  //can be in Management

}
