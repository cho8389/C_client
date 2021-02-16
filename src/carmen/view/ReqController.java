package carmen.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

import carmen.Main;
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

public class ReqController {
	
	@FXML
	private TabPane tab;
	@FXML
    private TableView<ReqVO> reqtable;
	@FXML
    private TableColumn<ReqVO,Number> ordreq_idColumn;
	@FXML
    private TableColumn<ReqVO,String> emp_idColumn;
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
	
    private Main main;
    
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public ReqController() {
    }
    
    private ListHandler lh=new ListHandler();
    private DateUtils du=new DateUtils();
    private ReqPaneController rc;
    
    @FXML
    private void initialize() {
    	ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_id()));
    	emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
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
    			(observable, oldValue, newValue) -> rc.showreqDetail(newValue));
    }
    
    public void SearchList(ReqVO req) {
    	List<ReqVO> reqlist=lh.list("req", target,req);
    	reqdata.setAll(reqlist);
    }
    
    public ObservableList<ReqVO> reqdata = FXCollections.observableArrayList();
    
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
    
    public void setTblData(List data) {
    	reqdata.setAll(data);
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
    }
    public void setReqPaneController(ReqPaneController rc) {
    	this.rc=rc;
    }
    public TableView getTable() {
    	return this.reqtable;
    }
}
