package carmen.view;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.simple.JSONObject;

import carmen.Main;
import carmen.domain.ConfigVO;
import carmen.domain.ConfigWrap;
import carmen.domain.LoginRequest;
import carmen.domain.MemWrap;
import carmen.domain.MemberVO;
import carmen.handler.ListHandler;
import carmen.handler.Login;
import carmen.utils.WebSocket;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController {
	
	@FXML
	private CheckBox idsave;
	@FXML
	private CheckBox autologin;
    @FXML
    private TextField id;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginbt;

    private Main main;
    private WebSocket wsu=new WebSocket();
    public ConfigVO config=new ConfigVO(false,false);
    private MemberVO mem;
    private RootController roc;
    public void setMain(Main main) {
        this.main = main;
    }
    
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	LoginController.client=client;
    	LoginController.target=target;
    }

    @FXML
    private void initialize() {
    	loginbt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
            	Login();
            }
        });
    	config=loadConfig();
    	mem=loadLoginUser();
    	if(config.getIdsave()) {
    		id.setText(mem.getEmp_id());
        	password.setText(mem.getEmp_pw());
        	idsave.setSelected(true);
    	}
    	if(config.getAutologin()) {
    		autologin.setSelected(true);
    	}
    	autologin.setOnAction(new EventHandler<ActionEvent>() {
    		 @Override
             public void handle(ActionEvent arg0) {
    			 if(autologin.isSelected()) {
    				 idsave.setSelected(true);
    			 }else {
    				 autologin.setSelected(false);
    			 }
             }
    	});
    	idsave.setOnAction(new EventHandler<ActionEvent>() {
   		 @Override
         public void handle(ActionEvent arg0) {
			 if(!idsave.isSelected()) {
				 autologin.setSelected(false);
			 }
         }
	});
    	password.setOnKeyReleased(new EventHandler<KeyEvent> () {
    	      public void handle(KeyEvent event) {
    	            if(event.getCode().equals(KeyCode.ENTER)) {
    	            	Login();
    	            }
    	      }
    	});
    }
    public ConfigVO loadConfig() {
        try {
        	File file=new File(Main.class.getResource("").getPath()+"/config/config.xml");
        	if(!file.exists()) {
        		new File(Main.class.getResource("").getPath()+"/config").mkdir();
        		file.createNewFile();
        		ConfigVO con=new ConfigVO(false,false);
        		saveConfig(con);
        	}
            JAXBContext context = JAXBContext.newInstance(ConfigWrap.class);
            Unmarshaller um = context.createUnmarshaller();
            ConfigWrap wrapper = (ConfigWrap) um.unmarshal(file);
            return wrapper.getCon();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    public void saveConfig(ConfigVO config) {
        try {
        	File file=new File(Main.class.getResource("").getPath()+"/config/config.xml");
        	if(!file.exists()) {
        		new File(Main.class.getResource("").getPath()+"/config").mkdir();
        		file.createNewFile();
        	}
            JAXBContext context = JAXBContext.newInstance(ConfigWrap.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ConfigWrap wrapper = new ConfigWrap();
            wrapper.setCon(config);
            m.marshal(wrapper, file);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    public MemberVO loadLoginUser() {
        try {
        	File file=new File(Main.class.getResource("").getPath()+"/config/login.xml");
            JAXBContext context = JAXBContext.newInstance(MemWrap.class);
            Unmarshaller um = context.createUnmarshaller();
            MemWrap wrapper = (MemWrap) um.unmarshal(file);
            mem=wrapper.getMem();
            return mem;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    public void setRootController(RootController roc) {
    	this.roc=roc;
    }
    public void Login() {
    	LoginRequest lr=new LoginRequest(id.getText(),password.getText());
    	Login test=new Login();
    	JSONObject json=test.login(target,lr);
    	JSONObject res=(JSONObject) json.get("response");
    	if(res.get("message").equals("로그인하였습니다.")) {
    		main.setWebSocket(wsu.createWebSocket("ws://218.235.176.109:8389/echo",main));
    		main.setMem((String) res.get("userid"));
    		main.initRoot();
    		config.setIdsave(idsave.isSelected());
    		config.setAutologin(autologin.isSelected());
    		main.saveConfig(config);
    	}
    }
}
