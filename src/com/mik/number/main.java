package com.mik.number;

import com.mik.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class main extends Activity {
	CustomDrawableView mCustomDrawableView;
	Recognizer recognizer;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bitmap numbers[]=new Bitmap[10];
        Log.v("RECOGNIZER","starting");
        
        	numbers[0]=BitmapFactory.decodeResource(getResources(),R.drawable.number0);
        	numbers[1]=BitmapFactory.decodeResource(getResources(),R.drawable.number1);
        	numbers[2]=BitmapFactory.decodeResource(getResources(),R.drawable.number2);
        	numbers[3]=BitmapFactory.decodeResource(getResources(),R.drawable.number3);
        	numbers[4]=BitmapFactory.decodeResource(getResources(),R.drawable.number4);
        	numbers[5]=BitmapFactory.decodeResource(getResources(),R.drawable.number5);
        	numbers[6]=BitmapFactory.decodeResource(getResources(),R.drawable.number6);
        	numbers[7]=BitmapFactory.decodeResource(getResources(),R.drawable.number7);
        	numbers[8]=BitmapFactory.decodeResource(getResources(),R.drawable.number8);
        	numbers[9]=BitmapFactory.decodeResource(getResources(),R.drawable.number9);
        
        /*double numbers[][] = {
	        {0.25,0.25,0.25,0.25},
	        {0.2 ,0.5 ,0.3 ,0   },
	        {0.2 ,0.3 ,0.19,0.31},
	        {0.15,0.35,0.35,0.15},
	        {0.33,0.33,0.33,0   },
	        {0.36,0.15,0.35,0.14},
	        {0.29,0.12,0.29,0.3 },
	        {0.19,0.49,0.02,0.3 },
	        {0.25,0.25,0.25,0.25},
	        {0.29,0.3 ,0.29,0.12},
        };*/
        recognizer = new Recognizer(numbers);
        Toast.makeText(main.this,"Caching finished", Toast.LENGTH_LONG).show();
        
        mCustomDrawableView = new CustomDrawableView(this);
        
        setContentView(mCustomDrawableView);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	
    	MenuItem clear = menu.add("Clear");
    	clear.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem item) {
				mCustomDrawableView.clear();
				return true;
			}
		});
    	MenuItem rec = menu.add("Recognize");
    	rec.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			
			public boolean onMenuItemClick(MenuItem item) {
				int n=recognizer.recognize(mCustomDrawableView.getBitmap());
				Toast.makeText(main.this,""+n, Toast.LENGTH_LONG).show();
				return false;
			}
		});
    	
    	return true;
    }
}