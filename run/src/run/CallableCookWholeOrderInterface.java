package run;

public interface CallableCookWholeOrderInterface {
	
	public void addDish(RunnableCookOneDish newDish);
	// start threads to cook the orders
	public void startCookTheOrder();
	public Boolean IsOrderIsDone();
	

}
