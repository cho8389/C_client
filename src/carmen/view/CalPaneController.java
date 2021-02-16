package carmen.view;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.CalVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CalPaneController {
	
	@FXML
	public GridPane calpane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;
	@FXML
	private Button subbtn3;
	@FXML
	private Button subbtn4;
	
	@FXML
	private TextField cal_emp_id;
	@FXML
	private DatePicker cal_date;
	@FXML
	private DatePicker cal_date_e;
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
	private TextField cal_check;
	@FXML
	private DatePicker cal_chkdate;
	@FXML
	private TextField cal_note;
	
	private DateUtils du=new DateUtils();
	private CalController cc;
	private ListHandler lh=new ListHandler();
	private Convert con=new Convert();
	private CalVO cal_chk;
	private int ordreq_id;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public CalPaneController() {
    }
	
	@FXML
    private void initialize() {
    	cal_price.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if (!newValue.matches("\\d*")) {
    	    	cal_price.setText(oldValue);
    	    }
    	});
    	subbtn3.setOnAction((event) -> {
			roc.AllList(ordreq_id);
		});
    }
	
    public void showreqDetail(CalVO cal) {
    	cal_emp_id.setText(cal.getEmp_id());
    	cal_date.setValue(du.asLocalDate(cal.getCal_date()));
    	cal_price.setText(Integer.toString(cal.getCal_price()));
    	cal_bank.setText(cal.getCal_bank());
    	cal_name.setText(cal.getCal_name());
    	cal_state.setText(cal_state.getText());
    	cal_bill.setText(cal.getCal_bill());
    	cal_check.setText(cal.getCal_check());
    	cal_chkdate.setValue(du.asLocalDate(cal.getCal_chkdate()));
    	cal_note.setText(cal.getCal_note());
    	
    	if(cal.getCal_check()==null) {
    		if(con.rolecheck("officer",main.getRole())) {
    			subbtn2.setVisible(true);
    		}
    	}
    	if(cal.getCal_check()!=null) {
    		subbtn2.setVisible(false);
    	}
    	if(cal.getCal_check()==null) {
    		if(con.rolecheck("account",main.getRole())) {
    			ord_up();
    		}
    	}
    	if(cal.getCal_check()!=null) {
    		subbtn4.setVisible(false);
    	}
    	
    	cal_chk=cal;
    	ordreq_id=cal.getOrdreq_id();
    	cal_chk.setCal_check(main.getMem().getEmp_id());
    	cal_chk.setCal_chkdate(new Date());
    	subbtn3.setVisible(true);
    	
    	if(con.rolecheck("admin",main.getRole())) {
    		adminbt();
    	}
    }
    
    @FXML
    public void cal_search() {
    	CalVO cal=new CalVO(0,0,cal_emp_id.getText(),du.asDate(cal_date.getValue()),
    			du.sti(cal_price.getText()),cal_bank.getText(),cal_name.getText(),cal_state.getText(),
    			cal_bill.getText(),cal_check.getText(),du.asDate(cal_chkdate.getValue()),cal_note.getText(),
    			du.asDate(cal_date_e.getValue()));
    	cc.SearchList(cal);
    }
    @FXML
    public void cal_check() {
    	lh.update("cal", target,cal_chk);
    	roc.calview();
    }
    
    public void setCalController(CalController cc) {
    	this.cc=cc;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    public void ord_up() {
    	subbtn4.setVisible(true);
    	subbtn4.setOnAction((event) -> {
    		roc.calupdatepane(cal_chk);
		});
    }
    public void adminbt() {
    	subbtn4.setText("»èÁ¦");
    	subbtn4.setVisible(true);
    	subbtn4.setOnAction((event) -> {
    		lh.admin_del(target, "cal",cal_chk.getOrdreq_id(),cal_chk.getCal_id());
		});
    }
}
