package objects.exceptions;

public class ResolveLocatorException extends FrameworkException {
	
	private static final long serialVersionUID = -120452278927884928L;

	public ResolveLocatorException(String message) {
		super(message);
	}
	
	public ResolveLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
