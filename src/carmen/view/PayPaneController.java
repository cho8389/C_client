package carmen.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.PayVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PayPaneController {
	
	@FXML
	public GridPane paypane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;
	@FXML
	private Button subbtn3;
	
	@FXML
	private TextField pay_emp_id;
	@FXML
	private DatePicker pay_date;
	@FXML
	private DatePicker pay_date_e;
	@FXML
	private TextField pay_price;
	
	private DateUtils du=new DateUtils();
	private PayController pc;
	private ListHandler lh=new ListHandler();
	private Convert con=new Convert();
	private int ordreq_id;
	private PayVO pay_up;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public PayPaneController() {
    }
	
	@FXML
    private void initialize() {
		
    	pay_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	pay_price.setText(oldValue);
    	    }
    	});
    	subbtn3.setOnAction((event) -> {
			roc.AllList(ordreq_id);
		});
    }
	
    public void showreqDetail(PayVO pay) {
    	pay_emp_id.setText(pay.getEmp_id());
    	pay_date.setValue(du.asLocalDate(pay.getPay_date()));
    	pay_price.setText(Integer.toString(pay.getPay_price()));
    	ordreq_id=pay.getOrdreq_id();
    	subbtn3.setVisible(true);
    	
    	pay_up=pay;
    	if(main.getMem().getEmp_id().equals(pay.getEmp_id())) {
    		if(con.rolecheck("account",main.getRole())) {
    			pay_up();
    		}
    	}
    	if(con.rolecheck("admin",main.getRole())) {
    		adminbt();
    	}
    }
    
    @FXML
    public void pay_search() {
    	PayVO pay=new PayVO(0,0,pay_emp_id.getText(),"0",
    			du.sti(pay_price.getText()),du.asDate(pay_date.getValue()),
    			du.asDate(pay_date_e.getValue()));
    	pc.SearchList(pay);
    }
    public void pay_up() {
    	subbtn2.setText("수정");
    	subbtn2.setVisible(true);
    	subbtn2.setOnAction((event) -> {
    		roc.payupdatepane(pay_up);
		});
    }
    public void setPayController(PayController pc) {
    	this.pc=pc;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    
    public void adminbt() {
    	subbtn2.setText("삭제");
    	subbtn2.setVisible(true);
    	subbtn2.setOnAction((event) -> {
    		lh.admin_del(target, "pay",pay_up.getOrdreq_id(),pay_up.getPay_id());
		});
    }
}
