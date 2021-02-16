package carmen.view;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.PayVO;
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

public class PayController {
	
	@FXML
	private TabPane tab;
	@FXML
    private TableView<PayVO> paytable;
	@FXML
    private TableColumn<PayVO,Number> pay_idColumn;
    @FXML
    private TableColumn<PayVO,Number> ordreq_idColumn;
    @FXML
    private TableColumn<PayVO,String> emp_idColumn;
    @FXML
    private TableColumn<PayVO,Date> pay_dateColumn;
    @FXML
    private TableColumn<PayVO,Number> pay_priceColumn;
	
    private Main main;
    
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public PayController() {
    }
    
    private ListHandler lh=new ListHandler();
    private DateUtils du=new DateUtils();
    private PayPaneController pc;
    
    @FXML
    private void initialize() {
    	pay_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPay_id()));
    	ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_id()));
    	emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
    	pay_dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(new Date(cellData.getValue().getPay_date().getTime())));
    	pay_priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPay_price()));
    	
    	paytable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> pc.showreqDetail(newValue));
    }
    
    public void SearchList(PayVO pay) {
    	List<PayVO> paylist=lh.list("pay", target,pay);
    	paydata.setAll(paylist);
    }
    
    public ObservableList<PayVO> paydata = FXCollections.observableArrayList();
    
    public void setTblData(List data) {
    	paydata.setAll(data);
    }
    public ObservableList<PayVO> getPayData() {
        return paydata;
    }
    
    public void setMain(Main main) {
        this.main = main;
        paytable.setItems(paydata);
    }
    public void setPayPaneController(PayPaneController pc) {
    	this.pc=pc;
    }
    public TableView getTable() {
    	return this.paytable;
    }
    
}
