package carmen.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.OrdVO;
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

public class OrdUpdatePaneController {
	
	@FXML
	public GridPane ordupdatepane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;
	
	@FXML
	private TextField ord_amount;
	@FXML
	private TextField ord_cost;
	@FXML
	private TextField ord_price;
	
	private DateUtils du=new DateUtils();
	private OrdController oc;
	private ListHandler lh=new ListHandler();
	private Convert con=new Convert();
	private OrdVO ord_up;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public OrdUpdatePaneController() {
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
    }
	
    public void showreqDetail(OrdVO ord) {
    	ord_amount.setText(Integer.toString(ord.getOrd_amount()));
    	ord_cost.setText(Integer.toString(ord.getOrd_cost()));
    	ord_price.setText(Integer.toString(ord.getOrd_price()));
    	
    }
    
    @FXML
    public void ord_up() {
    	OrdVO ord=ord_up;
    	ord.setOrd_amount(du.sti(ord_amount.getText()));
    	ord.setOrd_cost(du.sti(ord_cost.getText()));
    	ord.setOrd_price(du.sti(ord_price.getText()));
    	lh.update("ord", target, ord);
    	roc.ordview();
    }
    @FXML
    public void ord_upcan() {
    	roc.ordview();
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
    public void setOrd(OrdVO ord) {
    	ord_up=ord;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
