package run;



public interface CallableCookWholeOrderInterface {
	
	public void addDish(OrderOfDish newDish);
	public Boolean IsOrderIsDone();
	public void run();
	public Long getTotalCookingTime();
	public void update1();
	public void updateTotalNumberOfDishs();
	public String toString();
}
