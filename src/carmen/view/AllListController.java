package carmen.view;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import carmen.Main;
import carmen.domain.CalVO;
import carmen.domain.DelVO;
import carmen.domain.OrdVO;
import carmen.domain.PayVO;
import carmen.domain.ReqVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AllListController {
	
	@FXML
	private TabPane tab;
	@FXML
    private TableView<ReqVO> reqtable;
	@FXML
    private TableColumn<ReqVO,Number> ordreq_idColumn;
	@FXML
    private TableColumn<ReqVO,String> ordreq_emp_idColumn;
	@FXML
    private TableColumn<ReqVO,Timestamp> ordreq_dateColumn;
	@FXML
    private TableColumn<ReqVO,Date> ordreq_edateColumn;
	@FXML
    private TableColumn<ReqVO,String> ordreq_customerColumn;
	@FXML
    private TableColumn<ReqVO,String> ordreq_nameColumn;
	@FXML
    private TableColumn<ReqVO,String> ordreq_purchaseColumn;
	@FXML
    private TableColumn<ReqVO,Number> ordreq_amountColumn;
	@FXML
    private TableColumn<ReqVO,Number> ordreq_priceColumn;
	@FXML
    private TableColumn<ReqVO,Number> ordreq_costColumn;
	@FXML
    private TableColumn<ReqVO,String> ordreq_regionColumn;
	@FXML
    private TableColumn<ReqVO,String> ordreq_stateColumn;
	@FXML
    private TableColumn<ReqVO,String> ordreq_typeColumn;
	@FXML
    private TableColumn<ReqVO,Number> ordreq_fullcostColumn;
	
	@FXML
    private TableView<OrdVO> ordtable;
	@FXML
    private TableColumn<OrdVO,Number> ord_idColumn;
    @FXML
    private TableColumn<OrdVO,Number> ord_ordreq_idColumn;
    @FXML
    private TableColumn<OrdVO,String> ord_emp_idColumn;
    @FXML
    private TableColumn<OrdVO,Timestamp> ord_dateColumn;
    @FXML
    private TableColumn<OrdVO,Number> ord_amountColumn;
    @FXML
    private TableColumn<OrdVO,Number> ord_costColumn;
    @FXML
    private TableColumn<OrdVO,Number> ord_priceColumn;
    @FXML
    private TableColumn<OrdVO,Date> ord_indateColumn;
    @FXML
    private TableColumn<OrdVO,Number> ord_inamountColumn;
    @FXML
    private TableColumn<OrdVO,String> ord_stateColumn;
    
    @FXML
    private TableView<PayVO> paytable;
	@FXML
    private TableColumn<PayVO,Number> pay_idColumn;
    @FXML
    private TableColumn<PayVO,Number> pay_ordreq_idColumn;
    @FXML
    private TableColumn<PayVO,String> pay_emp_idColumn;
    @FXML
    private TableColumn<PayVO,Date> pay_dateColumn;
    @FXML
    private TableColumn<PayVO,Number> pay_priceColumn;
    
    @FXML
    private TableView<DelVO> deltable;
	@FXML
    private TableColumn<DelVO,Number> del_idColumn;
    @FXML
    private TableColumn<DelVO,Number> del_ordreq_idColumn;
    @FXML
    private TableColumn<DelVO,String> del_emp_idColumn;
    @FXML
    private TableColumn<DelVO,String> del_stateColumn;
    @FXML
    private TableColumn<DelVO,Timestamp> del_dateColumn;
    @FXML
    private TableColumn<DelVO,String> del_nameColumn;
    @FXML
    private TableColumn<DelVO,String> del_customerColumn;
    @FXML
    private TableColumn<DelVO,Number> del_amountColumn;
    @FXML
    private TableColumn<DelVO,Number> del_costColumn;
    @FXML
    private TableColumn<DelVO,Number> del_priceColumn;
    @FXML
    private TableColumn<DelVO,String> del_typeColumn;
    
    @FXML
    private TableView<CalVO> caltable;
	@FXML
    private TableColumn<CalVO,Number> cal_idColumn;
    @FXML
    private TableColumn<CalVO,Number> cal_ordreq_idColumn;
    @FXML
    private TableColumn<CalVO,String> cal_emp_idColumn;
    @FXML
    private TableColumn<CalVO,Date> cal_dateColumn;
    @FXML
    private TableColumn<CalVO,Number> cal_priceColumn;
    @FXML
    private TableColumn<CalVO,String> cal_bankColumn;
    @FXML
    private TableColumn<CalVO,String> cal_nameColumn;
    @FXML
    private TableColumn<CalVO,String> cal_stateColumn;
    @FXML
    private TableColumn<CalVO,String> cal_billColumn;
    @FXML
    private TableColumn<CalVO,String> cal_checkColumn;
    @FXML
    private TableColumn<CalVO,Timestamp> cal_chkdateColumn;
    @FXML
    private TableColumn<CalVO,String> cal_noteColumn;
	
    private Main main;
    
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public AllListController() {
    }
    
    private ListHandler lh=new ListHandler();
    private DateUtils du=new DateUtils();
    private Convert con=new Convert();
    private RootController roc;
    
    @FXML
    private void initialize() {
    	reqtable();
    	ordtable();
    	paytable();
    	deltable();
    	caltable();
    }
    
    private void reqtable() {
    	ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_id()));
    	ordreq_emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
    	ordreq_dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Timestamp>(new Timestamp(cellData.getValue().getOrdreq_date().getTime())));
    	ordreq_edateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(new Date(cellData.getValue().getOrdreq_edate().getTime())));
    	ordreq_customerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrdreq_customer()));
    	ordreq_nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrdreq_name()));
    	ordreq_amountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_amount()));
    	ordreq_purchaseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrdreq_purchase()));
    	ordreq_priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_price()));
    	ordreq_costColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_cost()));
    	ordreq_regionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrdreq_region()));
    	ordreq_stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(ordreq_state(cellData.getValue().getOrdreq_state())));
    	ordreq_typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(ordreq_type(cellData.getValue().getOrdreq_type())));
    	ordreq_fullcostColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(ordreq_fullcost(cellData.getValue())));
    	
    	reqtable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> select("req",newValue));
    }
    
    private void ordtable() {
    	ord_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_id()));
    	ord_ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_id()));
    	ord_emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
    	ord_dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Timestamp>(new Timestamp(cellData.getValue().getOrd_date().getTime())));
    	ord_amountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_amount()));
    	ord_costColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_cost()));
    	ord_priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_price()));
    	ord_indateColumn.setCellValueFactory(cellData -> du.datenull(cellData.getValue().getOrd_indate()));
    	ord_inamountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_inamount()));
    	ord_stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(ord_state(cellData.getValue().getOrd_state())));

    	ordtable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> select("ord",newValue));
    }
    
    private void paytable() {
    	pay_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPay_id()));
    	pay_ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_id()));
    	pay_emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
    	pay_dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(new Date(cellData.getValue().getPay_date().getTime())));
    	pay_priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPay_price()));
    	
    	paytable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> select("pay",newValue));
    }
    
    private void deltable() {
    	del_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDel_id()));
    	del_ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_id()));
    	del_emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
    	del_stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(del_state(cellData.getValue().getDel_state())));
    	del_dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Timestamp>(new Timestamp(cellData.getValue().getDel_date().getTime())));
    	del_nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDel_name()));
    	del_customerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDel_customer()));
    	del_amountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDel_amount()));
    	del_priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDel_price()));
    	del_costColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDel_cost()));
    	del_typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDel_type()));

    	deltable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> select("del",newValue));
    }
    
    private void caltable() {
    	cal_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCal_id()));
    	cal_ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_id()));
    	cal_emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
    	cal_dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(new Date(cellData.getValue().getCal_date().getTime())));
    	cal_priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCal_price()));
    	cal_bankColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCal_bank()));
    	cal_nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCal_name()));
    	cal_stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCal_state()));
    	cal_billColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCal_bill()));
    	cal_checkColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCal_check()));
    	cal_chkdateColumn.setCellValueFactory(cellData -> du.timenull(cellData.getValue().getCal_chkdate()));
    	cal_noteColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCal_note()));
    	
    	caltable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> select("cal",newValue));
    }
    
    public void SearchList(int ordreq_id) {
    	JSONObject json=lh.AllList(target, ordreq_id);
    	JSONArray ra=new JSONArray();
    	ra.add(json.get("req"));
    	reqdata.setAll(con.jtl("req",ra));
    	orddata.setAll(con.jtl("ord",(JSONArray) json.get("ord")));
    	paydata.setAll(con.jtl("pay",(JSONArray) json.get("pay")));
    	deldata.setAll(con.jtl("del",(JSONArray) json.get("del")));
    	caldata.setAll(con.jtl("cal",(JSONArray) json.get("cal")));
    }
    
    public ObservableList<ReqVO> reqdata = FXCollections.observableArrayList();
    public ObservableList<OrdVO> orddata = FXCollections.observableArrayList();
    public ObservableList<PayVO> paydata = FXCollections.observableArrayList();
    public ObservableList<DelVO> deldata = FXCollections.observableArrayList();
    public ObservableList<CalVO> caldata = FXCollections.observableArrayList();
    
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
    public String del_state(String state) {
    	if(state.equals("1")) {
			return "납품";
		}else if(state.equals("2")) {
			return "정산";
		}
		return null;
    }
    public String ord_state(String state) {
    	if(state.equals("1")) {
			return "발주";
		}else if(state.equals("2")) {
			return "입고";
		}
		return null;
    }
    
    public ObservableList<ReqVO> getReqData() {
        return reqdata;
    }
    
    public int ordreq_fullcost(ReqVO req) {
    	return (int) (req.getOrdreq_cost()*req.getOrdreq_amount()*1.1);
    }
    
    public void setMain(Main main) {
        this.main = main;
        reqtable.setItems(reqdata);
        ordtable.setItems(orddata);
        paytable.setItems(paydata);
        deltable.setItems(deldata);
        caltable.setItems(caldata);
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void select(String type,Object obj) {
    	if(type.equals("req")) {
    		roc.reqview();
    		roc.reqcon.SearchList((ReqVO) obj);
    	}
    	if(type.equals("ord")) {
    		roc.ordview();
    		roc.ordcon.SearchList((OrdVO) obj);
    	}
    	if(type.equals("pay")) {
    		roc.payview();
    		roc.paycon.SearchList((PayVO) obj);
    	}
    	if(type.equals("del")) {
    		roc.delview();
    		roc.delcon.SearchList((DelVO) obj);
    	}
    	if(type.equals("cal")) {
    		roc.calview();
    		roc.calcon.SearchList((CalVO) obj);
    	}
    }
}
