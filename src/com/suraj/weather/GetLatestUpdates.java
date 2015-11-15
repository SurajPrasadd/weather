package com.suraj.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GetLatestUpdates extends AsyncTask<String, Void, Void> {
	
	Spinner city;
	TextView t1,t2,t3,t4;
	URL connecturl;
	InputStream ins;
	Bundle result;
	String cityname;
	double temp,prss,hum;

	public GetLatestUpdates(TextView t1, TextView t2, TextView t3, TextView t4) {
		this.t1=t1;
		this.t2=t2;
		this.t3=t3;
		this.t4=t4;
		this.city=city;
		
	}

	@Override
	protected Void doInBackground(String... arg0) {
		Log.d("debg", arg0[0]);
		
		try {
			connecturl  = new URL(arg0[0]);
			HttpURLConnection connection = (HttpURLConnection) connecturl.openConnection();
			ins = connection.getInputStream();
			InputStreamReader insr = new InputStreamReader(ins);
			BufferedReader br = new BufferedReader(insr);
			
			String line="";
			StringBuffer buffer= new StringBuffer();
			
			while((line = br.readLine())!= null)
			{
				buffer.append(line+"\r\n");
			}
			
			String jsondata =  buffer.toString();
			result = WeatherupdatesJsonParser.getcityandtempvaues(jsondata);
		      cityname =  result.getString("city");
		     Log.d("ddddd", cityname);
		      temp=((result.getDouble("tempurature"))-273.15);
		      prss=result.getDouble("pressure");
		      hum=result.getDouble("humidity");
		      
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if(cityname!=null){
		t1.setText("You have Selected "+cityname);
		t2.setText("Tempurature :"+Integer.toString((int)temp)+"C");
		t3.setText("Pressure :"+Double.toString(prss)+"hPa");
		t4.setText("Humidity :"+Double.toString(hum)+"%");
		}
		else{
			t1.setText("Internet Connection Error");
			t2.setText("");
			t3.setText("");
			t4.setText("");
		}
	}

}
