package run;
import java.util.Vector;

public class Order implements OrderInterface {
	
	private int orderID;
	private int difficultyRating;
	private int orderStatus;
	private Vector<OrderOfDish> orderDish;
	private String customerAddress;
	// the status are:
	private final int incomplete=1;
	private final int inProgress=2;
	private final int complete=3;
	private final int delivered=4;
	
	
	public Order(int orderID,int difficultyRating, int orderStatus, Vector<OrderOfDish> orderDish,String customerAddress){
		this.orderID=orderID;
		this.difficultyRating=difficultyRating;
		this.orderStatus=orderStatus;
		this.orderDish=orderDish;
		this.customerAddress=customerAddress;
	}
	
	public Order(int orderID,int difficultyRating, int orderStatus,String customerAddress){
		this.orderID=orderID;
		this.difficultyRating=difficultyRating;
		this.orderStatus=orderStatus;
		this.customerAddress=customerAddress;
	}
	
	public void addDish(OrderOfDish orderDish){
		this.orderDish.add(orderDish);
	}
	public int getOrderStatus(){
		return this.orderStatus;
	}
	public int getOrderID(){
		return this.orderID;
	}
	public int getDifficultyRating(){
		return this.difficultyRating;
	}
	public String getCustomerAddress(){
		return this.customerAddress;
		
	}
	public void changeOrderDifficultyRating(int difficultyRating){
		this.difficultyRating=difficultyRating;
	}

}
