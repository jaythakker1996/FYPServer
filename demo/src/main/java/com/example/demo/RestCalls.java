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
	
	String accessTokenZ ="b0dd24cdb0dc487b527e518264e529f5";
	String accessTokenU ="3K2-Zu5X9KWn0vAu2ER8dD6dOUBv89fo66ykVB_E";
	
	RestCalls()
	{
	}
	
	public String postApiCall(String apiUrl,JSONObject json)
	{
		//Verify changes
		
		//To add headers when authentication and other additional headers used
		HttpHeaders httpHeaders = new HttpHeaders();
		
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		String header;
		String accessToken;
		header="user-key";
		accessToken=accessTokenZ;
		
		httpHeaders.set(header,"Bearer "+ accessToken);
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
		String response = restTemplate.postForObject(apiUrl, httpEntity, String.class);

		
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
	
	public String getApiCall(Double slat,Double slong,Double elat,Double elong,Integer seat)
	{
		String UberUrl="https://api.uber.com/v1.2/estimates/price?server_token="+accessTokenU+"&start_latitude="+slat+"&start_longitude="+slong+"&end_latitude="+elat+"&end_longitude="+elong;
		String response = restTemplate.getForObject(UberUrl, String.class);
		System.out.println(UberUrl);
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

}
