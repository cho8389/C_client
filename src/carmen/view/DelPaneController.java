package carmen.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.DelVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DelPaneController {
	
	@FXML
	public GridPane delpane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;
	@FXML
	private Button subbtn3;
	
	@FXML
	private ComboBox del_state;
	@FXML
	private TextField del_emp_id;
	@FXML
	private DatePicker del_date;
	@FXML
	private DatePicker del_date_e;
	@FXML
	private TextField del_name;
	@FXML
	private TextField del_customer;
	@FXML
	private TextField del_amount;
	@FXML
	private TextField del_cost;
	@FXML
	private TextField del_price;
	@FXML
	private TextField del_type;
	
	private DateUtils du=new DateUtils();
	private DelController dc;
	private ListHandler lh=new ListHandler();
	private Convert con=new Convert();
	private int ordreq_id;
	private int del_id;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public DelPaneController() {
    }
	
	@FXML
    private void initialize() {
		
    	del_cost.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	del_cost.setText(oldValue);
    	    }
    	});
    	del_amount.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	del_amount.setText(oldValue);
    	    }
    	});
    	del_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	del_price.setText(oldValue);
    	    }
    	});
    	subbtn3.setOnAction((event) -> {
			roc.AllList(ordreq_id);
		});
    }
	
    public void showreqDetail(DelVO del) {
    	del_state.setValue(del_state(del.getDel_state()));
    	del_emp_id.setText(del.getEmp_id());
    	del_date.setValue(du.asLocalDate(del.getDel_date()));
    	del_name.setText(del.getDel_name());
    	del_customer.setText(del.getDel_customer());
    	del_amount.setText(Integer.toString(del.getDel_amount()));
    	del_cost.setText(Integer.toString(del.getDel_cost()));
    	del_price.setText(Integer.toString(del.getDel_price()));
    	del_type.setText(del.getDel_type());
    	
    	ordreq_id=del.getOrdreq_id();
    	del_id=del.getDel_id();
    	if(del.getDel_state().equals("1")) {
    		del_inbtn();
    	}
    	if(del.getDel_state().equals("2")) {
    		subbtn2.setVisible(false);
    	}
    	subbtn3.setVisible(true);
    	if(con.rolecheck("admin",main.getRole())) {
    		adminbt();
    	}
    }
    
    @FXML
    public void del_search() {
    	DelVO del=new DelVO(0,0,del_emp_id.getText(),Integer.toString(del_state.getSelectionModel().getSelectedIndex()+1),
    			du.asDate(del_date.getValue()),del_name.getText(),del_customer.getText(),
    			du.sti(del_amount.getText()),du.sti(del_cost.getText()),du.sti(del_price.getText()),
    			del_type.getText(),du.asDate(del_date_e.getValue()));
    	dc.SearchList(del);
    }
    public String del_state(String state) {
    	if(state.equals("1")) {
			return "납품";
		}else if(state.equals("2")) {
			return "정산";
		}
		return null;
    }
    public void setDelController(DelController dc) {
    	this.dc=dc;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    public void del_inbtn() {
    	subbtn2.setText("정산");
		subbtn2.setVisible(true);
		subbtn2.setOnAction((event) -> {
			roc.del_calinpane(ordreq_id, del_id);
		});
    }
    
    public void adminbt() {
    	subbtn2.setText("삭제");
    	subbtn2.setVisible(true);
    	subbtn2.setOnAction((event) -> {
    		lh.admin_del(target, "del",ordreq_id,del_id);
		});
    }
}
