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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Ord_inPaneController {
	
	@FXML
	public GridPane ord_inpane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;

	@FXML
	private DatePicker ord_indate;
	@FXML
	private TextField ord_inamount;
	
	private DateUtils du=new DateUtils();
	private OrdController oc;
	private ListHandler lh=new ListHandler();
	private Gson gson=new Gson();
	private OrdVO ord_in;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public Ord_inPaneController() {
    }
	
	@FXML
    private void initialize() {
    	ord_inamount.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ord_inamount.setText(oldValue);
    	    }
    	});
    }
	
	@FXML
	public void ord_in() {
		OrdVO ord=ord_in;
    	ord.setOrd_inamount(du.sti(ord_inamount.getText()));
    	ord.setOrd_indate(du.asDate(ord_indate.getValue()));
    	ord.setOrd_state("2");
    	lh.update("ord", target,ord);
    	ReqVO req=new ReqVO();
    	req.setOrdreq_id(ord.getOrdreq_id());
    	req.setOrdreq_state("4");
    	lh.updatestate("req", target, req);
    	
    	JSONObject data=new JSONObject();
		data.put("type","req");
		data.put("json",gson.toJson(req));
		main.ws.send(data.toString());
		
		roc.ordview();
	}
	@FXML
	public void ord_incan() {
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
    public void setMain(Main main) {
        this.main = main;
    }
    public void setOrd_in(OrdVO ord_in) {
        this.ord_in=ord_in;
    }
    public OrdVO getOrd_in() {
        return ord_in;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
}
