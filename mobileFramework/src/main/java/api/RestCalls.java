package api;
import java.io.IOException;
import java.util.Map;

import logger.ScriptLogger;
import objects.exceptions.ScriptException;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestCalls {
	static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	static OkHttpClient CLIENT = new OkHttpClient();
	
	private static Request switchRequest(String type, Builder builder, String json) throws Exception {
		try {
				if(type.contains(RequestTypeEnum.POST.getValue())) {
					return (json !=null) ? builder.post(body(json)).build() : builder.post(RequestBody.create(null, new byte[0])).build();
				}
				else if(type.contains(RequestTypeEnum.PUT.getValue())) {
					return builder.put(body(json)).build();
				}
				else if(type.contains(RequestTypeEnum.DELETE.getValue())) {
					return (json !=null) ? builder.delete(body(json)).build() : builder.delete().build();
				}
				else if(type.contains(RequestTypeEnum.PATCH.getValue())) {
					return builder.patch(body(json)).build();
				}
				else if(type.contains(RequestTypeEnum.GET.getValue())) {
					return builder.get().build();
				} else {
					ScriptLogger.debug("Invalid request type: " +type);
				}
			} catch (Exception e) {
				throw new ScriptException("Request is not completed sucessfully.");
			}
		return null;
		}
	
	
	private static RequestBody body(String json) {
		return RequestBody.create(JSON, json);
	}

	
	public static String url(String url) {
		return url;
	}
	
	public static String url(String url, Map<String, String> queryParams) {
		okhttp3.HttpUrl.Builder  builder = HttpUrl.parse(url).newBuilder();
		for(Map.Entry<String, String> entry : queryParams.entrySet()) {
			builder.addQueryParameter(entry.getKey(), entry.getValue());
		}
		return builder.build().toString();
	}
	
	
	public static String url(String url, Map<String, String> headers , Map<String, String> queryParams) {
		okhttp3.HttpUrl.Builder  builder = HttpUrl.parse(url).newBuilder();
		for(Map.Entry<String, String> entry : headers.entrySet()) {
			builder.addQueryParameter(entry.getKey(), entry.getValue());
		}
		for(Map.Entry<String, String> entry : queryParams.entrySet()) {
			builder.addQueryParameter(entry.getKey(), entry.getValue());
		}
		
		return builder.build().toString();
	}
	
	public static Response reponse(boolean needPrint,Request request) throws Exception {
		try {
			return print(needPrint,CLIENT.newCall(request).execute());
		} catch (IOException e) {
			throw new ScriptException("Unable to execute script");
		}
	}
	
	
	public static Request request(boolean needPrint, String type, String url, String json) throws Exception {
		Builder builder = new Request.Builder().url(url);
		return print(needPrint, switchRequest(type, builder, json), json);
	}
	
	public static Request request(boolean needPrint,String type, String url, String json, Map<String, String> headers) throws Exception {
		Builder builder = new Request.Builder();
		for(Map.Entry<String, String> entry : headers.entrySet()) {
			builder.header(entry.getKey(), entry.getValue());
		}
		builder.url(url);
		return print(needPrint,switchRequest(type, builder, json), json);
	}
	
	
	private static Request print(boolean needPrint, Request request, String json) {
		if (needPrint) {
			ScriptLogger.info("Request Invoked   :: " + request);
			ScriptLogger.info("Request Body Sent :: " + json);
		}
		return request;
	}
	
	private static Response print(boolean needPrint, Response response) throws IOException {
		if (needPrint) {
			ScriptLogger.info("Response Rendered :: " + response);
			ScriptLogger.info("Response Body     :: " + response.body().string());
		}
		return response;
	}
}
