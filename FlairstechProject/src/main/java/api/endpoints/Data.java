package api.endpoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {

	private static int employeeID;

	public static Map getAddUserBody() {

		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("username", "Admin"+System.currentTimeMillis());
		requestBody.put("password", "test1234");
		requestBody.put("status", true);
		requestBody.put("userRoleId", 1);
		requestBody.put("empNumber", 215);
		return requestBody;
	}
	
	public static Map getDeleteUserBody(int userID) {

		Map<String, Object> requestBody = new HashMap<String, Object>();
		ArrayList<Integer> idsArray=new ArrayList<Integer>();
		idsArray.add(userID);
		requestBody.put("ids", idsArray);
		return requestBody;
	}
}
