package carmen.view;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import carmen.Main;
import carmen.domain.ReqVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import carmen.utils.WebSocket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ReqPaneController {
	
	@FXML
	public GridPane reqpane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;
	@FXML
	private Button subbtn3;
	@FXML
	private Button subbtn4;
	
	@FXML
	private ComboBox ordreq_state;
	@FXML
	private ComboBox ordreq_type;
	@FXML
	private TextField ordreq_emp_id;
	@FXML
	private DatePicker ordreq_date;
	@FXML
	private DatePicker ordreq_date_e;
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
	@FXML
	private TextField ordreq_fullcost;
	
	private DateUtils du=new DateUtils();
	private ReqController rc;
	private ListHandler lh=new ListHandler();
	private Gson gson=new Gson();
	private int ordreq_id;
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
    public ReqPaneController() {
    }
	WebSocket wsa=new WebSocket();
	@FXML
    private void initialize() {
		
    	ordreq_cost.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ordreq_cost.setText(oldValue);
    	    }else if(newValue!=null&&!ordreq_amount.getText().equals("")){
    	    	ordreq_fullcost.setText(Long.toString((long) (Long.parseLong(newValue)*Long.parseLong(ordreq_amount.getText())*1.1)));
    	    }
    	});
    	ordreq_amount.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ordreq_amount.setText(oldValue);
    	    }else if(newValue!=null&&!ordreq_cost.getText().equals("")) {
    	    	ordreq_fullcost.setText(Long.toString((long) (Long.parseLong(ordreq_cost.getText())*Long.parseLong(newValue)*1.1)));
    	    }
    	});
    	ordreq_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	ordreq_price.setText(oldValue);
    	    }
    	});
    	subbtn3.setOnAction((event) -> {
			roc.AllList(ordreq_id);
		});
    }
	
    public void showreqDetail(ReqVO req) {
    	ordreq_state.setValue(ordreq_state(req.getOrdreq_state()));
    	ordreq_type.setValue(ordreq_type(req.getOrdreq_type()));
    	ordreq_emp_id.setText(req.getEmp_id());
    	ordreq_date.setValue(du.asLocalDate(req.getOrdreq_date()));
    	ordreq_edate.setValue(du.asLocalDate(req.getOrdreq_edate()));
    	ordreq_customer.setText(req.getOrdreq_customer());
    	ordreq_region.setText(req.getOrdreq_region());
    	ordreq_name.setText(req.getOrdreq_name());
    	ordreq_purchase.setText(req.getOrdreq_purchase());
    	ordreq_amount.setText(Integer.toString(req.getOrdreq_amount()));
    	ordreq_price.setText(Integer.toString(req.getOrdreq_price()));
    	ordreq_cost.setText(Integer.toString(req.getOrdreq_cost()));
    	ordreq_fullcost.setText(Integer.toString((int)((req.getOrdreq_amount()*req.getOrdreq_cost()*1.1))));
    	
    	req_up=req;
    	ordreq_id=req.getOrdreq_id();
    	if(req.getOrdreq_state().equals("1")) {
    		if(con.rolecheck("order",main.getRole())) {
    			reqcheck_btn();
    		}
    		subbtn4.setVisible(false);
    	}
    	if(req.getOrdreq_state().equals("2")) {
    		if(con.rolecheck("order",main.getRole())) {
    			ordin_btn();
    		}
    		subbtn4.setVisible(false);
    	}
    	if(req.getOrdreq_state().equals("3")) {
    		subbtn2.setVisible(false);
    	}
    	if(req.getOrdreq_state().equals("4")) {
    		subbtn2.setVisible(false);
    		subbtn4.setVisible(false);
    	}
    	if(req.getOrdreq_state().equals("5")) {
    		subbtn2.setVisible(false);
    		subbtn4.setVisible(false);
    	}
    	if(req.getOrdreq_state().equals("6")) {
    		subbtn2.setVisible(false);
    		subbtn4.setVisible(false);
    	}
    	if(req.getOrdreq_state().equals("3")) {
    		if(con.rolecheck("order",main.getRole())) {
    			subbtn4.setVisible(true);
    		}
    	}
    	subbtn3.setVisible(true);
    	
    	if(con.rolecheck("admin",main.getRole())) {
    		adminbt();
    	}
    }
    
    @FXML
    public void req_search() {
    	ReqVO req=new ReqVO(0,ordreq_emp_id.getText(),du.asDate(ordreq_date.getValue()),du.asDate(ordreq_edate.getValue()),
    			ordreq_customer.getText(),ordreq_name.getText(),du.sti(ordreq_amount.getText()),
    			ordreq_purchase.getText(),du.sti(ordreq_price.getText()),
    			du.sti(ordreq_cost.getText()),ordreq_region.getText(),
    			Integer.toString(ordreq_state.getSelectionModel().getSelectedIndex()+1),
    			Integer.toString(ordreq_type.getSelectionModel().getSelectedIndex()+1),
    			du.asDate(ordreq_date_e.getValue()));
    	rc.SearchList(req);
    }
    
    public String ordreq_state(String state) {
    	if(state.equals("1")) {
			return "신청";
		}else if(state.equals("2")) {
			return "확인";
		}else if(state.equals("3")) {
			return "발주";
		}else if(state.equals("4")) {
			return "입고";
		}else if(state.equals("5")) {
			return "납품";
		}else if(state.equals("6")) {
			return "정산";
		}
		return null;
    }
    
    public String ordreq_type(String type) {
    	if(type.equals("1")) {
			return "납품";
		}else if(type.equals("2")) {
			return "샘플";
		}
		return null;
    }
    public void setReqController(ReqController rc) {
    	this.rc=rc;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    
    public void reqcheck_btn() {
    	subbtn2.setText("신청확인");
		subbtn2.setVisible(true);
		subbtn2.setOnAction((event) -> {
			ReqVO req=req_up;
			req.setOrdreq_id(ordreq_id);
			req.setOrdreq_state("2");
			lh.updatestate("req", target,req);
			
			JSONObject data=new JSONObject();
			data.put("type","req");
			data.put("json",gson.toJson(req));
			main.ws.send(data.toString());
			
			ReqVO req2=new ReqVO();
			rc.SearchList(req2);
			ordreq_state.setValue(ordreq_state("2"));
			ordin_btn();
		});
    }
    public void ordin_btn() {
    	subbtn2.setText("발주");
		subbtn2.setVisible(true);
		subbtn2.setOnAction((event) -> {
			roc.req_ordinpane(ordreq_id);
		});
    }
    public void req_update() {
		roc.requpdatepane(req_up);
    }
    
    public void adminbt() {
    	subbtn2.setText("삭제");
    	subbtn2.setVisible(true);
    	subbtn2.setOnAction((event) -> {
    		lh.admin_del(target,"req",req_up.getOrdreq_id(),req_up.getOrdreq_id());
		});
    }
}
