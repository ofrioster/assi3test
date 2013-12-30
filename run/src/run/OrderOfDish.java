package run;

public class OrderOfDish implements OrderOfDishInterface{
	
	private Dish dish;
	private int quantity;
	private int quantityLeft;
	private int orderStatus;
	private final int INCOMPLETE=1;
	private final int INPROGRESS=2;
	private final int COMPLETE=3;
	
	
	public OrderOfDish (Dish dish,int quantity){
		this.dish=dish;
		this.quantity=quantity;
		this.orderStatus=1;
		this.quantityLeft=quantity;
	}
	public OrderOfDish (Dish dish,int quantity, int orderStatus){
		this.dish=dish;
		this.quantity=quantity;
		this.orderStatus=orderStatus;
		this.quantityLeft=quantity;
	}
	
	/** (non-Javadoc)
	 *@param the status are:
	 * @param incomplete=1;
	 * @param inProgress=2;
	* @param  complete=3;
	 */
	public synchronized int getOrderStatus(){
		return this.orderStatus;
	}
	public synchronized int getquantity(){
		return this.quantity;
	}
	public synchronized void setquantity(int quantity){
		this.quantity=quantity;
		this.quantityLeft=quantity;
	}
	public Dish gestDish(){
		return this.dish;
	}
	
	/** (non-Javadoc)
	 *@param the status are:
	 * @param incomplete=1;
	 * @param inProgress=2;
	* @param  complete=3;
	 */
	public synchronized void setOrderStatus(int status){
		this.orderStatus=status;
	}
	
	public synchronized void setOneDishIsDone(){
		if(this.quantityLeft>0){
			this.quantityLeft=this.quantityLeft-1;
		}
	}
	public synchronized int getQuantityLeft(){
		return this.quantityLeft;
	}
//	public String toString(){
//		String res=" dish- "+this.dish.toString()+" quantity- "+this.quantity+" quantityLeft- "+this.quantityLeft+" orderStatus- "+this.orderStatus;
//		return res;
//	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderOfDish [dish=");
		builder.append(dish);
		builder.append("][quantity=");
		builder.append(quantity);
		builder.append("][quantityLeft=");
		builder.append(quantityLeft);
		builder.append("][orderStatus=");
		builder.append(orderStatus);
		builder.append("]");
		return builder.toString();
	}


}
