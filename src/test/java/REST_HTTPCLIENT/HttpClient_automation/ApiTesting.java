package REST_HTTPCLIENT.HttpClient_automation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wbl.api_automation.base.BaseAPITest;
import com.wbl.api_automation.base.BaseApi;
import com.wbl.api_automation.helper.RestResponse;

public class ApiTesting extends BaseAPITest {
	static BaseApi api;
	static ObjectList list = new ObjectList();
	private static ApiTesting getinstance;
	private ApiTesting() {
		// TODO Auto-generated constructor stub
	}
	public static JSONObject object=null;
	public static RestResponse response=null;
	public static ApiTesting instance()
	{
		if (getinstance==null){
			 getinstance=new ApiTesting();
			 return getinstance;
		}
		else return getinstance;
	}
	
	public static String response()
	{
		//RestResponse response=null;
		//String object=null;
		if(response==null){
		response = api.get("/status?authentication=false");
		System.out.println("response created");
		return response.getPayload();
		}
		//response = api.get("/status?authentication=false");
		return response.getPayload();
	}
	
	public static JSONObject object()
	{
		//JSONObject object=null;
				if(object==null){
		object = new JSONObject(ApiTesting.response());
		System.out.println("object created");
		return object;
		}
		return object;
	}
	
	

	@BeforeClass
	private void beforeClass() {
		api = new BaseApi(endpoint);

	}

	@Test
	public void getStatusTest() {
		RestResponse response = api.get("/status?authentication=false");
		System.out.println(response.getStatuscode());
		assertEquals(response.getStatuscode(), 200);
		System.out.println(response.getStatusMessage());
		assertTrue(response.getStatusMessage().contains("HTTP/1.1 200 OK"));
		// JSONArray json = new JSONArray(response.getPayload());
		// System.out.println(json.toString());
		// System.out.println("id:"+((JSONObject)json.get(0)).get("id"));

	}

	@DataProvider(name="test")

	public static Iterator<Object[]> locales() throws InterruptedException {

	    List<Object[]> data = new ArrayList<Object[]>();
	    JSONArray keys=ApiTesting.object().names();
	    for(int i=0;i<keys.length();i++)
	    {
	    	//System.out.println(keys.get(i).toString());
	    	 data.add(new String[]{keys.get(i).toString()});
	    	 Thread.sleep(200);
	    }
	    return data.iterator();

	}

	

	@Test(dataProvider="test")
	public void country_status_y(String name) 
	{   JSONObject obj=null;
		obj=ApiTesting.object().getJSONObject(name);// pass the parameter , the parameter is object name
		 if("y".equals(obj.get("status")))
{
			 System.out.println("*******Failed country name*******");
			 System.out.println("country name--"+obj.get("name"));
			 
		 }
		Assert.assertEquals(obj.get("status"),"n");
		//Thread.sleep(2000);
}
	
	
}		

