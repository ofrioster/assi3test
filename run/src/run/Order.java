package run;

public class Order implements OrderInterface {
	
	private int orderID;
	private int difficultyRating;
	private int orderStatus;
	private OrderInterface orderDish;
	private String customerAddress;
	// the status are:
	private final int incomplete=1;
	private final int inProgress=2;
	private final int complete=3;
	private final int delivered=4;
	

}
