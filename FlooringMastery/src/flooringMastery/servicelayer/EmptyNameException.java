package flooringMastery.servicelayer;

public class EmptyNameException extends Exception{

	public EmptyNameException(String msg) {
		super(msg);
	}
	public EmptyNameException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
