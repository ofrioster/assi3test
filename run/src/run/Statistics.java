package run;
import java.util.Vector;


public class Statistics implements StatisticsInterface {
	
	private Double moneyGain;
	private Vector<Order> deliveredOrders;
	private Vector<Ingredient> ingredientsConsumed;
	
	public Statistics(){
		this.moneyGain=0.0;
		this.deliveredOrders=new Vector<Order>();
		this.ingredientsConsumed=new Vector<Ingredient>();
	}
	/**
	 * @param newOrder that has been finish
	 * 
	 */
	public Statistics(Order newOrder){
		this.deliveredOrders=new Vector<Order>();
		this.ingredientsConsumed=new Vector<Ingredient>();
		if(newOrder.getOrderStatus()==4){
			this.deliveredOrders.add(newOrder);
			this.moneyGain=newOrder.getTotalReward();
			this.addinConsumedIgredients(newOrder);
		}
		else{
			System.out.println("Problem with order");
		}
	}
	/** (non-Javadoc)
	 * @param new order to add
	 * @ update all the data on this object 
	 */
	public synchronized void addDeliveredOrder(Order order){
		try{
		System.out.println("order.getOrderStatus() "+order.getOrderStatus());
		System.out.println("888 getOrderID - "+order.getOrderID()+"888 reward- "+order.getTotalReward());
		if(order.getOrderStatus()==4){
			this.deliveredOrders.add(order);
			//this.moneyGain=order.getTotalReward();
			this.upDateMoneyGain(order);
			this.addinConsumedIgredients(order);
		}
		else{
			System.out.println("Problem with order");
		}
		}
		catch (Exception e){
			System.out.println("Problem with order");
		}
	}
	public synchronized void upDateMoneyGain(Order order){
		System.out.println("order.getTotalReward() "+order.getTotalReward());
		System.out.println("this.moneyGain "+this.moneyGain);
		this.moneyGain=this.moneyGain+ order.getTotalReward();
		System.out.println("dish name "+order.getOrderDish().get(0).gestDish().getDishName());
		System.out.println("order.getTotalReward() "+order.getTotalReward());
		System.out.println("this.moneyGain "+this.moneyGain);
		int k=0;
		
	}
	/** (non-Javadoc)
	 * @param order to add her inredients
	 */
	public void addinConsumedIgredients(Order order){
		Boolean found=false;
		for (int i=0; i<order.getOrderDish().size();i++){
			for (int w=0; w<order.getOrderDish().get(i).gestDish().getDishIngredients().size();w++){
				found=false;
				for (int k=0; k<this.ingredientsConsumed.size() &&!found;k++){
					if (order.getOrderDish().get(i).gestDish().getDishIngredients().get(w).getIngredientName()==this.ingredientsConsumed.get(k).getIngredientName()){
						this.ingredientsConsumed.get(k).returnIngredient();
						found=true;
					}
				}
				if(!found){
					this.ingredientsConsumed.add(order.getOrderDish().get(i).gestDish().getDishIngredients().get(w));
				}
			}
			
		}
	}
	public Double getMoneyGain(){
		return this.moneyGain;
	}
	public Vector<Order> getDeliveredOrders(){
		return this.deliveredOrders;
	}
	public Vector<Ingredient> getIngredientsConsumed(){
		return this.ingredientsConsumed;
	}

}
