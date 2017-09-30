package com.example.demo;

import org.json.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCalls {
	
	RestTemplate restTemplate = new RestTemplate();
	
	RestCalls()
	{
	}
	
	public String getApiCall(String zomatoUrl,JSONObject json)
	{
		//Verify changes
		
		//To add headers when authentication and other additional headers used
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		String accessToken ="b0dd24cdb0dc487b527e518264e529f5";
		
		httpHeaders.set("user-key", accessToken);
		HttpEntity <String> httpEntity;
		
		if(json!=null)
		{
			//Creation of packet with headers
		 httpEntity = new HttpEntity <String> (json.toString(), httpHeaders);
		}
		else
		{
		 httpEntity = new HttpEntity <String> (null, httpHeaders);
		}
		
		//Sending the packet to server
		String response = restTemplate.postForObject(zomatoUrl, httpEntity, String.class);

		
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(response);
			String resp = jsonObj.toString();
			System.out.println("Json "+resp );
			return resp;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Here");
			return "Failed";
		}

	}
	
	public void postApiCall()
	{
				
	}

}
