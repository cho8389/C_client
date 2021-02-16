package carmen.handler;

import java.awt.Window;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import carmen.domain.LoginRequest;
import carmen.utils.Convert;

public class Login {

	public JSONObject login(WebTarget target,LoginRequest lr){
	    target = target.path("clogin/loginRequest");
	    
	    String res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.post(Entity.form(Convert.otf(lr)),String.class);
	    JSONObject json = Convert.stj(res);
	    return json;
	}
}
