package bean;

public class RefrigeratorFoodHolder {

	private RefrigeratorFood refrigeratorFood;
	
	private final static RefrigeratorFoodHolder REFRIGERATORFOOD_INSTANCE = new RefrigeratorFoodHolder();

	public RefrigeratorFood getRefrigeratorFood() {
		return refrigeratorFood;
	}

	public void setRefrigeratorFood(RefrigeratorFood refrigeratorFood) {
		this.refrigeratorFood = refrigeratorFood;
	}

	public static RefrigeratorFoodHolder getRefrigeratorfoodInstance() {
		return REFRIGERATORFOOD_INSTANCE;
	}
	
}
