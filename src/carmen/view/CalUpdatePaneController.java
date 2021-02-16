package carmen.view;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.CalVO;
import carmen.domain.OrdVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CalUpdatePaneController {
	
	@FXML
	public GridPane calupdatepane;
	
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
	private Convert con=new Convert();
	private CalVO cal_up;
	private int ordreq_id;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public CalUpdatePaneController() {
    }
	
	@FXML
    private void initialize() {
    	cal_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	cal_price.setText(oldValue);
    	    }
    	});
    }
	
    public void showreqDetail(CalVO cal) {
    	cal_date.setValue(du.asLocalDate(cal.getCal_date()));
    	cal_price.setText(Integer.toString(cal.getCal_price()));
    	cal_bank.setText(cal.getCal_bank());
    	cal_name.setText(cal.getCal_name());
    	cal_state.setText(cal_state.getText());
    	cal_bill.setText(cal.getCal_bill());
    	cal_note.setText(cal.getCal_note());
    }
    
    @FXML
    public void cal_up() {
    	CalVO cal=cal_up;
    	cal.setCal_date(du.asDate(cal_date.getValue()));
    	cal.setCal_price(du.sti(cal_price.getText()));
    	cal.setCal_bank(cal_bank.getText());
    	cal.setCal_name(cal_name.getText());
    	cal.setCal_state(cal_state.getText());
    	cal.setCal_bill(cal_bill.getText());
    	cal.setCal_note(cal_note.getText());
    	lh.update("cal", target,cal);
    	roc.calview();
    }
    @FXML
    public void cal_upcan() {
    	roc.calview();
    }
    
    public void setCalController(CalController cc) {
    	this.cc=cc;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setCal(CalVO cal) {
    	this.cal_up=cal;
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
