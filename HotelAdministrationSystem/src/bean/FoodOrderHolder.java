package bean;

public class FoodOrderHolder {
	
	private FoodOrder foodOrder;
	
	private final static FoodOrderHolder FOODORDER_INSTANCE = new FoodOrderHolder();

	public FoodOrder getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(FoodOrder foodOrder) {
		this.foodOrder = foodOrder;
	}

	public static FoodOrderHolder getFoodorderInstance() {
		return FOODORDER_INSTANCE;
	}

	
}
