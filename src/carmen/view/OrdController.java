package carmen.view;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.OrdVO;
import carmen.domain.ReqVO;
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

public class OrdController {
	
	@FXML
	private TabPane tab;
	@FXML
    private TableView<OrdVO> ordtable;
	@FXML
    private TableColumn<OrdVO,Number> ord_idColumn;
    @FXML
    private TableColumn<OrdVO,Number> ordreq_idColumn;
    @FXML
    private TableColumn<OrdVO,String> emp_idColumn;
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
	
    private Main main;
    
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public OrdController() {
    }
    
    private ListHandler lh=new ListHandler();
    private DateUtils du=new DateUtils();
    private OrdPaneController oc;
    
    @FXML
    private void initialize() {
    	ord_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_id()));
    	ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_id()));
    	emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
    	ord_dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Timestamp>(new Timestamp(cellData.getValue().getOrd_date().getTime())));
    	ord_amountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_amount()));
    	ord_costColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_cost()));
    	ord_priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_price()));
    	ord_indateColumn.setCellValueFactory(cellData -> du.datenull(cellData.getValue().getOrd_indate()));
    	ord_inamountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrd_inamount()));
    	ord_stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(ord_state(cellData.getValue().getOrd_state())));

    	ordtable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> oc.showreqDetail(newValue));
    }
    
    public void SearchList(OrdVO ord) {
    	List<OrdVO> ordlist=lh.list("ord", target,ord);
    	orddata.setAll(ordlist);
    }
    
    public ObservableList<OrdVO> orddata = FXCollections.observableArrayList();
    
    public String ord_state(String state) {
    	if(state.equals("1")) {
			return "발주";
		}else if(state.equals("2")) {
			return "입고";
		}
		return null;
    }
    
    public void setTblData(List data) {
    	orddata.setAll(data);
    }
    public ObservableList<OrdVO> getOrdData() {
        return orddata;
    }
    
    public void setMain(Main main) {
        this.main = main;
        ordtable.setItems(orddata);
    }
    public void setOrdPaneController(OrdPaneController oc) {
    	this.oc=oc;
    }
    public TableView getTable() {
    	return this.ordtable;
    }
    
}
