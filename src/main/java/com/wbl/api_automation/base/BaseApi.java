package com.wbl.api_automation.base;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import com.wbl.api_automation.helper.ConfigUtils;
import com.wbl.api_automation.helper.RestResponse;

public class BaseApi {
	
	private String url;
	RestResponse restResponse;
	HttpClient httpClient;
	public BaseApi(String url)
	{
		 this.url=url;
		 httpClient=HttpClientBuilder.create().build();
		 setAuthenntication();
	}
	public void setAuthenntication()
	{
				Properties prop=ConfigUtils.loadproperties("config.properties");
		}	
	public RestResponse get(String resouce)
	{
		HttpGet get =new  HttpGet(url+resouce);
		restResponse =new RestResponse();		
		try {			
		HttpResponse response = httpClient.execute(get);
			//restResponse.setStatuscode(response.getStatusLine().getStatusCode());
			restResponse.setStatuscode(response.getStatusLine().getStatusCode());
	     	restResponse.setStatusMessage((response.getStatusLine().toString()));
		    restResponse.setPayload((IOUtils.toString(response.getEntity().getContent())));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return restResponse;				
	}
	
	
	public RestResponse post(String resource){
		  
		  //create required httpmethod object
		  HttpPost post = new HttpPost(url+resource); 
		  restResponse= new RestResponse();
		  try {
		   
		   post.setHeader("Content-Type","application/json");
		  // consumer.sign(post);
		   /*List<NameValuePair> entityList = new ArrayList <NameValuePair>();
		   entityList.add(new BasicNameValuePair("lang", "fr"));
		   HttpEntity entity = new UrlEncodedFormEntity(entityList);*/
		   
		   
		   HttpEntity entity = new StringEntity(createRequestPayload());
		   post.setEntity(entity);
		   
		   HttpResponse response = httpClient.execute(post);
		   restResponse.setStatuscode(response.getStatusLine().getStatusCode());
		   restResponse.setHeaders(response.getAllHeaders());
		   restResponse.setStatusMessage(response.getStatusLine().toString());
		   restResponse.setPayload(IOUtils.toString(response.getEntity().getContent()));
		   
		  } catch (ClientProtocolException e) {
		   e.printStackTrace();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return restResponse;
		 }

	private String createRequestPayload(){
String reqPayload = "{\"name\": \"APIiiiiiiipppi\",\"icon_class\": \"ts-seleniumwebdriver\", \"description\": \"SWD\"}";
/*
		  JSONObject json = new JSONObject();
		  json.put("name", "API1NewVersion1");
		  json.put("icon_class", "ts-seleniumwebdriver");
		  json.put("description", "SWd");
		  
		  return json.toString();*/
		  return reqPayload;
		 }

	public RestResponse update(String resource,String requestparam){
		  
		  //create required httpmethod object
		  HttpPut put = new HttpPut(url+resource+"/"+requestparam); 
		  restResponse= new RestResponse();
		  try {
		   
		   put.setHeader("Content-Type","application/json");
		  // consumer.sign(post);
		  
		   
		   HttpEntity entity = new StringEntity(createRequestPayload());
		   put.setEntity(entity);
		   
		   HttpResponse response = httpClient.execute(put);
		   restResponse.setStatuscode(response.getStatusLine().getStatusCode());
		   restResponse.setHeaders(response.getAllHeaders());
		   restResponse.setStatusMessage(response.getStatusLine().toString());
		   restResponse.setPayload(IOUtils.toString(response.getEntity().getContent()));
		   
		  } catch (ClientProtocolException e) {
		   e.printStackTrace();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return restResponse;
		 }
	
	
	public RestResponse delete(String resource,String requestparam){
		  
		  //create required httpmethod object
		  HttpDelete delete = new HttpDelete(url+resource+"/"+requestparam); 
		  restResponse= new RestResponse();
		  try {   
		   HttpResponse response = httpClient.execute(delete);
		   restResponse.setStatuscode(response.getStatusLine().getStatusCode());
		   restResponse.setHeaders(response.getAllHeaders());
		   restResponse.setStatusMessage(response.getStatusLine().toString());
		   restResponse.setPayload(IOUtils.toString(response.getEntity().getContent()));
		   
		  } catch (ClientProtocolException e) {
		   e.printStackTrace();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return restResponse;
		 }				 
	}


