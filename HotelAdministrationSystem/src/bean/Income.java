package bean;



import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Income {
private IntegerProperty incomeId;
private StringProperty date;
private DoubleProperty totalAmount;


public Income(Integer incomeId, String date, Double totalAmount) {
	super();
	this.incomeId = new SimpleIntegerProperty(incomeId) ;
	this.date = new SimpleStringProperty(date) ;
	this.totalAmount =new SimpleDoubleProperty(totalAmount) ;
}

public Income(String date, Double totalAmount) {
	super();
	this.date = new SimpleStringProperty(date) ;
	this.totalAmount =new SimpleDoubleProperty(totalAmount) ;
}

public Integer getIncomeId() {
	return incomeId.get();
}
public void setIncomeId(Integer incomeId) {
	this.incomeId = new SimpleIntegerProperty(incomeId) ;
}
public String getDate() {
	return date.get();
}
public void setDate(String date) {
	this.date =  new SimpleStringProperty(date) ;
}
public Double getTotalAmount() {
	return totalAmount.get();
}
public void setTotalAmount(Double totalAmount) {
	this.totalAmount =new SimpleDoubleProperty(totalAmount) ;
}

}
