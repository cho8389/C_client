package carmen.handler;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import carmen.domain.MemberVO;
import carmen.domain.RoleVO;
import carmen.utils.Convert;
import carmen.utils.ImprovedDateTypeAdapter;

public class ListHandler {
	
	private Convert con=new Convert();
	
	public List list(String type,WebTarget target,Object obj){
		target = target.path("search_list");
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Gson gson = builder.create();
	    
		String json=gson.toJson(obj);
		Form form=new Form();
		form.param("json",json);
		form.param("type",type);
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.post(Entity.form(form));
		JSONObject jo=con.stj(res.readEntity(String.class));
	    return con.jtl(type,(JSONArray) jo.get("list"));
	 
	}
	
	public void update(String type,WebTarget target,Object obj){
		target = target.path("update");
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Gson gson = builder.create();
	    
		String json=gson.toJson(obj);
		Form form=new Form();
		form.param("json",json);
		form.param("type",type);
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.post(Entity.form(form));
	}
	
	public void updatestate(String type,WebTarget target,Object obj){
		target = target.path("updatestate");
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Gson gson = builder.create();
	    
		String json=gson.toJson(obj);
		Form form=new Form();
		form.param("json",json);
		form.param("type",type);
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.post(Entity.form(form));
	}
	
	public void insert(String type,WebTarget target,Object obj){
		target = target.path("insert");
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Gson gson = builder.create();
	    
		String json=gson.toJson(obj);
		Form form=new Form();
		form.param("json",json);
		form.param("type",type);
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.post(Entity.form(form));
	}
	
	public JSONObject AllList(WebTarget target,int ordreq_id){
		target = target.path("AllList/"+ordreq_id);
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Gson gson = builder.create();
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.get();
		JSONObject jo=con.stj(res.readEntity(String.class));
		return jo;
	}
	public JSONObject wstest(WebTarget target){
		target = target.path("/echo");
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Gson gson = builder.create();
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.get();
		return null;
	}
	public List mem_list(WebTarget target){
		target = target.path("memlist");
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.get();
		JSONObject jo=con.stj(res.readEntity(String.class));
	    return con.jtl("mem",(JSONArray) jo.get("list"));
	}
	public void update_mem(WebTarget target,MemberVO mem,List<RoleVO> role) {
		target = target.path("update_mem");
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Gson gson = builder.create();
		String memjs=gson.toJson(mem);
		JSONArray roleay=new JSONArray();
		for(int i=0;i<role.size();i++) {
			roleay.add(gson.toJson(role.get(i)));
		}
		Form form=new Form();
		form.param("mem",memjs);
		form.param("role",roleay.toJSONString());
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.post(Entity.form(form));
	}
	public void insert_mem(WebTarget target,MemberVO mem,List<RoleVO> role) {
		target = target.path("insert_mem");
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Gson gson = builder.create();
		String memjs=gson.toJson(mem);
		JSONArray roleay=new JSONArray();
		for(int i=0;i<role.size();i++) {
			roleay.add(gson.toJson(role.get(i)));
		}
		Form form=new Form();
		form.param("mem",memjs);
		form.param("role",roleay.toJSONString());
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.post(Entity.form(form));
	}
	public void admin_del(WebTarget target,String type,int ordreq_id,int target_id) {
		target = target.path("admin_del/"+type+"/"+ordreq_id+"/"+target_id);
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Response res =target
	    		.request(MediaType.APPLICATION_JSON)
	    		.get();
	}
}
