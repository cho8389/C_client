package carmen.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.ReqVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ReqUpdatePaneController {
	
	@FXML
	public GridPane requpdatepane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;

	@FXML
	private ComboBox ordreq_type;
	@FXML
	private DatePicker ordreq_edate;
	@FXML
	public TextField ordreq_customer;
	@FXML
	private TextField ordreq_region;
	@FXML
	private TextField ordreq_name;
	@FXML
	private TextField ordreq_purchase;
	@FXML
	private TextField ordreq_amount;
	@FXML
	private TextField ordreq_price;
	@FXML
	private TextField ordreq_cost;
	
	private DateUtils du=new DateUtils();
	private ReqController rc;
	private ListHandler lh=new ListHandler();
	private ReqVO req_up;
	private RootController roc;
	private Convert con=new Convert();
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public ReqUpdatePaneController() {
    }
	
	@FXML
    private void initialize() {
    	ordreq_cost.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ordreq_cost.setText(oldValue);
    	    }
    	});
    	ordreq_amount.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ordreq_amount.setText(oldValue);
    	    }
    	});
    	ordreq_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ordreq_price.setText(oldValue);
    	    }
    	});
    }
	
    public void showreqDetail(ReqVO req) {
    	ordreq_type.setValue(ordreq_type(req.getOrdreq_type()));
    	ordreq_edate.setValue(du.asLocalDate(req.getOrdreq_edate()));
    	ordreq_customer.setText(req.getOrdreq_customer());
    	ordreq_region.setText(req.getOrdreq_region());
    	ordreq_name.setText(req.getOrdreq_name());
    	ordreq_purchase.setText(req.getOrdreq_purchase());
    	ordreq_amount.setText(Integer.toString(req.getOrdreq_amount()));
    	ordreq_price.setText(Integer.toString(req.getOrdreq_price()));
    	ordreq_cost.setText(Integer.toString(req.getOrdreq_cost()));
    }
    
    @FXML
    public void req_update() {
    	ReqVO req=req_up;
    	req.setOrdreq_type(Integer.toString(ordreq_type.getSelectionModel().getSelectedIndex()+1));
    	req.setOrdreq_edate(du.asDate(ordreq_edate.getValue()));
    	req.setOrdreq_customer(ordreq_customer.getText());
    	req.setOrdreq_region(ordreq_region.getText());
    	req.setOrdreq_name(ordreq_name.getText());
    	req.setOrdreq_purchase(ordreq_purchase.getText());
    	req.setOrdreq_amount(du.sti(ordreq_amount.getText()));
    	req.setOrdreq_price(du.sti(ordreq_price.getText()));
    	req.setOrdreq_cost(du.sti(ordreq_cost.getText()));
    	lh.update("req", target, req);
    	roc.reqview();
    }
    @FXML
    public void req_updatecan() {
    	roc.reqview();
    }
    
    public String ordreq_type(String type) {
    	if(type.equals("1")) {
			return "³³Ç°";
		}else if(type.equals("2")) {
			return "»ùÇÃ";
		}
		return null;
    }
    public void setReqController(ReqController rc) {
    	this.rc=rc;
    }
    public void setReq(ReqVO req) {
    	req_up=req;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setMain(Main main) {
        this.main = main;
    }

    
}
