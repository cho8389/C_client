package carmen.view;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.CalVO;
import carmen.domain.DelVO;
import carmen.domain.ReqVO;
import carmen.handler.ListHandler;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Del_CalinPaneController {
	
	@FXML
	public GridPane del_calinpane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;
	
	@FXML
	private DatePicker cal_date;
	@FXML
	private TextField cal_price;
	@FXML
	private TextField cal_bank;
	@FXML
	private TextField cal_name;
	@FXML
	private TextField cal_state;
	@FXML
	private TextField cal_bill;
	@FXML
	private TextField cal_note;
	
	private DateUtils du=new DateUtils();
	private CalController cc;
	private ListHandler lh=new ListHandler();
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
    public Del_CalinPaneController() {
    }
	
	@FXML
    private void initialize() {
    	cal_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	cal_price.setText(oldValue);
    	    }
    	});
    }
	@FXML
	public void cal_in() {
		CalVO cal=new CalVO();
		cal.setOrdreq_id(ordreq_id);
		cal.setEmp_id(main.getMem().getEmp_id());
		cal.setCal_date(du.asDate(cal_date.getValue()));
		cal.setCal_price(du.sti(cal_price.getText()));
		cal.setCal_bank(cal_bank.getText());
		cal.setCal_name(cal_name.getText());
		cal.setCal_state(cal_state.getText());
		cal.setCal_bill(cal_bill.getText());
		cal.setCal_note(cal_note.getText());
    	lh.insert("cal", target,cal);
    	ReqVO req=new ReqVO();
    	req.setOrdreq_id(ordreq_id);
    	req.setOrdreq_state("6");
    	lh.updatestate("req", target, req);
    	DelVO del=new DelVO();
    	del.setDel_id(del_id);
    	del.setDel_state("2");
    	lh.updatestate("del", target, del);
    	roc.calview();
	}
	@FXML
	public void cal_incan() {
		roc.delview();
	}

    public void setCalController(CalController cc) {
    	this.cc=cc;
    }
    public void setOrdreq_id(int ordreq_id) {
    	this.ordreq_id=ordreq_id;
    }
    public void setDel_id(int del_id) {
    	this.del_id=del_id;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
