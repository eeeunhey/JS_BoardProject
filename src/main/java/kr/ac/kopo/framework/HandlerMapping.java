package kr.ac.kopo.framework;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {

	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		Properties prop = new Properties();
		try(
			FileInputStream in = new FileInputStream("C:\\Users\\User\\git\\JS_BoardProject\\Board-WEB\\bean.properties");
		) {
			prop.load(in);
			
			Set<Object> keys = prop.keySet();
			for(Object key : keys) {
//				System.out.println(key);//주소키 값을 가져온다
				// 프로퍼티에 이름을 가져온다
				String className = prop.getProperty(key.toString());
				System.out.println(className);
				
				Class<?> clz = Class.forName(className);
				Constructor<?> constructor = clz.getConstructor();
				mappings.put(key.toString(), (Controller)constructor.newInstance());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}

	public Controller getController(String uri) {
		
		Controller control = mappings.get(uri);
		return control;
	}
}











