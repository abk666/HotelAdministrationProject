package reception;

import java.net.URL;
import java.util.ResourceBundle;

import bean.BillSlipHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class BillSlipController implements Initializable{

    @FXML
    private Label lblRoomNo;

    @FXML
    private Label lblRoomPrice;

    @FXML
    private Label lblDiningCost;

    @FXML
    private Label lblInRoomCost;

    @FXML
    private Label lblTotalCost;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblRoomNo.setText(BillSlipHolder.getRoomNo());
		lblInRoomCost.setText(BillSlipHolder.getInRoomCost());
		lblDiningCost.setText(BillSlipHolder.getDiningRoomPrice());
		lblRoomPrice.setText(BillSlipHolder.getRoomPrice());
		lblTotalCost.setText(BillSlipHolder.getTotalPrice());
		
	}

}
