package api;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptException;

import org.json.simple.JSONObject;

import helpers.JSONUtils;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import logger.ScriptLogger;


public class RestAction {
	
	private static Response resp;

	public static void getRequest(String url) throws Exception {
		resp = get(url);
		ScriptLogger.info(resp.asString());
	}
		
	public static Response getRequestAndGetResponse(String url) throws Exception {
		resp = get(url);
		try {
			ScriptLogger.info(resp.asString());
			return resp;
		} catch (Exception e) {
			throw new ScriptException("Response wasn't caught by code.");
		}
	}
	
	public static Response getRequestAndGetResponse(String url, Headers head) throws Exception {
		RequestSpecification request = given().headers(head);
		resp = request.get(url);
		try {
			ScriptLogger.info(resp.asString());
			return resp;
		} catch (Exception e) {
			throw new ScriptException("Response wasn't caught by code.");
		}
	}
	
	public static String getValueOfKeyByKeyName(String key) throws Exception {
		String responseStr = resp.asString();
		JSONObject jo = (JSONObject) JSONUtils.toJson(responseStr);
		return (String) jo.get(key);
	}
	
	public static void putRequest(String url, String contentType, HashMap<String, String> params, String... body) throws Exception {
		if(contentType.contains("WWW_FORM")) {
			RequestSpecification rs = given().contentType(ContentTypeEnum.WWW_FORM.getValue());
			for(Map.Entry<String, String> entry : params.entrySet()) {
				rs.formParam(entry.getKey(), entry.getValue());
			}
			resp = rs.when().put(url);
		} else if (contentType.contains("JSON")) {
			resp = given().contentType(ContentType.JSON).body(body).when().put(url);
		}
		try {
			ScriptLogger.info(resp.asString());
		} catch (Exception e) {
			throw new ScriptException("Response wasn't caught by code.");
		}
	}
		
	public static Response putRequestAndGetResponse(String url, String contentType, HashMap<String, String> params, String... body) throws Exception {
		if(contentType.contains("WWW_FORM")) {
			RequestSpecification res = given().contentType(ContentTypeEnum.WWW_FORM.getValue());
			for(Map.Entry<String, String> entry : params.entrySet()) {
				res.formParam(entry.getKey(), entry.getValue());
			}
			resp = res.when().put(url);
		} else if (contentType.contains("JSON")) {
			resp = given().contentType(ContentType.JSON).body(body).when().put(url);
		}
		try {
			ScriptLogger.info(resp.asString());
			return resp;
		} catch (Exception e) {
			throw new ScriptException("Response wasn't caught by code.");
		}
	}
	
	public static Response postRequestAndGetResponseWWWFORM(String url, HashMap<String, String> params) throws Exception {
		RequestSpecification res = given().contentType(ContentTypeEnum.WWW_FORM.getValue());
		for(Map.Entry<String, String> entry : params.entrySet()) {
			res.formParam(entry.getKey(), entry.getValue());
		}
		resp = res.when().post(url);
		try {
			ScriptLogger.info(resp.asString());
			return resp;
		} catch (Exception e) {
			throw new ScriptException("Response wasn't caught by code.");
		}
	}
	
	public static Response postRequestAndGetResponseJSON(String url, String body) throws Exception {
		resp = given().contentType(ContentType.JSON).body(body).when().post(url);
		try {
			ScriptLogger.info(resp.asString());
			return resp;
		} catch (Exception e) {
			throw new ScriptException("Response wasn't caught by code.");
		}
	}
	
	public static Response postRequestAndGetResponseJSON(String url, String body, Headers head) throws Exception {
		resp = given().headers(head).contentType(ContentType.JSON).body(body).when().post(url);
		try {
			ScriptLogger.info(resp.asString());
			return resp;
		} catch (Exception e) {
			throw new ScriptException("Response wasn't caught by code.");
		}
	}
	
	public static Response putRequestAndGetResponseJSON(String url, String body, Headers head) throws Exception {
		resp = given().headers(head).contentType(ContentType.JSON).body(body).when().put(url);
		try {
			ScriptLogger.info(resp.asString());
			return resp;
		} catch (Exception e) {
			throw new ScriptException("Response wasn't caught by code.");
		}
	}
	
	public static void deleteRequest(String url) throws Exception {
		resp = delete(url);
		ScriptLogger.info(resp.asString());
	}
	
	public static void deleteRequest(String url, Headers head) throws Exception {
		RequestSpecification request = given().headers(head);
		resp = request.delete(url);
		ScriptLogger.info(resp.asString());
	}
}
