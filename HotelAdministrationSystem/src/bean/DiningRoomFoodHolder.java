package bean;

public class DiningRoomFoodHolder {
	
	private DiningRoomFood diningRoomFood;
	
	private final static DiningRoomFoodHolder DININGROOMFOOD_INSTANCE = new DiningRoomFoodHolder();

	public DiningRoomFood getDiningRoomFood() {
		return diningRoomFood;
	}

	public void setDiningRoomFood(DiningRoomFood diningRoomFood) {
		this.diningRoomFood = diningRoomFood;
	}

	public static DiningRoomFoodHolder getDiningRoomFoodInstance() {
		return DININGROOMFOOD_INSTANCE;
	}

}
