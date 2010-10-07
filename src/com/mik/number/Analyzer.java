package com.mik.number;

import java.util.Vector;

import android.graphics.Bitmap;
import android.util.Log;

public class Analyzer {
	public static Vector<Integer> getVerticalMap(Bitmap bitmap) {
		Vector<Integer> v=new Vector<Integer>();
		
		for(int i=0;i<bitmap.getHeight();i++) {
			int s=0;
			for(int j=0;j<bitmap.getWidth();j++) {
				if(bitmap.getPixel(j, i) == 0xff74AC23)
					s++;
			}
			if(s!=0 && s>10) {
				v.add(new Integer(s));
				Log.v("ololo",i+" : "+s);
			}
		}
		return v;
	}
	public static Vector<Integer> getHorizontalMap(Bitmap bitmap) {
		Vector<Integer> v=new Vector<Integer>();
		
		for(int i=0;i<bitmap.getWidth();i++) {
			int s=0;
			for(int j=0;j<bitmap.getHeight();j++) {
				if(bitmap.getPixel(i, j) == 0xff74AC23)
					s++;
			}
			if(s!=0 && s>10)
				v.add(new Integer(s));
		}
		return v;
	}
}
