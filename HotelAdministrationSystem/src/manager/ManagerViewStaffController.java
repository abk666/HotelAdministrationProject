package manager;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import bean.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utility.ManagerDataUtils;


public class ManagerViewStaffController implements Initializable{

    @FXML
    private PieChart genderChart;

    @FXML
    private Label lblGender;

    @FXML
    private TableView<Staff> genderTable;

    @FXML
    private TableColumn<Staff, String> staffGender;

    @FXML
    private TableColumn<Staff, Double> genderCount;

    @FXML
    private TableView<Staff> roleTable;

    @FXML
    private TableColumn<Staff, String> staffRole;

    @FXML
    private TableColumn<Staff, Integer> roleCount;


    @FXML
    private PieChart roleChart;
    
    @FXML
    private JFXButton genderButton;

    @FXML
    private JFXButton roleButton;

    @FXML
    private Label lblRole;
    String genderText="";
    String roleText="";
    Integer genderClickCount=0;
    Integer roleClickCount=0;

    private final ManagerDataUtils managerDataUtils=new ManagerDataUtils();
    @FXML
    void processBack(MouseEvent event) {

    }

    @FXML
    void processGender(MouseEvent event) throws SQLException {
    	this.genderClickCount+=1;
    	if(this.genderClickCount%2==0) {
    		genderTable.setVisible(false);
    		genderButton.setText("View");
    		
    	}else {
    		genderTable.setVisible(true);
    	    genderTable.setItems(managerDataUtils.getStaffGender("select staffGender,count(*) as genderCount from staff group by staffGender;"));
    	    genderButton.setText("Hide");
    	}
    
    
    }

    @FXML
    void processRole(MouseEvent event) throws SQLException {
    	this.roleClickCount+=1;
    	if(this.roleClickCount%2==0) {
    		roleTable.setVisible(false);
    		roleButton.setText("View");
    	}
    	else {
    		roleTable.setVisible(true);
    		roleTable.setItems(managerDataUtils.getStaffRole("select staffId,staffRole,count(*) as roleCount from staff group by staffRole;"));
    		roleButton.setText("Hide");
    		
    	}
    }
    
  

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ObservableList<Staff>staffList=managerDataUtils.getStaffGender("select staffGender,count(*) as genderCount from staff group by staffGender;");
			ObservableList<Data>genderData=FXCollections.observableArrayList();
			for(Staff staff:staffList) {
				genderData.add(new Data(staff.getGender(), staff.getGenderCount()));
			}
			genderChart.setData(genderData);
			for(Data data: genderData) {
				data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						lblGender.setText(String.valueOf(data.getName() +" = "+ Math.round(data.getPieValue())));
						
					}
				});
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ObservableList<Staff>staffList=managerDataUtils.getStaffRole("select staffId,staffRole,count(*) as roleCount from staff group by staffRole;");
			ObservableList<Data>roleData=FXCollections.observableArrayList();
			for(Staff staff:staffList) {
				roleData.add(new Data(staff.getRole(), staff.getRoleCount()));
			}
			roleChart.setData(roleData);
			for(Data data: roleData) {
				data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent arg0) {
						lblRole.setText(String.valueOf(data.getName() +" = "+ Math.round(data.getPieValue())));
						
					}
				});
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		staffGender.setCellValueFactory(new PropertyValueFactory<>("staffGender"));
		genderCount.setCellValueFactory(new PropertyValueFactory<>("genderCount"));
		staffRole.setCellValueFactory(new PropertyValueFactory<>("staffRole"));
		roleCount.setCellValueFactory(new PropertyValueFactory<>("roleCount"));
		
		
	}

}
