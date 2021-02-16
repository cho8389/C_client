package carmen;
	
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import carmen.domain.ConfigVO;
import carmen.domain.ConfigWrap;
import carmen.domain.MemWrap;
import carmen.domain.MemberVO;
import carmen.domain.ReqVO;
import carmen.domain.RoleVO;
import carmen.utils.Convert;
import carmen.view.LoginController;
import carmen.view.ReqController;
import carmen.view.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import okhttp3.WebSocket;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class Main extends Application {
	private Stage primaryStage;
    private BorderPane rootLayout;
    public RootController roc;
    public WebSocket ws;
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("http://218.235.176.109:8389");
    private static MemberVO mem=new MemberVO();
    private static List<RoleVO> role=new ArrayList<RoleVO>();
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Carmen");
        this.primaryStage.getIcons().add(new Image("file://"+Main.class.getResource("").getPath()+"../../resources/images/icon.png"));
        loginview();
    }

    public void loginview() {	
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Login.fxml"));
            AnchorPane loginview = (AnchorPane) loader.load();
            Scene scene = new Scene(loginview);
            primaryStage.setScene(scene);
            primaryStage.show();
            LoginController controller = loader.getController();
            controller.setMain(this);
            controller.setTarget(client,target);
            if(controller.config.getAutologin()) {
            	controller.Login();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    
    public void initRoot() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            rootLayout.autosize();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            RootController roc = loader.getController();
            roc.setMain(this);
            roc.setTarget(client,target);
            roc.viewmembtn();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainview() {	
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Main.fxml"));
            AnchorPane mainview = (AnchorPane) loader.load();
            rootLayout.setCenter(mainview);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void reqview() {	
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Reqview.fxml"));
	        AnchorPane reqview;
			reqview = (AnchorPane) loader.load();
			
			reqview.autosize();
	        rootLayout.setCenter(reqview);

	        ReqController controller = loader.getController();
	        controller.setMain(this);
	        controller.setTarget(client,target);
	        ReqVO req=new ReqVO();
	        controller.SearchList(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Main() {
    }
    
    public void setMem(String id) {
    	Gson gson=new Gson();
    	WebTarget memtarget = client.target("http://218.235.176.109:8389/getmem/"+id);
	    String res =memtarget 
	    		.request(MediaType.APPLICATION_JSON)
	    		.get(String.class);
	    JSONObject json = Convert.stj(res);
	    
	    mem=gson.fromJson(json.get("mem").toString(), MemberVO.class);
	    saveLoginUser();
	    role=Convert.jtl("role",(JSONArray) json.get("role"));
    }
    public MemberVO getMem() {
    	return this.mem;
    }
    public List<RoleVO> getRole(){
    	return this.role;
    }
    public void setWebSocket(WebSocket ws) {
    	this.ws=ws;
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
    public void saveLoginUser() {
        try {
        	File file=new File(Main.class.getResource("").getPath()+"/config/login.xml");
        	if(!file.exists()) {
        		new File(Main.class.getResource("").getPath()+"/config").mkdir();
        		file.createNewFile();
        	}
            JAXBContext context = JAXBContext.newInstance(MemWrap.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            MemWrap wrapper = new MemWrap();
            wrapper.setMem(mem);
            m.marshal(wrapper, file);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    public void deleteLoginUser() {
        try {
        	File file=new File(Main.class.getResource("").getPath()+"/config/login.xml");
            JAXBContext context = JAXBContext.newInstance(ConfigWrap.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            MemWrap wrapper = new MemWrap();
            MemberVO mem=new MemberVO();
            wrapper.setMem(mem);
            m.marshal(wrapper, file);
        } catch (Exception e) {
        	e.printStackTrace();
        }
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
    public void displayAlert(String msg) throws Exception{
		String title = "Carmen";
        String message = msg;
        NotificationType notification = NotificationType.NOTICE;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndWait();
	}
    
    
    
}
