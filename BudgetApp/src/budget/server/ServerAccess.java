package budget.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import budget.Driver;

public class ServerAccess {
	
	public static boolean loginUser(String username, String password) {
		return Boolean.parseBoolean(serverRequest("login user," + username + password));
	}
	
	public static boolean newUser(String username, String password) {
		return Boolean.parseBoolean(serverRequest("new user," + username + password));
	}
	
	public static boolean pushBudget(String username, String budgetname, String dataCSV) {
		return Boolean.parseBoolean(serverRequest("push,"+ username + "," + budgetname + "," + dataCSV));
	}
	
	private static String serverRequest(String request) {//TODO: Acutal Exception Handling
		try {
			InetAddress address = InetAddress.getByName(Driver.SERVER_URL);
			Socket socket = new Socket(address, Driver.PORT);
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter oswriter = new OutputStreamWriter(os);
			BufferedWriter bwriter = new BufferedWriter(oswriter);
			
			bwriter.write(request);
			bwriter.flush();
			
			InputStream is = socket.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        
	        StringBuilder response = new StringBuilder();
	        String message;
	        while((message = br.readLine()) != null) {
	        		response.append(message);
	        }
        
        		socket.close();
        		return response.toString();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Server Error";
	}
	
	
}
