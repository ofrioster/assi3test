package run;
import java.util.Vector;


public class Statistics implements StatisticsInterface{
	
	private Double moneyGain;
	private Vector<Order> deliveredOrders;
	private Vector<Ingredient> ingredientsConsumed;
	
	public Statistics(){
		this.moneyGain=0.0;
	}
	/**
	 * @param newOrder that has been finish
	 * 
	 */
	public Statistics(Order newOrder){
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
	public void addDeliveredOrder(Order order){
		if(order.getOrderStatus()==4){
			this.deliveredOrders.add(order);
			this.moneyGain=order.getTotalReward();
			this.addinConsumedIgredients(order);
		}
		else{
			System.out.println("Priblem with order");
		}
	}
	public void upDateMoneyGain(Order order){
		this.moneyGain=this.moneyGain+ order.getTotalReward();
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
					if(!found){
						this.ingredientsConsumed.add(order.getOrderDish().get(i).gestDish().getDishIngredients().get(k));
					}
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
