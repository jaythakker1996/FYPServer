package com.example.demo;


import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;


public class URestCalls {
	
	RestTemplate restTemplate = new RestTemplate();
	
	double endlong;
	double endlat;
	double startlong;
	double startlat;
	String stoken="ntjAdLh-cwNH2Liq3dZmY1TM9lHk_L-UN0bCC73h";
	String UberUrl;
	
	
	public URestCalls() {
		super();
		// TODO Auto-generated constructor stub
	}



	public JSONObject UberCall(Double slat,Double slong,Double elat,Double elong,Integer seat)
	{
		UberUrl="https://api.uber.com/v1.2/estimates/price?server_token="+stoken+"&start_latitude="+slat+"&start_longitude="+slong+"&end_latitude="+elat+"&end_longitude="+elong;
		System.out.println(UberUrl);
		String response = restTemplate.getForObject(UberUrl, String.class);
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(response);
			String resp = jsonObj.toString();
			System.out.println("Json "+resp );
			return jsonObj;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Here");
			return null;
		}

	}
}
