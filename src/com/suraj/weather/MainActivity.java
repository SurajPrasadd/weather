package com.suraj.weather;

import android.R.color;
import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Spinner city;
	TextView t1,t2,t3,t4;
	Button refresh;
	//String updateurl = "http://api.openweathermap.org/data/2.5/weather?q=Bangalore,India&appid=bd82977b86bf27fb59a04b61b657fb6f";
	String updateurl ;
	RelativeLayout r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city=(Spinner) findViewById(R.id.spinner1);
        t1=(TextView) findViewById(R.id.textView2);
        t2=(TextView) findViewById(R.id.textView3);
        t3=(TextView) findViewById(R.id.textView4);
        t4=(TextView) findViewById(R.id.textView5);
        refresh=(Button) findViewById(R.id.button1);
        
       r=(RelativeLayout) findViewById(R.layout.activity_main);
        
        final String[] place={"City","Bangalore","Mangalore","Patna","Delhi"};
        ArrayAdapter<String> aa=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,place);
        city.setAdapter(aa);
        
        
        city.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if(arg2!=0){
					//if(arg2==1){
					//r.setBackgroundResource(R.drawable.rfgyy);
					/*if(arg2==2){
						r.setBackgroundResource(R.drawable.man);}
					if(arg2==3){
						r.setBackgroundResource(R.drawable.pa);}
					if(arg2==4){
						r.setBackgroundResource(R.drawable.d);}*/
					//r.setBackgroundColor(Color.BLUE);
				updateurl="http://api.openweathermap.org/data/2.5/weather?q="+place[arg2]+",India&appid=bd82977b86bf27fb59a04b61b657fb6f";
				 
				refresh.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							
							//r.setBackgroundColor(Color.GREEN);
							GetLatestUpdates updates = new GetLatestUpdates(t1,t2,t3,t4);
							updates.execute(updateurl);
						}
					});
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				}else if(arg2==0){
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
                      refresh.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
					Toast.makeText(MainActivity.this, "plz select city", Toast.LENGTH_SHORT).show();
				
                              }
				});}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
      
        
        
        
    }


    
    
}
