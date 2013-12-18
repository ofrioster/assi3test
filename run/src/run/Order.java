package run;
import java.util.Vector;

public class Order implements OrderInterface {
	
	private String orderID;
	private int difficultyRating;
	private int orderStatus;
	private Vector<OrderOfDish> orderDish;
	private Address customerAddress;
	// the status are:
	private final int incomplete=1;
	private final int inProgress=2;
	private final int complete=3;
	private final int delivered=4;
	private long actualDeliveryTime;
	private long actualCookTime;
	private long expectedDeliveryTime;
	private long expectedcookTime;
	private Double totalReward;

	
	public Order(String orderID, int orderStatus, Vector<OrderOfDish> orderDish,Address customerAddress){
		this.orderID=orderID;
		this.difficultyRating=0;
		this.orderStatus=orderStatus;
		this.orderDish=orderDish;
		this.customerAddress=customerAddress;
		this.expectedcookTime=this.calculateCookTime(orderDish);
		this.setDifficultyRating();
	}
	public Order(String orderID, Vector<OrderOfDish> orderDish,Address customerAddress){
		this.orderID=orderID;
		this.difficultyRating=0;
		this.orderStatus=1;
		this.orderDish=orderDish;
		this.customerAddress=customerAddress;
		this.expectedcookTime=this.calculateCookTime(orderDish);
		this.setDifficultyRating();
	}
	
	public Order(String orderID, int orderStatus,Address customerAddress){
		this.orderID=orderID;
		this.difficultyRating=0;
		this.orderStatus=orderStatus;
		this.customerAddress=customerAddress;
		this.setDifficultyRating();
	}
	public Order(Order order){
		this.orderID=order.getOrderID();
		this.difficultyRating=order.getDifficultyRating();
		this.orderStatus=order.getOrderStatus();
		this.orderDish=order.getOrderDish();
		this.customerAddress=order.getCustomerAddress();
		this.expectedcookTime=this.calculateCookTime(orderDish);
		this.setDifficultyRating();
		
	}
	
	public void addDish(OrderOfDish orderDish){
		this.orderDish.add(orderDish);
		this.expectedcookTime=this.expectedcookTime+this.calculateCookTime(orderDish);
	}
	/** (non-Javadoc)
	 *@param the status are:
	 * @param incomplete=1;
	 * @param inProgress=2;
	* @param  complete=3;
	*@param delivered=4;
	 */
	public int getOrderStatus(){
		return this.orderStatus;
	}
	public String getOrderID(){
		return this.orderID;
	}
	public int getDifficultyRating(){
		return this.difficultyRating;
	}
	public Address getCustomerAddress(){
		return this.customerAddress;
		
	}
	public void changeOrderDifficultyRating(int difficultyRating){
		this.difficultyRating=difficultyRating;
	}
	public Vector<OrderOfDish> getOrderDish(){
		return this.orderDish;
	}
	/** (non-Javadoc)
	 *@param the status are:
	 * @param incomplete=1;
	 * @param inProgress=2;
	* @param  complete=3;
	*@param delivered=4;
	 */
	public void setOrderStatus(int status){
		this.orderStatus=status;
	}
	/** (non-Javadoc)
	 *@param the status are:
	 * @param incomplete=1;
	 * @param inProgress=2;
	* @param  complete=3;
	*@param delivered=4;
	 */
	public void setOrderStatus(String status){
		if (status=="incomplete"){
			this.orderStatus=1;
		}
		else if(status=="inProgress"){
			this.orderStatus=2;
		}
		else if(status=="complete"){
			this.orderStatus=3;
		}
		else if(status=="delivered"){
			this.orderStatus=4;
		}
			
	}
	public void setExpectedDeliveryTime(long deliveryTime){
		this.expectedDeliveryTime=deliveryTime;
	}
	public long getExpectedDeliveryTime(){
		return this.expectedDeliveryTime;
	}
	public void setActualCookTime(long cookTime){
		this.actualCookTime=cookTime;
	}
	public long getActualCookTime(){
		return this.actualCookTime;
	}
	public void setActualDeliveryTime(long deliveryTime){
		this.actualDeliveryTime=deliveryTime;
	}
	public long getActualDeliveryTime(){
		return this.actualDeliveryTime;
	}
	public void setExpectedCookTime(long cookTime){
		this.actualCookTime=cookTime;
	}
	public long getExpectedCookTime(){
		return this.expectedcookTime;
	}
	public void setTotalReward(){
		if ((this.actualCookTime+this.actualDeliveryTime)>(1.15*(this.expectedcookTime+this.expectedDeliveryTime))){
			this.totalReward=0.5*(this.calculateReward());
		}
		else{
			this.totalReward=this.calculateReward();
		}
	}
	public Double getTotalReward(){
		return this.totalReward;
	}
	public Double calculateReward(){
		Double res=0.0;
		for (int i=0; i<this.orderDish.size();i++){
			res= res+this.orderDish.get(i).gestDish().getReward();
		}
		return res;
	}
	
	/** (non-Javadoc)
	 * @update the ExpectedCookTime by take the dish that take the longest time to cook
	 */
	public long calculateCookTime(Vector<OrderOfDish> orderDish){
		long res=0;
		for (int i=0; i<orderDish.size();i++){
			long getdishExpectedCookTime=orderDish.get(i).gestDish().getdishExpectedCookTime();
			if (res<getdishExpectedCookTime){
				res=getdishExpectedCookTime;
			}
		}
		return res;
	}
	public long calculateCookTime(OrderOfDish orderDish){
		long res=0;
			long quantity=(long)orderDish.getquantity();
			res=res+(orderDish.gestDish().getdishExpectedCookTime() * quantity);
		return res;
	}
	public void setDifficultyRating(){
		for (int i=0;i<this.orderDish.size();i++){
			this.difficultyRating=this.difficultyRating+ (this.orderDish.get(i).gestDish().getDishDifficultyRating()*this.orderDish.get(i).getquantity());
		}
	}

}
