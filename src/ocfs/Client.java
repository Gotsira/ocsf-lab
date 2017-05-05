package ocfs;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.lloseng.ocsf.client.AbstractClient;

public class Client extends AbstractClient {
	static String msg;
	int result;
	
	public Client(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println(msg.toString());
		this.msg = msg.toString();
	}
	
	public int calculate (String msg) {
		String [] arr = this.msg.split(" ");
		int binary1 = Integer.parseInt(arr[2]);
		int binary2 = Integer.parseInt(arr[4].substring(0, arr[4].length() - 1));
		switch(arr[3]) {
		case "&":
			result = (binary1 & binary2); 
			break;
			
		case "|":
			result = (binary1 | binary2);
			break;
			
		case "^":
			result = (binary1 ^ binary2);
			break;
		}
		return result;
	}
	
	public static void main(String[] args) {
		String host = "158.108.137.253";
		int port = 5001;
		Client client = new Client(host, port);
		Scanner scan = new Scanner(System.in);
		try {
			client.openConnection();
//			client.sendToServer("Login Got");
//			TimeUnit.SECONDS.sleep(1);
//			while(client.isConnected()) {
//				client.sendToServer("" + client.calculate(msg));
//			}
			while(client.isConnected()) {
				client.sendToServer(scan.nextLine());
			}
			client.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
