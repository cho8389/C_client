package carmen.view;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import carmen.Main;
import carmen.domain.CalVO;
import carmen.domain.MemberVO;
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

public class MemController {
	
	@FXML
	private TabPane tab;
	@FXML
    private TableView<MemberVO> memtable;
	@FXML
    private TableColumn<MemberVO,String> emp_idColumn;
    @FXML
    private TableColumn<MemberVO,String> emp_nameColumn;
    @FXML
    private TableColumn<MemberVO,String> emp_pwColumn;
    @FXML
    private TableColumn<MemberVO,String> emp_titleColumn;
    @FXML
    private TableColumn<MemberVO,String> enabledColumn;
	
    private Main main;
    
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public MemController() {
    }
    
    private ListHandler lh=new ListHandler();
    private DateUtils du=new DateUtils();
    private MemPaneController mc;
    
    @FXML
    private void initialize() {
    	emp_idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_id()));
    	emp_nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_name()));
    	emp_pwColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_pw()));
    	emp_titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmp_title()));
    	enabledColumn.setCellValueFactory(cellData -> new SimpleStringProperty(enabled(cellData.getValue().getEnabled())));

    	memtable.getSelectionModel().selectedItemProperty().addListener(
    			(observable, oldValue, newValue) -> mc.showreqDetail(newValue));
    }
    public String enabled(String state) {
    	if(state.equals("1")) {
			return "사용가능";
		}else if(state.equals("0")) {
			return "비활성화";
		}
		return null;
    }
    public void SearchList(MemberVO mem) {
    	List<MemberVO> memlist=lh.mem_list(target);
    	memdata.setAll(memlist);
    }
    
    public ObservableList<MemberVO> memdata = FXCollections.observableArrayList();
    
    public void setTblData(List data) {
    	memdata.setAll(data);
    }
    public ObservableList<MemberVO> getMemData() {
        return memdata;
    }
    
    public void setMain(Main main) {
        this.main = main;
        memtable.setItems(memdata);
    }
    public void setMemPaneController(MemPaneController mc) {
    	this.mc=mc;
    }
    
    
}
