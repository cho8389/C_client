package carmen.utils;

import java.awt.Image;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.imageio.IIOImage;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import carmen.Main;
import carmen.domain.ReqVO;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocketListener;
import okio.ByteString;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class WebSocket extends WebSocketListener {
	private Main main;
	private Convert con=new Convert();
	private String str=null;
	public okhttp3.WebSocket createWebSocket(String url,Main main) {
	    OkHttpClient client = new OkHttpClient.Builder()
	        .readTimeout(0,  TimeUnit.MILLISECONDS)
	        .build();

	    Request request = new Request.Builder()
	        .url(url)
	        .build();
	    this.main=main;
	    return client.newWebSocket(request, this);
	  }
	@Override
	public void onClosed(okhttp3.WebSocket webSocket, int code, String reason) {
		// TODO Auto-generated method stub
		super.onClosed(webSocket, code, reason);
		System.out.println("연결끊김");
		main.setWebSocket(webSocket);
	}

	@Override
	public void onClosing(okhttp3.WebSocket webSocket, int code, String reason) {
		// TODO Auto-generated method stub
		super.onClosing(webSocket, code, reason);
		System.out.println("연결끊김");
		main.setWebSocket(webSocket);
	}

	@Override
	public void onFailure(okhttp3.WebSocket webSocket, Throwable t, Response response) {
		// TODO Auto-generated method stub
		super.onFailure(webSocket, t, response);
		System.out.println("연결끊김");
		main.setWebSocket(webSocket);
	}

	@Override
	public void onMessage(okhttp3.WebSocket webSocket, ByteString bytes) {
		// TODO Auto-generated method stub
		super.onMessage(webSocket, bytes);
	}

	@Override
	public void onMessage(okhttp3.WebSocket webSocket, String text) {
		// TODO Auto-generated method stub
		super.onMessage(webSocket, text);
		System.out.println(text);
		alertcheck(text);
	}

	@Override
	public void onOpen(okhttp3.WebSocket webSocket, Response response) {
		// TODO Auto-generated method stub
		super.onOpen(webSocket, response);
		System.out.println("연결댐");
	}
	/*public void displayTray(String data) throws Exception {
		try {
		SystemTray tray=SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit().createImage("");
		Tray.registerTrayIcon(image,"");
		Tray.displayMessage("Carmen",data, MessageType.INFO);
		tray.remove(Tray.trayIcon);
		}catch(Exception e){
			e.printStackTrace();
		}
    }*/
	public void displayAlert(String msg) throws Exception{
		String title = "Carmen";
        String message = msg;
        NotificationType notification = NotificationType.NOTICE;
        TrayNotification tray = new TrayNotification(title, message, notification);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndWait();
	}
	public void alertcheck(String text) {
		Gson gson=new Gson();
		JSONObject data=con.stj(text);
		JSONObject json=con.stj(data.get("json").toString());
		
		ReqVO req=gson.fromJson(data.get("json").toString(),ReqVO.class);
		try {
			
		if(con.rolecheck("order",main.getRole())) {
			if(req.getOrdreq_state()==null) {
				str="발주 신청이 등록되었습니다";
			}else if(req.getOrdreq_state().equals("2")&&!con.rolecheck("sales",main.getRole())) {
				str="발주 신청이 수정되었습니다";
			} 
		}
		if(con.rolecheck("account",main.getRole())) {
			if(req.getOrdreq_state()!=null) {
			if(req.getOrdreq_state().equals("3")) {
				str="발주 되었습니다";
			}
			}
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		Platform.runLater(new Runnable() {
		    @Override
		    public void run(){
		    	try {
					displayAlert(str);
				} catch (Exception e) {
					e.printStackTrace();
				}
		   }
		});
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}