package carmen.view;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.CalVO;
import carmen.domain.OrdVO;
import carmen.handler.ListHandler;
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

public class CalController {
	
	@FXML
	private TabPane tab;
	@FXML
    private TableView<CalVO> caltable;
	@FXML
    private TableColumn<CalVO,Number> cal_idColumn;
    @FXML
    private TableColumn<CalVO,Number> ordreq_idColumn;
    @FXML
    private TableColumn<CalVO,String> emp_idColumn;
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
    public CalController() {
    }
    
    private ListHandler lh=new ListHandler();
    private DateUtils du=new DateUtils();
    private CalPaneController cc;
    
    @FXML
    private void initialize() {
    	cal_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCal_id()));
    	ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_id()));
    	emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
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
    			(observable, oldValue, newValue) -> cc.showreqDetail(newValue));
    }
    
    public void SearchList(CalVO cal) {
    	List<CalVO> callist=lh.list("cal", target,cal);
    	caldata.setAll(callist);
    }
    
    public ObservableList<CalVO> caldata = FXCollections.observableArrayList();
    
    public void setTblData(List data) {
    	caldata.setAll(data);
    }
    public ObservableList<CalVO> getCalData() {
        return caldata;
    }
    
    public void setMain(Main main) {
        this.main = main;
        caltable.setItems(caldata);
    }
    public void setCalPaneController(CalPaneController cc) {
    	this.cc=cc;
    }
    public TableView getTable() {
    	return this.caltable;
    }
    
    
}
