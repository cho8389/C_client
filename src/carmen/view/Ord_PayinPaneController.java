package carmen.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.OrdVO;
import carmen.domain.PayVO;
import carmen.domain.ReqVO;
import carmen.handler.ListHandler;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Ord_PayinPaneController {
	
	@FXML
	public GridPane ord_payinpane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;
	
	@FXML
	private DatePicker pay_date;
	@FXML
	private TextField pay_price;
	
	private DateUtils du=new DateUtils();
	private PayController pc;
	private ListHandler lh=new ListHandler();
	private int ordreq_id;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public Ord_PayinPaneController() {
    }
	
	@FXML
    private void initialize() {
		
    	pay_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	pay_price.setText(oldValue);
    	    }
    	});
    }
	
	@FXML
	public void pay_in() {
		PayVO pay=new PayVO();
		pay.setOrdreq_id(ordreq_id);
		pay.setEmp_id(main.getMem().getEmp_id());
		pay.setPay_date(du.asDate(pay_date.getValue()));
		pay.setPay_price(du.sti(pay_price.getText()));
    	lh.insert("pay", target,pay);
    	roc.payview();
	}
	@FXML
	public void pay_incan() {
		roc.ordview();
	}
	public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setOrdreq_id(int ordreq_id) {
    	this.ordreq_id=ordreq_id;
    }
    public void setPayController(PayController pc) {
    	this.pc=pc;
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
