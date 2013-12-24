package run;

public class OrderOfDish implements OrderOfDishInterface{
	
	private Dish dish;
	private int quantity;
	private int quantityLeft;
	private int orderStatus;
	// the status are:
	private final int incomplete=1;
	private final int inProgress=2;
	private final int complete=3;
	
	
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
	//	System.out.println("setOrderStatus111 "+status);
	}
	
	public synchronized void setOneDishIsDone(){
		if(this.quantityLeft>0){
			this.quantityLeft=this.quantityLeft-1;
		}
	}
	public synchronized int getQuantityLeft(){
		return this.quantityLeft;
	}

}
