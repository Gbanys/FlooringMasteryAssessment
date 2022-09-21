package flooringMastery.servicelayer;

public class NegativeDateResultException extends Exception{
	
	public NegativeDateResultException(String message) {
		super(message);
	}
	public NegativeDateResultException(String message, Throwable cause) {
		super(message, cause);
	}

}
