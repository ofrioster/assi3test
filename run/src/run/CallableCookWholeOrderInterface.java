package run;

public interface CallableCookWholeOrderInterface {
	
	public void addDish(OrderOfDish newDish);
	// start threads to cook the orders
	public Boolean IsOrderIsDone();
	public void run();
	public Long getTotalCookingTime();
	

}
