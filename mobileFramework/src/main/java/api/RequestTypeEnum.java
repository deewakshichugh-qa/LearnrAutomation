package api;

public enum RequestTypeEnum {
	
	POST("POST"),
	PUT("PUT"),
	GET("GET"),
	DELETE("DELETE"),
	PATCH("PATCH"),
	;
	
	private String value;
	
	public String getValue() {
		return value;
	}
	private RequestTypeEnum(String value) {
		this.value=value;
	}
}
