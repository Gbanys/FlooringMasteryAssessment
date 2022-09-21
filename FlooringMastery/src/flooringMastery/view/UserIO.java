package flooringMastery.view;

public interface UserIO {

	String readString(String msg);
	
	int readInteger(String msg);
	
	double readDouble(String msg);
	
	void print(String msg);
}
