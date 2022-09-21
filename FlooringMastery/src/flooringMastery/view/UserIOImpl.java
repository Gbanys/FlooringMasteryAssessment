package flooringMastery.view;

import java.util.Scanner;

public class UserIOImpl implements UserIO{

	public String readString(String msg) {
		
		print(msg);
		Scanner myScanner = new Scanner(System.in);
		String userInput = myScanner.nextLine();
		return userInput;
	}
	public int readInteger(String msg) {
		
		print(msg);
		Scanner myScanner = new Scanner(System.in);
		int userInput = myScanner.nextInt();
		return userInput;
	}
	
	public double readDouble(String msg) {
		
		print(msg);
		Scanner myScanner = new Scanner(System.in);
		double userInput = myScanner.nextDouble();
		return userInput;
	}
	
	public void print(String msg) {
		System.out.println(msg);
	}
}
