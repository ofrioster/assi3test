package run;
import java.util.Vector;

public class Order implements OrderInterface {
	
	private String orderID;
	private int difficultyRating;
	private int orderStatus;
	private Vector<OrderOfDish> orderDish;
	private Address customerAddress;
	private final int INCOMPLETE=1;
	private final int INPROGRESS=2;
	private final int COMPLETE=3;
	private final int DELIVERED=4;
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
		this.orderStatus=INCOMPLETE;
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
		if(this.expectedcookTime<orderDish.gestDish().getdishExpectedCookTime()){
			this.expectedcookTime=orderDish.gestDish().getdishExpectedCookTime();
		}
		this.difficultyRating=0;
		this.setDifficultyRating();
	}
	/** (non-Javadoc)
	 *@param the status are:
	 * @param INCOMPLETE=1;
	 * @param INPROGRESS=2;
	* @param  COMPLETE=3;
	*@param DELIVERED=4;
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
		System.out.println(" order  change difficulty: "+this.difficultyRating);
	}
	
	public Vector<OrderOfDish> getOrderDish(){
		return this.orderDish;
	}
	
	
	/** (non-Javadoc)
	 *@param the status are:
	 * @param INCOMPLETE=1;
	 * @param INPROGRESS=2;
	* @param  COMPLETE=3;
	*@param DELIVERED=4;
	 */
	public void setOrderStatus(int status){
		this.orderStatus=status;
	}
	
	public void updateStatus(){
		this.orderStatus++;
		if (this.orderStatus > DELIVERED)    {
		System.out.print("error with order status update");
		}
	}
	/** (non-Javadoc)
	 *@param the status are:
	 * @param INCOMPLETE=1;
	 * @param INPROGRESS=2;
	* @param  COMPLETE=3;
	*@param DELIVERED=4;
	 */
	public void setOrderStatus(String status){
		if (status=="incomplete"){
			this.orderStatus=INCOMPLETE;
		}
		else if(status=="inProgress"){
			this.orderStatus=INPROGRESS;
		}
		else if(status=="complete"){
			this.orderStatus=COMPLETE;
		}
		else if(status=="delivered"){
			this.orderStatus=DELIVERED;
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
		this.expectedcookTime=cookTime;
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
			res= res+(this.orderDish.get(i).gestDish().getReward()*this.orderDish.get(i).getquantity());
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
			System.out.println("dish name: "+orderDish.get(i).gestDish().getDishName()+" getdishExpectedCookTime: "+getdishExpectedCookTime );
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
			this.difficultyRating=this.difficultyRating+ this.orderDish.get(i).gestDish().getDishDifficultyRating();
		}
	}
	public void setAddress(Address tmpAddress) {
		this.customerAddress=tmpAddress;
	}
	public void setOrderOfDish(Vector<OrderOfDish> tmpOrderOfDish) {
		this.orderDish=tmpOrderOfDish;
	}
	
	public int numberOfMeals(){
		int retnumberOfMeals = 0;
		for (OrderOfDish dish : this.orderDish){
			retnumberOfMeals += dish.getquantity();
			
			
		
		}
		return retnumberOfMeals;
	}
	
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [ID=");
		builder.append(orderID);
		builder.append("][Status=");
		builder.append(orderStatus);
		builder.append("][difficultyRating=");
		builder.append(difficultyRating);
		builder.append("][");
		builder.append(customerAddress);
		builder.append("][expectedcookTime=");
		builder.append(expectedcookTime);
		builder.append(", actualCookTime=");
		builder.append(actualCookTime);
		builder.append(", expectedDeliveryTime=");
		builder.append(expectedDeliveryTime);
		builder.append(", actualDeliveryTime=");
		builder.append(actualDeliveryTime);
		builder.append(", totalReward=");
		builder.append(totalReward);
		builder.append(", orderDish=");
		builder.append(orderDish);
		builder.append("]");
		return builder.toString();
	}
	public String toStringForLogger(){	
		String res="["+ this.orderID + "][numberOfMeals=" + this.numberOfMeals()+"]";
		return res;
	}
	public String toStringTimes() {
		String res="["+ this.orderID + "][expectedTimeToDeliver=" + this.expectedDeliveryTime + "][realTimeSpentToDeliver=" + this.actualDeliveryTime +"]";
		return res;
	}
}
