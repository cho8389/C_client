package carmen.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.OrdVO;
import carmen.domain.PayVO;
import carmen.handler.ListHandler;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PayUpdatePaneController {
	
	@FXML
	public GridPane paypane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;

	@FXML
	private TextField pay_price;
	
	private DateUtils du=new DateUtils();
	private PayController pc;
	private ListHandler lh=new ListHandler();
	private PayVO pay_up;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public PayUpdatePaneController() {
    }
	
	@FXML
    private void initialize() {
		
    	pay_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	pay_price.setText(oldValue);
    	    }
    	});
    }
	
    public void showreqDetail(PayVO pay) {
    	pay_price.setText(Integer.toString(pay.getPay_price()));
    }
    
    @FXML
    public void pay_up() {
    	PayVO pay=pay_up;
    	pay.setPay_price(du.sti(pay_price.getText()));
    	lh.update("pay", target,pay);
    	roc.payview();
    }
    @FXML
    public void pay_upcan() {
    	roc.payview();
    }
    public void setPayController(PayController pc) {
    	this.pc=pc;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setPay_up(PayVO pay) {
    	pay_up=pay;
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
