package bean;

public class BillSlipHolder {
private static String roomNo;
private static String roomPrice;
private static String diningRoomPrice;
private static String totalPrice;
private static String inRoomCost;
public static String getRoomNo() {
	return roomNo;
}
public static void setRoomNo(String roomNo) {
	BillSlipHolder.roomNo = roomNo;
}
public static String getRoomPrice() {
	return roomPrice;
}
public static void setRoomPrice(String roomPrice) {
	BillSlipHolder.roomPrice = roomPrice;
}
public static String getDiningRoomPrice() {
	return diningRoomPrice;
}
public static void setDiningRoomPrice(String diningRoomPrice) {
	BillSlipHolder.diningRoomPrice = diningRoomPrice;
}
public static String getTotalPrice() {
	return totalPrice;
}
public static void setTotalPrice(String totalPrice) {
	BillSlipHolder.totalPrice = totalPrice;
}
public static String getInRoomCost() {
	return inRoomCost;
}
public static void setInRoomCost(String inRoomCost) {
	BillSlipHolder.inRoomCost = inRoomCost;
}





}
