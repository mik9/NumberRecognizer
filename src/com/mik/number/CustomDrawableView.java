package com.mik.number;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CustomDrawableView extends View {
	Paint p=new Paint();
	Bitmap bitmap=null;
	Point last=null,current=null;

	public void clear() {
		bitmap=null;
		current=null;
		last=null;
		invalidate(0, 0, getWidth(), getHeight());
	}
	public Bitmap getBitmap() {
		return bitmap;
	}

	public CustomDrawableView(Context context) {
        super(context);

        p.setColor(0xff74AC23);
    	p.setStyle(Paint.Style.STROKE);
    	p.setAntiAlias(true);
    	p.setStrokeWidth(30	);
    	p.setStrokeCap(Paint.Cap.ROUND);
    	p.setStrokeJoin(Paint.Join.ROUND);
    } 	

    protected void onDraw(Canvas canvas) {
    	if(current==null)
    		return;
    	
    	if(last==null) {
    		return;
    	}
    	
    	if(bitmap==null) {
    		bitmap=Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(),Config.ARGB_8888);
    		/*Canvas t_canvas = new Canvas();
    		t_canvas.setBitmap(bitmap);
    		Paint p=new Paint();
    		p.setColor(0xff74AC23);
        	p.setStyle(Paint.Style.STROKE);
        	p.setAntiAlias(true);
        	p.setStrokeWidth(1);
    		t_canvas.drawLine((int)canvas.getWidth()/2, (int)0, (int)canvas.getWidth()/2, (int)canvas.getHeight(), p);
    		t_canvas.drawLine((int)0, (int)canvas.getHeight()/2, (int)canvas.getWidth(), (int)canvas.getHeight()/2, p);*/
    	}
    	
    	
    	
    	Canvas t_canvas = new Canvas();
    	t_canvas.setBitmap(bitmap);
    	
    	t_canvas.drawLine(last.x, last.y, current.x, current.y, p);
    	
    	canvas.drawBitmap(bitmap, new Matrix(), p);
    }

    public boolean onTouchEvent(MotionEvent e)
    {
    	if(e.getAction()==MotionEvent.ACTION_DOWN) {
			last=null;
			current=null;
		}
    	
    	int x=(int)e.getX();
    	int y=(int)e.getY();
    	
    	if(current!=null) {
    		last=new Point(current);
    	}
    	current=new Point(x,y);
    	
    	if(last!=null) {
    	int lx=last.x,ly=last.y;
	    	int r,l,t,b;
	    	if(x<lx) {
	    		l=x;
	    		r=lx;
	    	} else {
	    		l=lx-10;
	    		r=x;
	    	}
	    	if(y<ly) {
	    		t=y;
	    		b=ly;
	    	} else {
	    		t=ly;
	    		b=y;
	    	}
	    	
	    	invalidate(l-15,t-15,r+15,b+15);
    	}
		
		return true;
    }
}