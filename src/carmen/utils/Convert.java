package carmen.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Form;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import carmen.domain.CalVO;
import carmen.domain.DelVO;
import carmen.domain.MemberVO;
import carmen.domain.OrdVO;
import carmen.domain.PayVO;
import carmen.domain.ReqVO;
import carmen.domain.RoleVO;

public class Convert {
	public static Object mto(Map map, Object objClass) {
		String keyAttribute = null;
		String setMethodString = "set";
		String methodString = null;
		Iterator itr = map.keySet().iterator();
		while (itr.hasNext()) {
			keyAttribute = (String) itr.next();
			methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);
			try {
				Method[] methods = objClass.getClass().getDeclaredMethods();
				for (int i = 0; i <= methods.length - 1; i++) {
					if (methodString.equals(methods[i].getName()) && map.get(keyAttribute) != "") {
						if (methods[i].toString().contains("(java.util.Date)")) {
							methods[i].invoke(objClass,
									new SimpleDateFormat("yyyy-MM-dd").parse((String) map.get(keyAttribute)));
						} else if (methods[i].toString().contains("(int)")) {
							methods[i].invoke(objClass, Integer.parseInt((String) map.get(keyAttribute)));
						} else {
							methods[i].invoke(objClass, map.get(keyAttribute));
						}
					}
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return objClass;
	}

	public static Map otm(Object obj){
        Map map = new HashMap();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i=0; i <fields.length; i++){
            fields[i].setAccessible(true);
            try{
                map.put(fields[i].getName(), fields[i].get(obj));
            }catch(Exception e){
                e.printStackTrace();
            }
        }        
        return map;
    }
	
	public static List<String> otl(Object obj){
        List<String> list = new ArrayList<String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i=0; i <fields.length; i++){
            fields[i].setAccessible(true);
            try{
            	if(fields[i].get(obj)!=null) {
            		list.add(fields[i].get(obj).toString());
            	}else {
            		list.add("");
            	}
            }catch(Exception e){
                e.printStackTrace();
            }
        }        
        return list;
    }

	public static Form otf(Object obj){
        Form form=new Form();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i=0; i <fields.length; i++){
            fields[i].setAccessible(true);
            try{
                form.param(fields[i].getName(), (String) fields[i].get(obj));
            }catch(Exception e){
                e.printStackTrace();
            }
        }        
        return form;
    }
	
	public static JSONObject stj(String str){
		JSONParser jsonParser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json=(JSONObject) jsonParser.parse(str);
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	    return json;
    }
	
	public static List jtl(String type,JSONArray json){
		
		GsonBuilder builder = new GsonBuilder(); 
		builder.registerTypeAdapter(Date.class, new ImprovedDateTypeAdapter());
		Gson gson = builder.create();
		JSONArray empty=new JSONArray();
		
		if(type=="role") {
			List<RoleVO> list=new ArrayList<RoleVO>();
			if(json!=null) {
			for(int i=0;i<json.size();i++) {
				list.add(gson.fromJson(json.get(i).toString(),RoleVO.class));
			}
			}
			return list;
		}
		if(type=="req") {
			List<ReqVO> list=new ArrayList<ReqVO>();
			if(json!=null) {
			for(int i=0;i<json.size();i++) {
				list.add(gson.fromJson(json.get(i).toString(),ReqVO.class));
			}
			}
			return list;
		}
		if(type=="ord") {
			List<OrdVO> list=new ArrayList<OrdVO>();
			if(json!=null) {
			for(int i=0;i<json.size();i++) {
				list.add(gson.fromJson(json.get(i).toString(),OrdVO.class));
			}
			}
			return list;
		}
		if(type=="pay") {
			List<PayVO> list=new ArrayList<PayVO>();
			if(json!=null) {
			for(int i=0;i<json.size();i++) {
				list.add(gson.fromJson(json.get(i).toString(),PayVO.class));
			}
			}
			return list;
		}
		if(type=="del") {
			List<DelVO> list=new ArrayList<DelVO>();
			if(json!=null) {
			for(int i=0;i<json.size();i++) {
				list.add(gson.fromJson(json.get(i).toString(),DelVO.class));
			}
			}
			return list;
		}
		if(type=="cal") {
			List<CalVO> list=new ArrayList<CalVO>();
			if(json!=null) {
			for(int i=0;i<json.size();i++) {
				list.add(gson.fromJson(json.get(i).toString(),CalVO.class));
			}
			}
			return list;
		}
		if(type=="mem") {
			List<MemberVO> list=new ArrayList<MemberVO>();
			if(json!=null) {
			for(int i=0;i<json.size();i++) {
				list.add(gson.fromJson(json.get(i).toString(),MemberVO.class));
			}
			}
			return list;
		}
		return null;
    }
	public boolean rolecheck(String role,List<RoleVO> rl) {
		for(int i=0;i<rl.size();i++) {
			if(rl.get(i).getRole().equals(role)){
				return true;
			}
		}
		return false;
	}
}
