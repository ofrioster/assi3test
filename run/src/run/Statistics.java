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
//		System.out.println("order.getOrderStatus() "+order.getOrderStatus());
//		System.out.println("888 getOrderID - "+order.getOrderID()+"888 reward- "+order.getTotalReward());
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
	/** (non-Javadoc)
	 * @param order to add her ingredients
	 */
	public synchronized void upDateMoneyGain(Order order){
//		System.out.println("order.getTotalReward() "+order.getTotalReward());
//		System.out.println("this.moneyGain "+this.moneyGain);
		this.moneyGain=this.moneyGain + order.getTotalReward();
		this.receivedRewards.add(order.getTotalReward());
		this.expectedRewards.add(order.calculateReward());
//		System.out.println("dish name "+order.getOrderDish().get(0).gestDish().getDishName());
//		System.out.println("order.getTotalReward() "+order.getTotalReward());
//		System.out.println("this.moneyGain "+this.moneyGain);
		int k=0;
		
	}
	/** 
	 * @param order to add her inredients
	 */
	public synchronized void addinConsumedIgredients(Order order){
		Boolean found=false;
//		System.out.println("order ID: "+order.getOrderID());
		for (int i=0; i<order.getOrderDish().size();i++){
			for (int w=0; w<order.getOrderDish().get(i).gestDish().getDishIngredients().size();w++){
		//		System.out.println(order.getOrderDish().get(i).gestDish().getDishIngredients().get(w));
				found=false;
				for (int k=0; k<this.ingredientsConsumed.size() &&!found;k++){
					if (order.getOrderDish().get(i).gestDish().getDishIngredients().get(w).getIngredientName().equals(this.ingredientsConsumed.get(k).getIngredientName())){
						found=true;
		//				System.out.println("this: "+(this.ingredientsConsumed.get(k)));
		//				System.out.println("new: "+order.getOrderDish().get(i).gestDish().getDishIngredients().get(w));
		//				for (int t=0;t<order.getOrderDish().get(i).getquantity();t++){
		//					int amoutToAdd=order.getOrderDish().get(i).gestDish().getDishIngredients().get(w).getNumberOfIngredient();
					//		this.ingredientsConsumed.get(k).addIngredientConsumed(order.getOrderDish().get(i).gestDish().getDishIngredients().get(w), order.getOrderDish().get(i).getquantity());
		//				}
					}
				}
				if(!found){
				//	Ingredient newIngredient=new Ingredient((order.getOrderDish().get(i).gestDish().getDishIngredients().get(w)));
					this.ingredientsConsumed.add(this.warehouse.getTheIngredient(order.getOrderDish().get(i).gestDish().getDishIngredients().get(w)));
				//	System.out.println("this: "+this.ingredientsConsumed.get(this.ingredientsConsumed.size()-1));
				//	System.out.println("new: "+order.getOrderDish().get(i).gestDish().getDishIngredients().get(w));
				//	int amoutOfDish=order.getOrderDish().get(i).getquantity();
				//	int amoutToAdd=order.getOrderDish().get(i).gestDish().getDishIngredients().get(w).getNumberOfIngredient()*(amoutOfDish-1);
				//	this.ingredientsConsumed.get(this.ingredientsConsumed.size()-1).addIngredientConsumed(order.getOrderDish().get(i).gestDish().getDishIngredients().get(w), amoutOfDish);
//					System.out.println()
			//		for (int t=0;t<order.getOrderDish().get(i).getquantity();t++){
			//			this.ingredientsConsumed.get(this.ingredientsConsumed.size()).returnIngredient();
			//		}
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
	@Override
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
