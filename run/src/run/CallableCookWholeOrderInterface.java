package run;

public interface CallableCookWholeOrderInterface {
	
	public void addDish(Dish newDish);
	// start threads to cook the orders
	public Boolean IsOrderIsDone();
	public void run();
	

}
