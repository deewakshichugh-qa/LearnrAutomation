package api;

public enum ContentTypeEnum {
	
	WWW_FORM("application/x-www-form-urlencoded"),
	
	;
	
	private String value;
	
	public String getValue() {
		return value;
	}
	private ContentTypeEnum(String value) {
		this.value=value;
	}
}
