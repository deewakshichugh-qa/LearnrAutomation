package helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

import logger.ScriptLogger;
import objects.exceptions.ApplicationException;
import objects.exceptions.ScriptException;

public class JSONUtils {
	@SuppressWarnings("unchecked")
	public static JSONArray getLessJSONObjects(JSONArray largeArray, int objectCount) {
		JSONArray array = new JSONArray();
		for(int i =0; i < objectCount; i++) {
			array.add(largeArray.get(i));
		}
		return array;
	}


	/**
	 * Returns Keyname of a Key in a jsonObject.
	 * payload is a String form of JSON
	 * @author Raman Ojha
	 * @version 
	 * @since 2019-Oct-9 
	 * @updatedOn 
	 */
	public static String getKeyName(String payload, int index) throws Exception{	
		return (String) getKeyList(payload).get(index);		
	}

	/**
	 * Returns ArrayList of Keys for a certain jsonobject.
	 * @author Raman Ojha
	 * @version 
	 * @since 2019-Oct-9 
	 * @updatedOn 
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getKeyList(String payload) throws Exception{	

		if(payload.startsWith("{") && payload.endsWith("}")) {
			JSONObject jsonObject = (JSONObject)toJson(payload);
			Set<T> set = new HashSet<T>(jsonObject.keySet());
			return new ArrayList<T>(set);
		} 
		else{
			System.out.println("Payload is not JSONObject"+payload);
			return null;
		}	
	}

	/**
	 * return a response into pojo format.
	 * @author Raman Ojha
	 * @version 
	 * @since 2019-Oct-9 
	 * @updatedOn 
	 */
	public static <T> T toPojo(T json, Class<T> clazz) throws Exception{
		Gson gson = new Gson();
		return gson.fromJson(json.toString(), clazz);	
	}

	/**
	 * return a jsonString into object form which can be casted in JSONArray on JSONOBject.
	 * @author Raman Ojha
	 * @version 
	 * @since 2019-Oct-9 
	 * @updatedOn 
	 */
	public static Object toJson(String jsonString) throws Exception {
		try {
			return (Object) (new JSONParser()).parse(jsonString);
		} catch (Exception e) {
			throw new ScriptException("JSON Parser Exeception,  JSON is not parsed");
		}
	}


	/**
	 * return a jsonString into object based on Key or Index which can be casted in JSONArray on JSONOBject.
	 * 
	 * @author Raman Ojha
	 * @version 
	 * @since 2019-Oct-9 
	 * @updatedOn 
	 */
	public static Object getObjectByKey(String jsonString, String keyOrIndex) throws Exception {
		Object object = null;
		if(jsonString.startsWith("{") && jsonString.endsWith("}")) 
			object = (JSONObject) toJson(jsonString);	
		else if(jsonString.startsWith("[") && jsonString.endsWith("]"))
			object = (JSONArray) toJson(jsonString);
		return 	getObjectByKey(object, keyOrIndex);		
	}

	/**
	 * return a object based on Key or Index which can be casted in JSONArray on JSONOBject.
	 * 
	 * @author Raman Ojha
	 * @version 
	 * @since 2019-Oct-9 
	 * @updatedOn 
	 */
	public static Object getObjectByKey(Object object, String keyOrIndex) throws Exception {
		String objectString = object.toString();
		if(objectString.startsWith("{")) {
			JSONObject jsonobject = (JSONObject) toJson(objectString);
			Object value = jsonobject.get(keyOrIndex);

			if( value.toString().startsWith("{"))
				return (JSONObject)value;
			else if(value.toString().startsWith("["))
				return (JSONArray)value;
			else
				return (Object)value;
		}
		else if(objectString.startsWith("[")) {
			JSONArray jsonArray = (JSONArray) toJson(objectString);
			Object value = jsonArray.get(Integer.parseInt(keyOrIndex));

			if(value.toString().startsWith("{"))
				return (JSONObject)value;
			else if(value.toString().startsWith("["))
				return (JSONArray)value;
			else
				return (Object)value;
		}
		else {
			throw new Exception("Object is not a json parsable");
		}			
	}


	/**
	 * return a object based on path.
	 * Ex. object is JsonArray then 0/key1/key2/2/ where 0,2 is index of JsonObject
	 * Ex. object is JsonObject then key1/key2/0/key3 where 0 is index.
	 * @author Raman Ojha
	 * @version 
	 * @since 2019-Oct-9 
	 * @updatedOn 
	 */
	public static Object getObjectByPath(Object object, String jsonPath) throws Exception {
		String[] nodes = jsonPath.split("/");
		for(String node : nodes) {
			try{object = getObjectByKey(object, node);}
			catch(NullPointerException e){
				ScriptLogger.info(object.toString());
				throw new ApplicationException("\n\nIt can be script or Application exception : Node ["+node+"] not found !!!\n\n\n");
			}
		}
		return object;
	}
}
