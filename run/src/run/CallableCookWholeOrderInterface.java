package run;

import java.util.Observable;
import java.util.Observer;

public interface CallableCookWholeOrderInterface {
	
	public void addDish(OrderOfDish newDish);
	// start threads to cook the orders
	public Boolean IsOrderIsDone();
	public void run();
	public Long getTotalCookingTime();
	public void update1();
	public void update(Observable obj, Boolean finish);
	public void updateTotalNumberOfDishs();
	public String toString();
}
