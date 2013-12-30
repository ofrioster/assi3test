package run;
import java.util.Vector;


public class Statistics implements StatisticsInterface {
	
	private Double moneyGain;
	private Vector<Order> deliveredOrders;
	private Vector<Ingredient> ingredientsConsumed;
	private Warehouse warehouse;
	private Vector<Double> expectedRewards;
	private Vector<Double> receivedRewards;
	
	public Statistics(Warehouse warehouse){
		this.moneyGain=0.0;
		this.deliveredOrders=new Vector<Order>();
		this.ingredientsConsumed=new Vector<Ingredient>();
		 this.expectedRewards = new  Vector<Double>();
		 this.receivedRewards = new Vector<Double>();
		 this.warehouse=warehouse;
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
			this.receivedRewards.add(newOrder.getTotalReward());
			this.expectedRewards.add(newOrder.calculateReward());
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
		if(order.getOrderStatus()==4){
			this.deliveredOrders.add(order);
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
	/** (non-Javadoc)
	 * @param order to add her ingredients
	 */
	public synchronized void upDateMoneyGain(Order order){
		this.moneyGain=this.moneyGain + order.getTotalReward();
		this.receivedRewards.add(order.getTotalReward());
		this.expectedRewards.add(order.calculateReward());
		
	}
	/** 
	 * @param order to add her inredients
	 */
	public synchronized void addinConsumedIgredients(Order order){
		Boolean found=false;
		for (int i=0; i<order.getOrderDish().size();i++){
			for (int w=0; w<order.getOrderDish().get(i).gestDish().getDishIngredients().size();w++){
				found=false;
				for (int k=0; k<this.ingredientsConsumed.size() &&!found;k++){
					if (order.getOrderDish().get(i).gestDish().getDishIngredients().get(w).getIngredientName().equals(this.ingredientsConsumed.get(k).getIngredientName())){
						found=true;
					}
				}
				if(!found){
					this.ingredientsConsumed.add(this.warehouse.getTheIngredient(order.getOrderDish().get(i).gestDish().getDishIngredients().get(w)));
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

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Statistics: MoneyGained=");
		builder.append(moneyGain);
		builder.append("\n Expected Rewards: ");
		builder.append(expectedRewards);
		builder.append("\n Received Rewards: ");
		builder.append(receivedRewards);
		builder.append("\n");
		for ( Order order : deliveredOrders){
			builder.append("\n Order:");
			builder.append(order);
		}
		builder.append("\n");
		for ( Ingredient ingredient : ingredientsConsumed){
			builder.append("\n Ingredient:");
			builder.append(ingredient);
		}
		return builder.toString();
	}

}
