package carmen.view;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.DelVO;
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

public class DelController {
	
	@FXML
	private TabPane tab;
	@FXML
    private TableView<DelVO> deltable;
	@FXML
    private TableColumn<DelVO,Number> del_idColumn;
    @FXML
    private TableColumn<DelVO,Number> ordreq_idColumn;
    @FXML
    private TableColumn<DelVO,String> emp_idColumn;
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
	
    private Main main;
    
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public DelController() {
    }
    
    private ListHandler lh=new ListHandler();
    private DateUtils du=new DateUtils();
    private DelPaneController dc;
    
    @FXML
    private void initialize() {
    	del_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDel_id()));
    	ordreq_idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrdreq_id()));
    	emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
    	del_stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(del_state(cellData.getValue().getDel_state())));
    	del_dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Timestamp>(new Timestamp(cellData.getValue().getDel_date().getTime())));
    	del_nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDel_name()));
    	del_customerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDel_customer()));
    	del_amountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDel_amount()));
    	del_priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDel_price()));
    	del_costColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDel_cost()));
    	del_typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDel_type()));

    	deltable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> dc.showreqDetail(newValue));
    }
    
    public void SearchList(DelVO del) {
    	List<DelVO> dellist=lh.list("del", target,del);
    	deldata.setAll(dellist);
    }
    
    public ObservableList<DelVO> deldata = FXCollections.observableArrayList();
    
    public String del_state(String state) {
    	if(state.equals("1")) {
			return "³³Ç°";
		}else if(state.equals("2")) {
			return "Á¤»ê";
		}
		return null;
    }
    
    public void setTblData(List data) {
    	deldata.setAll(data);
    }
    public ObservableList<DelVO> getDelData() {
        return deldata;
    }
    
    public void setMain(Main main) {
        this.main = main;
        deltable.setItems(deldata);
    }
    public void setDelPaneController(DelPaneController dc) {
    	this.dc=dc;
    }
    public TableView getTable() {
    	return this.deltable;
    }
    
}
