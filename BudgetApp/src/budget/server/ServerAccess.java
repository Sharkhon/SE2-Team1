package budget.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class ServerAccess {
	
	public static void testAccess() throws IOException, InterruptedException {
		//Process p = Runtime.getRuntime().exec("python server/main.py");
		
		String[] cmd = new String[6];
		
		if(System.getProperty("os.name").toLowerCase().startsWith("mac")) {
			cmd[0] = "/Library/Frameworks/Python.framework/Versions/3.6/bin/python3.6";
		} else {
			cmd[0] = "\"C:\\Program Files\\Python36\\python\"";
		}
		
		cmd[1] = "server/client.py";
		cmd[2] = "checkUser";
		cmd[3] = "user";
		cmd[4] = "pass";
		cmd[5] = null;
		
		/*	have args: 
		 * 		1: python file
		 * 		2: client function
		 * 		3: arg1 (username)
		 * 		4: arg2 (password/budgetName)
		 * 		5: arg3=null (filePath for writing)
		 */
		
		Runtime rt = Runtime.getRuntime();
		
		double time = System.currentTimeMillis();
		
		Process p = rt.exec(cmd);
		
		p.waitFor();//60, TimeUnit.SECONDS);
		
		time = (System.currentTimeMillis() - time)/1000;
		
		System.out.println("Time taken: " + time);
		
		String s = null;
		
		BufferedReader stdInput = new BufferedReader(new 
	            InputStreamReader(p.getInputStream()));
	
	       BufferedReader stdError = new BufferedReader(new 
	            InputStreamReader(p.getErrorStream()));
       
       System.out.println("Here is the standard output of the command:\n");
       while ((s = stdInput.readLine()) != null) {
           System.out.println(s);
       }
       
       // read any errors from the attempted command
       System.out.println("Here is the standard error of the command (if any):\n");
       while ((s = stdError.readLine()) != null) {
           System.out.println(s);
       }
		
	}
	
	public static boolean FindUser(String userName, String password) {
		
		
		
		return false;
	}
	
	
}
