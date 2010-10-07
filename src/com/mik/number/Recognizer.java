package com.mik.number;

import java.util.Vector;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;



public class Recognizer extends java.lang.Object{
	double numbers[][][]=new double[10][][];
	private static final int VSIZE=7;
	private static final int HSIZE=7;
	private static final int N=10;
	
	public Recognizer(Bitmap _numbers[]) {
		for(int i=0;i<N;i++) {
			Log.i("tag", ""+i);
			numbers[i]=cacheBitmap(_numbers[i]);
		}
	}
	
	public double[][] cacheBitmap(Bitmap b) {
		
		int top=-1,bottom=-1,right=-1,left=-1;
		int width=b.getWidth();
		int height = b.getHeight();
		
		// top
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				int p=b.getPixel(x,y);
				if( p == -9195999 || p==0xff74AC23) {
					top=y;
					break;
				}
			}
			if(top!=-1)
				break;
		}
		Log.i("rec", "top: "+top);
		
		//left
		for(int x=0;x<width;x++) {
			for(int y=top;y<height;y++) {
				int p=b.getPixel(x,y);
				if( p == -9195999 || p == 0xff74AC23) {
					left=x;
					break;
				}
			}
			if(left!=-1)
				break;
		}
		Log.i("rec", "left: "+left);
		
		//bottom
		for(int y=height-1;y>0;y--) {
			for(int x=left;x<width;x++) {
				int p=b.getPixel(x,y);
				if( p == -9195999 || p==0xff74AC23) {
					bottom=height-y;
					break;
				}
			}
			if(bottom!=-1)
				break;
		}
		Log.i("rec", "bottom: "+bottom);
		
		//right
		for(int x=width-1;x>0;x--) {
			for(int y=0;y<height-bottom;y++) {
				int p=b.getPixel(x,y);
				if( p == -9195999 || p==0xff74AC23) {
					right=width-x;
					break;
				}
			}
			if(right!=-1)
				break;
		}
		Log.i("rec", "right: "+right);

		int w = (b.getWidth()-right-left)/HSIZE;
//		Log.i("rec", ""+w);
		int h = (b.getHeight()-top-bottom)/VSIZE;
		
		int s=0;
		double curr[][] = new double[VSIZE][HSIZE];
		for(int z=0;z<VSIZE;z++) {
			for(int q=0;q<HSIZE;q++) {
				
				curr[z][q]=0;
				for(int i=right+z*w; i<right+z*w+w;i++) {
					for (int j=top+q*h;j<top+q*h+h;j++) {
						int p=b.getPixel(i,j);
						if(p == -9195999 || p == 0xff74AC23) {
							curr[z][q]++;
						}
					}
				}
				s+=curr[z][q];
				//Log.i("rec", ""+curr[z][q]);
			}
		}
		for(int z=0;z<VSIZE;z++) {
			for(int q=0;q<HSIZE;q++) {
				curr[z][q] = (double)curr[z][q]/(double)s;
				//Log.i("rec",""+curr[z][q]);
			}
		}
		
		return curr;
	}
	public int recognize(Bitmap b) {
		double curr[][] = cacheBitmap(b);
		int min=0;
		double t[]=new double[10];
		for(int i=0;i<N;i++) {
			t[i]=0;
			for(int x=0;x<VSIZE;x++) {
				for (int y=0;y<HSIZE;y++) {
					t[i] += Math.abs(numbers[i][x][y]-curr[x][y]);
					//Log.i("rec", ""+z);
				}
			}
			if(t[i]<t[min])
				min=i;
			Log.i("rec", i+" == "+t[i]);
		}
		return min;
	}
	
	/* 
	 * Стара реалізація
	 * 
	Vector<Vector<Integer>> vertical=new Vector<Vector<Integer>>();
	Vector<Vector<Integer>> horizontal=new Vector<Vector<Integer>>();
	Bitmap numbers[]=new Bitmap[10];
	
	public void createMapCache(Bitmap numbers[]) {
		for(int i=0;i<10;i++) {
			Log.v("Creating map cache", " "+i);
			vertical.add(Analyzer.getVerticalMap(numbers[i]));
			horizontal.add(Analyzer.getHorizontalMap(numbers[i]));
		}
	}
	
	public int recognize(Bitmap bitmap) {
		Vector<Integer> bitmap_h=Analyzer.getHorizontalMap(bitmap);
		Vector<Integer> bitmap_v=Analyzer.getVerticalMap(bitmap);
		int s[]=new int[10];
		int min=0;
		
		for(int k=0;k<10;k++) {
			s[k]=0;
			for(int i=0;i<bitmap_h.size();i++) {
				s[k]+=Math.abs(bitmap_h.get(i) - horizontal.get(k).get(i/bitmap_h.size()*horizontal.get(k).size()));
			}
			for(int i=0;i<bitmap_v.size();i++) {
				s[k]+=Math.abs(bitmap_v.get(i) - vertical.get(k).get(i/bitmap_v.size()*vertical.get(k).size()));
			}
			Log.v("Recognizing", "=="+k+"  "+s[k]);
			if(s[k]<s[min])
				min=k;
		}
		Log.v("Recognized", " "+min);
		
		return -1;
	}
	*/
}
