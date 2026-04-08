package util;

import java.io.File;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {

	public APIResponse ap;

	public APIResponse postCall(String strBaseUrl, String endPoint, Object postData, String strHeader) {
		try {
			RestAssured.baseURI = strBaseUrl;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			if (strHeader != null) {
				request.header("Authorization", strHeader);
			}
			request.body(postData.toString());
			request.log().all();
			Response response = request.post(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	public APIResponse postCall(String strBaseUrl, String endPoint, Object postData, Headers headers) {
		try {
			RestAssured.baseURI = strBaseUrl;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			if (headers != null) {
				request.headers(headers);
			}
			request.body(postData.toString());
			request.log().all();
			Response response = request.post(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	// Function to hit the Get API
	public APIResponse getCall(String strBaseUrl, String endPoint, String strHeader) {
		try {
			RestAssured.baseURI = strBaseUrl;
			System.out.println("base" + strBaseUrl);
			RequestSpecification request = RestAssured.given();
			if (strHeader != null) {
				request.header("Authorization", strHeader);
			}
			request.log().all();
			Response response = request.get(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	// Function to hit the PUT API to make some changes
	public APIResponse putCall(String baseUri, String endPoint, Object postData, String strHeader) {
		try {
			RestAssured.baseURI = baseUri;
			RequestSpecification request = RestAssured.given();
			if (strHeader != null) {
				request.header("Authorization", strHeader);
				request.header("Content-Type", "application/json");
			}
			request.log().all();
			request.body(postData.toString());
			Response response = request.put(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	public APIResponse patchCall(String strBaseUrl, String endPoint, Object postData, String strHeader) {
		try {
			RestAssured.baseURI = strBaseUrl;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			if (strHeader != null) {
				request.header("Authorization", strHeader);
			}
			request.body(postData.toString());
			request.log().all();
			Response response = request.patch(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	// Function to hit the PUT API to make some changes
	public APIResponse putCallJWT(String baseUri, String endPoint, Object postData, String strHeader) {
		try {
			RestAssured.baseURI = baseUri;
			RequestSpecification request = RestAssured.given();
			if (strHeader != null) {
				request.header("Authorization", strHeader);
				request.header("Content-Type", "application/json");
			}
			request.log().all();
			request.body(postData.toString());
			Response response = request.put(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	public APIResponse postCallWithBasicAuth(String baseUri, String endPoint, Object postData, String strHeader,
			String strUserName, String strPassword) {
		try {
			RestAssured.baseURI = baseUri;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			if (strHeader != null) {
				request.header("Authorization", strHeader);
			}
			request.auth().basic(strUserName, strPassword);
			request.body(postData.toString());
			request.log().all();
			Response response = request.post(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	// Function to hit the Get API
	public APIResponse getCall(String strBaseUrl, String endPoint, Headers headers) {
		try {
			RestAssured.baseURI = strBaseUrl;
			System.out.println("base" + strBaseUrl);
			RequestSpecification request = RestAssured.given();
			if (headers != null) {
				request.headers(headers);
			}
			request.log().all();
			Response response = request.get(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	public APIResponse postCallMultipleHeaderFormData(String baseUri, String endPoint, Map<String, String> formData,
			Headers headers, String strFileName) {
		try {
			RestAssured.baseURI = baseUri;
			RequestSpecification request = RestAssured.given();
			// request.header("Content-Type", "multipart/form-data");
			if (headers != null) {
				request.headers(headers);
			}
			request.multiPart(new File("src/main/resources/"+strFileName));
			request.log().all();
			request.formParams(formData);
			Response response = request.post(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	public APIResponse postCallMultipleHeader(String baseUri, String endPoint, Object postData, Headers headers) {
		try {
			RestAssured.baseURI = baseUri;
			RequestSpecification request = RestAssured.given();
			if (headers != null) {
				request.headers(headers);
			}
			request.log().all();
			request.body(postData.toString());
			Response response = request.post(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}

	// Function to hit the Get API
	public APIResponse getCallwithBasicAuth(String strBaseUrl, String endPoint, String strUserName, String strPassword,
			String strHeader, String jwtToken) {
		try {
			RestAssured.baseURI = strBaseUrl;
			System.out.println("base" + strBaseUrl);
			RequestSpecification request = RestAssured.given();
			if (strHeader != null) {
				request.header("Authorization", strHeader);
				request.header("x-jwt-token", jwtToken);
			}
			request.auth().basic(strUserName, strPassword);
			request.log().all();
			Response response = request.get(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}
	public APIResponse putCall(String strBaseUrl, String endPoint, Object postData, Headers headers) {
		try {
			RestAssured.baseURI = strBaseUrl;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			if (headers != null) {
				request.headers(headers);
			}
			request.body(postData.toString());
			request.log().all();
			Response response = request.put(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}
	
	public APIResponse getCallwithfile(String strBaseUrl, String endPoint, String filepath, Headers headers) {
		try {
			File file = new File(filepath);
			RestAssured.baseURI = strBaseUrl;
			System.out.println("base" + strBaseUrl);
			RequestSpecification request = RestAssured.given();
			request.multiPart(file);
			if (headers != null) {
				request.headers(headers);
			}
			request.log().all();
			Response response = request.post(endPoint);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}
	
	public APIResponse putwithfile(String strBaseUrl, String endPoint, String filepath, Headers headers) {
		try {
			File file = new File(filepath);
			RestAssured.baseURI = strBaseUrl;
			System.out.println("base" + strBaseUrl);
			RequestSpecification request = RestAssured.given().urlEncodingEnabled(false);
			request.multiPart(file);
			if (headers != null) {
				request.headers(headers);
			}
			request.log().all();
			Response response = request.put(strBaseUrl);
			response.then().log().all();
			ap = new APIResponse(response);
		} catch (Exception e) {
			System.out.println("Error occurred during API call: " + e.getMessage());
		}
		return ap;
	}
}