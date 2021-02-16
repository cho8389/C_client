package carmen.view;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import carmen.Main;
import carmen.domain.MemberVO;
import carmen.domain.RoleVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MemPaneController {
	
	@FXML
	public GridPane mempane;
	
	@FXML
	private Button subbtn1;
	@FXML
	private Button subbtn2;
	
	@FXML
	private TextField emp_id;
	@FXML
	private TextField emp_name;
	@FXML
	private TextField emp_pw;
	@FXML
	private TextField emp_title;
	@FXML
	private ComboBox enabled;
	@FXML
	private CheckBox empck;
	@FXML
	private CheckBox salesck;
	@FXML
	private CheckBox accountck;
	@FXML
	private CheckBox orderck;
	@FXML
	private CheckBox officerck;
	@FXML
	private CheckBox adminck;

	
	private DateUtils du=new DateUtils();
	private MemController mc;
	private ListHandler lh=new ListHandler();
	private Convert con=new Convert();
	private MemberVO mem_up;
	private List<RoleVO> role_list;
	private RootController roc;
	
	private Main main;
	
	private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    public MemPaneController() {
    }
	
	@FXML
    private void initialize() {
		 adminck.selectedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					if(arg2) {
						salesck.setSelected(false);
						salesck.setDisable(true);
						accountck.setSelected(false);
						accountck.setDisable(true);
						orderck.setSelected(false);
						orderck.setDisable(true);
						officerck.setSelected(false);
						officerck.setDisable(true);
					}
				}
		    });

    }
	
    public void showreqDetail(MemberVO mem) {
    	emp_id.setText(mem.getEmp_id());
    	emp_name.setText(mem.getEmp_name());
    	emp_pw.setText(mem.getEmp_pw());
    	emp_title.setText(mem.getEmp_title());
    	enabled.setValue(enabled(mem.getEnabled()));
    	List<RoleVO> role=getRole(mem.getEmp_id());
    	mem_up=mem;
    	if(con.rolecheck("sales",role)) {
    		salesck.setSelected(true);
    	}if(con.rolecheck("order",role)) {
    		orderck.setSelected(true);
    	}if(con.rolecheck("account",role)) {
    		accountck.setSelected(true);
    	}if(con.rolecheck("officer",role)) {
    		officerck.setSelected(true);
    	}if(con.rolecheck("admin",role)) {
    		adminck.setSelected(true);
    	}
    }
    
    @FXML
    public void mem_up() {
    	MemberVO mem=new MemberVO(emp_id.getText(),emp_pw.getText(),emp_name.getText(),emp_title.getText(),Integer.toString(enabled.getSelectionModel().getSelectedIndex()+1));
    	List<RoleVO> role_up=new ArrayList<RoleVO>();
    	if(enabled.getSelectionModel().getSelectedIndex()==0) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"emp"));
    	}if(salesck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"sales"));
    	}if(orderck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"order"));
    	}if(accountck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"account"));
    	}if(officerck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"officer"));
    	}if(adminck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"admin"));
    	}
    	lh.update_mem(target,mem,role_up);
    }
    @FXML
    public void mem_insert() {
    	MemberVO mem=new MemberVO(emp_id.getText(),emp_pw.getText(),emp_name.getText(),emp_title.getText(),Integer.toString(enabled.getSelectionModel().getSelectedIndex()+1));
    	List<RoleVO> role_up=new ArrayList<RoleVO>();
    	if(enabled.getSelectionModel().getSelectedIndex()==0) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"emp"));
    	}if(salesck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"sales"));
    	}if(orderck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"order"));
    	}if(accountck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"account"));
    	}if(officerck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"officer"));
    	}if(adminck.isSelected()) {
    		role_up.add(new RoleVO(mem_up.getEmp_id(),"admin"));
    	}
    	lh.insert_mem(target,mem,role_up);
    }
    public String enabled(String state) {
    	if(state.equals("1")) {
			return "사용가능";
		}else if(state.equals("0")) {
			return "비활성화";
		}
		return null;
    }
    public void setMemController(MemController mc) {
    	this.mc=mc;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    public List<RoleVO> getRole(String id) {
    	Gson gson=new Gson();
    	WebTarget memtarget = client.target("http://218.235.176.109:8389/getmem/"+id);
	    String res =memtarget 
	    		.request(MediaType.APPLICATION_JSON)
	    		.get(String.class);
	    JSONObject json = Convert.stj(res);
	    return Convert.jtl("role",(JSONArray) json.get("role"));
    }
    @FXML
    private void adminbtn() {
    	if(adminck.isSelected()) {
			
    	}
    }
}
