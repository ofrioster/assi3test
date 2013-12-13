package run;

public class OrderOfDish implements OrderOfDishInterface{
	
	private Dish dish;
	private int quantity;
	private int orderStatus;
	// the status are:
	private final int incomplete=1;
	private final int inProgress=2;
	private final int complete=3;
	private final int delivered=4;

}
