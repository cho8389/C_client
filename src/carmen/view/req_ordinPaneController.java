package carmen.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import carmen.Main;
import carmen.domain.OrdVO;
import carmen.domain.ReqVO;
import carmen.handler.ListHandler;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class req_ordinPaneController {
	
	@FXML
	public GridPane req_ordinPane;
	
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
	private Gson gson=new Gson();
	private int ordreq_id;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public req_ordinPaneController() {
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
    
    @FXML
    public void req_ordin() {
    	OrdVO ord=new OrdVO();
    	ord.setOrdreq_id(ordreq_id);
    	ord.setEmp_id(main.getMem().getEmp_id());
    	ord.setOrd_amount(du.sti(ord_amount.getText()));
    	ord.setOrd_cost(du.sti(ord_cost.getText()));
    	ord.setOrd_price(du.sti(ord_price.getText()));
    	ord.setOrd_state("1");
    	lh.insert("ord", target,ord);
    	ReqVO req=new ReqVO();
    	req.setOrdreq_id(ordreq_id);
    	req.setOrdreq_state("3");
    	lh.updatestate("req", target, req);
    	
    	JSONObject data=new JSONObject();
		data.put("type","req");
		data.put("json",gson.toJson(req));
		main.ws.send(data.toString());
		
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
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setOrdreq_id(int ordreq_id) {
    	this.ordreq_id=ordreq_id;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    public void ordincan() {
    	roc.reqview();
    }
}
