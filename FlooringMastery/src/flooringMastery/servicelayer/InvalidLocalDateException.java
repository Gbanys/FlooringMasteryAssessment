package flooringMastery.servicelayer;

public class InvalidLocalDateException extends Exception{

	public InvalidLocalDateException(String message) {
		super(message);
	}
	public InvalidLocalDateException(String message, Throwable cause) {
		super(message, cause);
	}
}
