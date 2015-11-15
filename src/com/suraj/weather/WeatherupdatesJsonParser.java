package com.suraj.weather;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;

public class WeatherupdatesJsonParser {
	
	static Bundle b1=new Bundle();

	public static Bundle getcityandtempvaues(String jsondata) {

		
		
		JSONObject rootobject;
		try {
			rootobject = new JSONObject(jsondata);
			
			String cityname = rootobject.getString("name");
			
			JSONObject mainobJsonObject = rootobject.getJSONObject("main");
			Double tempurature = mainobJsonObject.getDouble("temp");
			Double pressure = mainobJsonObject.getDouble("pressure");
			Double humidity = mainobJsonObject.getDouble("humidity");
			
			
			Log.d("DEBUG", tempurature.toString());
			
			b1.putString("city", cityname);
			b1.putDouble("tempurature", tempurature);
			b1.putDouble("pressure", pressure);
			b1.putDouble("humidity", humidity);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		
		
		return b1;
	}

}
