package carmen.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.OrdVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class OrdPaneController {
	
	@FXML
	public GridPane ordpane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;
	@FXML
	private Button subbtn3;
	@FXML
	private Button subbtn4;
	
	@FXML
	private ComboBox ord_state;
	@FXML
	private TextField ord_emp_id;
	@FXML
	private DatePicker ord_date;
	@FXML
	private DatePicker ord_date_e;
	@FXML
	private TextField ord_amount;
	@FXML
	private TextField ord_cost;
	@FXML
	private TextField ord_price;
	@FXML
	private DatePicker ord_indate;
	@FXML
	private TextField ord_inamount;
	
	private DateUtils du=new DateUtils();
	private OrdController oc;
	private ListHandler lh=new ListHandler();
	private Convert con=new Convert();
	private OrdVO ord_in;
	private int ordreq_id;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public OrdPaneController() {
    }
	
	@FXML
    private void initialize() {
		
    	ord_cost.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ord_cost.setText(oldValue);
    	    }
    	});
    	ord_amount.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ord_amount.setText(oldValue);
    	    }
    	});
    	ord_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ord_price.setText(oldValue);
    	    }
    	});
    	ord_inamount.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ord_inamount.setText(oldValue);
    	    }
    	});
    	subbtn4.setOnAction((event) -> {
			roc.AllList(ordreq_id);
		});
    }
	
    public void showreqDetail(OrdVO ord) {
    	ord_state.setValue(ord_state(ord.getOrd_state()));
    	ord_emp_id.setText(ord.getEmp_id());
    	ord_date.setValue(du.asLocalDate(ord.getOrd_date()));
    	ord_amount.setText(Integer.toString(ord.getOrd_amount()));
    	ord_cost.setText(Integer.toString(ord.getOrd_cost()));
    	ord_price.setText(Integer.toString(ord.getOrd_price()));
    	ord_inamount.setText(Integer.toString(ord.getOrd_inamount()));
    	ord_indate.setValue(du.asLocalDate(ord.getOrd_indate()));
    	
    	ord_in=ord;
    	ordreq_id=ord.getOrdreq_id();
    	if(ord.getOrd_state().equals("1")) {
    		if(con.rolecheck("order",main.getRole())) {
    			ord_inbtn();
    		}
    	}
    	if(ord.getOrd_state().equals("2")) {
    		subbtn2.setVisible(false);
    	}
    	subbtn4.setVisible(true);
    	if(con.rolecheck("account",main.getRole())) {
			ord_payin();
		}else if(con.rolecheck("order",main.getRole())) {
			if(ord.getEmp_id().equals(main.getMem().getEmp_id())) {
				ord_up();
			}
		}
    	if(con.rolecheck("admin",main.getRole())) {
    		adminbt();
    	}
    }
    
    @FXML
    public void ord_search() {
    	OrdVO ord=new OrdVO(0,0,ord_emp_id.getText(),du.asDate(ord_date.getValue()),
    			du.sti(ord_amount.getText()),du.sti(ord_cost.getText()),
    			du.sti(ord_price.getText()),du.asDate(ord_indate.getValue()),
    			du.sti(ord_inamount.getText()),Integer.toString(ord_state.getSelectionModel().getSelectedIndex()+1),
    			du.asDate(ord_date_e.getValue())
    			);
    	oc.SearchList(ord);
    }
    public String ord_state(String state) {
    	if(state.equals("1")) {
			return "발주";
		}else if(state.equals("2")) {
			return "입고";
		}
		return null;
    }
    public void setOrdController(OrdController oc) {
    	this.oc=oc;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    public void ord_inbtn() {
    	subbtn2.setText("입고");
		subbtn2.setVisible(true);
		subbtn2.setOnAction((event) -> {
			roc.ord_inpane(ord_in);
		});
    }
    public void ord_payin() {
    	subbtn3.setText("결제");
    	subbtn3.setVisible(true);
    	subbtn3.setOnAction((event) -> {
    		roc.ord_payinpane(ord_in.getOrdreq_id());
		});
    }
    public void ord_up() {
    	subbtn3.setText("수정");
    	subbtn3.setVisible(true);
    	subbtn3.setOnAction((event) -> {
    		roc.ordupdatepane(ord_in);
		});
    }
    
    public void adminbt() {
    	subbtn3.setText("삭제");
    	subbtn3.setVisible(true);
    	subbtn3.setOnAction((event) -> {
    		lh.admin_del(target, "ord",ord_in.getOrdreq_id(),ord_in.getOrd_id());
		});
    }
}
