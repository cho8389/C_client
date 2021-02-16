package carmen.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import carmen.Main;
import carmen.domain.CalVO;
import carmen.domain.ConfigVO;
import carmen.domain.DelVO;
import carmen.domain.MemberVO;
import carmen.domain.OrdVO;
import carmen.domain.PayVO;
import carmen.domain.ReqVO;
import carmen.handler.ListHandler;
import carmen.utils.Convert;
import carmen.utils.DateUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class RootController {
	
	@FXML
	private SplitPane split;
	@FXML
	private ScrollPane submenu;
	@FXML
	private Button membtn;
	
	private File file;
	private Main main;
    public void setMain(Main main) {
        this.main = main;
    }
    String now;
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target("");
    public void setTarget(Client client,WebTarget target) {
    	this.client=client;
    	this.target=target;
    }
    
    private Convert con=new Convert();
    private ListHandler lh=new ListHandler();
    private DateUtils du=new DateUtils();
    public ReqController reqcon;
    private ReqPaneController reqpanecon;
    private ReqUpdatePaneController requpdatepanecon;
    private req_ordinPaneController ordincon;
    public OrdController ordcon;
    private OrdPaneController ordpanecon;
    private OrdUpdatePaneController ordupdatepanecon;
    private Ord_inPaneController ord_incon;
    private Ord_PayinPaneController ord_payincon;
    public PayController paycon;
    private PayPaneController paypanecon;
    private PayUpdatePaneController payupdatepanecon;
    public DelController delcon;
    private DelPaneController delpanecon;
    private Del_CalinPaneController del_calincon;
    public CalController calcon;
    private CalPaneController calpanecon;
    private CalUpdatePaneController calupdatepanecon;
    private AllListController allcon;
    private MemController memcon;
    private MemPaneController mempanecon;
    private ObservableList tbldata = FXCollections.observableArrayList();
    
    public String type=null;
    
    @FXML
    private void initialize() {
    }
    
    @FXML
    public void reqview() {
    	reqtable();
    	reqpane();
    	reqcon.setReqPaneController(reqpanecon);
    	reqpanecon.setReqController(reqcon);
    	now="req";
    }
    @FXML
    public void ordview() {
    	ordtable();
    	ordpane();
    	ordcon.setOrdPaneController(ordpanecon);
    	ordpanecon.setOrdController(ordcon);
    	now="ord";
    }
    @FXML
    public void payview() {
    	paytable();
    	paypane();
    	paycon.setPayPaneController(paypanecon);
    	paypanecon.setPayController(paycon);
    	now="pay";
    }
    @FXML
    public void delview() {
    	deltable();
    	delpane();
    	delcon.setDelPaneController(delpanecon);
    	delpanecon.setDelController(delcon);
    	now="del";
    }
    @FXML
    public void calview() {
    	caltable();
    	calpane();
    	calcon.setCalPaneController(calpanecon);
    	calpanecon.setCalController(calcon);
    	now="cal";
    }
    @FXML
    public void memview() {
    	memtable();
    	mempane();
    	memcon.setMemPaneController(mempanecon);
    	mempanecon.setMemController(memcon);
    	now="mem";
    }
    @FXML
    private void logout_handler() {
    	ConfigVO logout=new ConfigVO(true,false);
    	main.saveConfig(logout);
    	main.ws.cancel();
    	main.loginview();
    }
    private void reqtable() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Reqview.fxml"));
	        AnchorPane reqview;
			reqview = (AnchorPane) loader.load();
			split.getItems().set(0,reqview);
	        reqcon = loader.getController();
	        reqcon.setMain(main);
	        reqcon.setTarget(client,target);
	        ReqVO req=new ReqVO();
	        reqcon.SearchList(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void requpdatepane(ReqVO req) {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/ReqUpdatePane.fxml"));
	        GridPane requpdatepane;
	        requpdatepane=(GridPane)loader.load();
	        submenu.setContent(requpdatepane);
	        requpdatepanecon=loader.getController();
	        requpdatepanecon.setMain(main);
	        requpdatepanecon.setTarget(client,target);
	        requpdatepanecon.setRootController(this);
	        requpdatepanecon.showreqDetail(req);
	        requpdatepanecon.setReq(req);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void reqpane() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/ReqPane.fxml"));
	        GridPane reqpane;
	        reqpane=(GridPane)loader.load();
	        submenu.setContent(reqpane);
	        reqpanecon=loader.getController();
	        reqpanecon.setMain(main);
	        reqpanecon.setTarget(client,target);
	        reqpanecon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void req_ordinpane(int ordreq_id) {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/req_ordinPane.fxml"));
	        GridPane ordinpane;
	        ordinpane=(GridPane)loader.load();
	        submenu.setContent(ordinpane);
	        ordincon=loader.getController();
	        ordincon.setMain(main);
	        ordincon.setTarget(client,target);
	        ordincon.setOrdreq_id(ordreq_id);
	        ordincon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void ordtable() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Ordview.fxml"));
	        AnchorPane ordview;
			ordview = (AnchorPane) loader.load();
			split.getItems().set(0,ordview);
	        ordcon = loader.getController();
	        ordcon.setMain(main);
	        ordcon.setTarget(client,target);
	        OrdVO ord=new OrdVO();
	        ordcon.SearchList(ord);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void ordpane() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/OrdPane.fxml"));
	        GridPane ordpane;
	        ordpane=(GridPane)loader.load();
	        submenu.setContent(ordpane);
	        ordpanecon=loader.getController();
	        ordpanecon.setMain(main);
	        ordpanecon.setTarget(client,target);
	        ordpanecon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void ordupdatepane(OrdVO ord) {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/OrdUpdatePane.fxml"));
	        GridPane ordupdatepane;
	        ordupdatepane=(GridPane)loader.load();
	        submenu.setContent(ordupdatepane);
	        ordupdatepanecon=loader.getController();
	        ordupdatepanecon.setMain(main);
	        ordupdatepanecon.setTarget(client,target);
	        ordupdatepanecon.setRootController(this);
	        ordupdatepanecon.showreqDetail(ord);
	        ordupdatepanecon.setOrd(ord);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void ord_inpane(OrdVO ord_in) {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Ord_inPane.fxml"));
	        GridPane ord_inpane;
	        ord_inpane=(GridPane)loader.load();
	        submenu.setContent(ord_inpane);
	        ord_incon=loader.getController();
	        ord_incon.setMain(main);
	        ord_incon.setTarget(client,target);
	        ord_incon.setOrd_in(ord_in);
	        ord_incon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void ord_payinpane(int ordreq_id) {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Ord_PayinPane.fxml"));
	        GridPane ord_payinpane;
	        ord_payinpane=(GridPane)loader.load();
	        submenu.setContent(ord_payinpane);
	        ord_payincon=loader.getController();
	        ord_payincon.setMain(main);
	        ord_payincon.setTarget(client,target);
	        ord_payincon.setOrdreq_id(ordreq_id);
	        ord_payincon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void paytable() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Payview.fxml"));
	        AnchorPane payview;
			payview = (AnchorPane) loader.load();
			split.getItems().set(0,payview);
	        paycon = loader.getController();
	        paycon.setMain(main);
	        paycon.setTarget(client,target);
	        PayVO pay=new PayVO();
	        paycon.SearchList(pay);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void paypane() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/PayPane.fxml"));
	        GridPane paypane;
	        paypane=(GridPane)loader.load();
	        submenu.setContent(paypane);
	        paypanecon=loader.getController();
	        paypanecon.setMain(main);
	        paypanecon.setTarget(client,target);
	        paypanecon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void payupdatepane(PayVO pay) {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/PayUpdatePane.fxml"));
	        GridPane ordupdatepane;
	        ordupdatepane=(GridPane)loader.load();
	        submenu.setContent(ordupdatepane);
	        payupdatepanecon=loader.getController();
	        payupdatepanecon.setMain(main);
	        payupdatepanecon.setTarget(client,target);
	        payupdatepanecon.setRootController(this);
	        payupdatepanecon.showreqDetail(pay);
	        payupdatepanecon.setPay_up(pay);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void deltable() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Delview.fxml"));
	        AnchorPane delview;
	        delview = (AnchorPane) loader.load();
			split.getItems().set(0,delview);
			delcon = loader.getController();
			delcon.setMain(main);
			delcon.setTarget(client,target);
	        DelVO del=new DelVO();
	        delcon.SearchList(del);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void delpane() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/DelPane.fxml"));
	        GridPane delpane;
	        delpane=(GridPane)loader.load();
	        submenu.setContent(delpane);
	        delpanecon=loader.getController();
	        delpanecon.setMain(main);
	        delpanecon.setTarget(client,target);
	        delpanecon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void del_calinpane(int ordreq_id,int del_id) {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Del_CalinPane.fxml"));
	        GridPane del_calinpane;
	        del_calinpane=(GridPane)loader.load();
	        submenu.setContent(del_calinpane);
	        del_calincon=loader.getController();
	        del_calincon.setMain(main);
	        del_calincon.setTarget(client,target);
	        del_calincon.setOrdreq_id(ordreq_id);
	        del_calincon.setDel_id(del_id);
	        del_calincon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void caltable() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Calview.fxml"));
	        AnchorPane calview;
	        calview = (AnchorPane) loader.load();
			split.getItems().set(0,calview);
			calcon = loader.getController();
			calcon.setMain(main);
			calcon.setTarget(client,target);
	        CalVO cal=new CalVO();
	        calcon.SearchList(cal);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void calpane() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/CalPane.fxml"));
	        GridPane calpane;
	        calpane=(GridPane)loader.load();
	        submenu.setContent(calpane);
	        calpanecon=loader.getController();
	        calpanecon.setMain(main);
	        calpanecon.setTarget(client,target);
	        calpanecon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void calupdatepane(CalVO cal) {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/CalUpdatePane.fxml"));
	        GridPane calupdatepane;
	        calupdatepane=(GridPane)loader.load();
	        submenu.setContent(calupdatepane);
	        calupdatepanecon=loader.getController();
	        calupdatepanecon.setMain(main);
	        calupdatepanecon.setTarget(client,target);
	        calupdatepanecon.setRootController(this);
	        calupdatepanecon.showreqDetail(cal);
	        calupdatepanecon.setCal(cal);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void AllList(int ordreq_id) {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/AllListView.fxml"));
	        GridPane allview;
	        AnchorPane emp=new AnchorPane();
	        allview = (GridPane) loader.load();
			split.getItems().set(0,allview);
			submenu.setContent(emp);
			allcon = loader.getController();
			allcon.setMain(main);
			allcon.setTarget(client,target);
	        allcon.SearchList(ordreq_id);
	        allcon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void memtable() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/Memview.fxml"));
	        AnchorPane memview;
	        memview = (AnchorPane) loader.load();
			split.getItems().set(0,memview);
			memcon = loader.getController();
			memcon.setMain(main);
			memcon.setTarget(client,target);
	        MemberVO mem=new MemberVO();
	        memcon.SearchList(mem);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void mempane() {
    	try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/MemPane.fxml"));
	        GridPane mempane;
	        mempane=(GridPane)loader.load();
	        submenu.setContent(mempane);
	        mempanecon=loader.getController();
	        mempanecon.setMain(main);
	        mempanecon.setTarget(client,target);
	        mempanecon.setRootController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void viewmembtn() {
    	if(con.rolecheck("admin",main.getRole())) {
    		membtn.setVisible(true);
    	};
    }
    @FXML
    public void saveExcel() throws Exception{
    	Platform.runLater(new Runnable() {
		    @Override
		    public void run(){
		    	FileChooser fileChooser = new FileChooser();
		    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("xlsx file (*.xlsx)","*.xlsx");
		    	fileChooser.getExtensionFilters().add(extFilter);
		    	file = fileChooser.showSaveDialog(main.getPrimaryStage());
		
    	
    	TableView table=new TableView();
    	if(now.equals("req")) {
    		table=reqcon.getTable();
    	}
    	if(now.equals("ord")) {
    		table=ordcon.getTable();
    	}
    	if(now.equals("pay")) {
    		table=paycon.getTable();
    	}
    	if(now.equals("del")) {
    		table=delcon.getTable();
    	}
    	if(now.equals("cal")) {
    		table=calcon.getTable();
    	}
    	Workbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet();
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        List<String> list=new ArrayList<String>();
        Convert con=new Convert();
        for(int i=0;i<table.getItems().size();i++){
        	row=sheet.createRow(i);
        	for(int j=0;j<table.getVisibleLeafColumns().size();j++) {
        		row.createCell(j).setCellValue(table.getVisibleLeafColumn(j).getText());
        	}
        }
        for(int i=1;i<table.getItems().size();i++){
        	row=sheet.createRow(i);
        	for(int j=0;j<table.getVisibleLeafColumns().size();j++) {
        		if(table.getVisibleLeafColumn(j).getCellObservableValue(i).getValue()!=null) {
        			row.createCell(j).setCellValue(table.getVisibleLeafColumn(j).getCellObservableValue(i).getValue().toString());
        		}else {
        			row.createCell(j).setCellValue("");
        		}
        	}
     }
        try {
        	if (file != null) {
                if (!file.getPath().endsWith(".xlsx")) {
                    file = new File(file.getPath() + ".xlsx");
                }
                FileOutputStream fileoutputstream = new FileOutputStream(file.getPath());
                workbook.write(fileoutputstream);
                fileoutputstream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		    }
		 		});
        
    }
}
