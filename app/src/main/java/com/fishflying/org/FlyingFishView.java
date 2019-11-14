package com.fishflying.org;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class FlyingFishView extends View {
    private Bitmap fish[]=new Bitmap[2];
    private Bitmap backgraoundImage;
    private Paint scorePaint=new Paint();
    private Bitmap life[]=new Bitmap[2];
    private int fishx=10;
    private int fishy;
    private int fishSpeed;
    private int canvasWidth,canvasHeight;
    private Boolean touch=false;
    private int yellowX,yellowY,yellowSpeed=9;
    private Paint yellowPaint=new Paint();
    private int blueY,blueX,blueSpeed=9;
    private Paint bluePaint=new Paint();
    private int greenX,greenY,greenSpeed=7;
    private Paint greenPaint=new Paint();
    private int redX,redY,redSpeed=12;
    private Paint redPaint=new Paint();
    private int score,lifecounter;






    public FlyingFishView(Context context) {
        super(context);
        fish[0]= BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1]= BitmapFactory.decodeResource(getResources(),R.drawable.fish2);
        backgraoundImage=BitmapFactory.decodeResource(getResources(), R.drawable.background);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(30);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        bluePaint.setColor(Color.BLUE);
        bluePaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);
        fishy=550;
        score=0;
        lifecounter=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasHeight=canvas.getHeight();
        canvasWidth=canvas.getWidth();

        canvas.drawBitmap(backgraoundImage,0,0,null);

        int minfishy=fish[0].getHeight();
        int maxfishy=canvasHeight- fish[0].getHeight()*3;

        fishy=fishy+fishSpeed;
        if (fishy<minfishy)
        {
            fishy=minfishy;
        }
        if (fishy>maxfishy)
        {
            fishy=maxfishy;
        }
        fishSpeed=fishSpeed+2;
        if (touch)
        {
            canvas.drawBitmap(fish[1],fishx,fishy,null);
            touch=false;
        }
        else
        {
            canvas.drawBitmap(fish[0],fishx,fishy,null);
        }

        yellowX=yellowX-yellowSpeed;
        if (hitballchecker(yellowX,yellowY))
        {
            score+=10;
            yellowX=-100;
        }
        if (yellowX<0)
        {
            yellowX=canvasWidth+21;
            int temp=getRandom(200,1000);
            yellowY=temp;

        }
        canvas.drawCircle(yellowX,yellowY,30,yellowPaint);

        greenX=greenX-greenSpeed;
        if (hitballchecker(greenX,greenY))
        {
            score+=50;
            greenX=-100;
        }
        if (greenX<0)
        {
            greenX=canvasWidth+21;
            int temp=getRandom(200,1000);
            greenY=temp;//*(maxfishy-minfishy)+minfishy+20;


        }
        canvas.drawCircle(greenX,greenY,45,greenPaint);
        blueX=blueX-greenSpeed;
        if (hitballchecker(blueX,blueY))
        {
            score+=10;
            blueX=-100;
        }
        if (blueX<0)
        {
            blueX=canvasWidth+21;
            int temp=getRandom(200,1000);
            blueY=temp;//*(maxfishy-minfishy)+minfishy+20;

        }
        canvas.drawCircle(blueX,blueY,30,bluePaint);
        redX=redX-redSpeed;
        if (hitballchecker(redX,redY))
        {
            redX=-500;
            lifecounter--;
            if (lifecounter==0)
            {
                Intent intent=new Intent(getContext(),GameOverActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("score",score);
                getContext().startActivity(intent);
            }
        }
        if (redX<-200)
        {
            redX=canvasWidth+100;
            int temp=getRandom(200,1000);
            redY=temp;

        }
        canvas.drawCircle(redX,redY,45,redPaint);


        for (int i=0;i<3;i++)
        {
            int x=(int)(400+life[0].getWidth()*1.5*i);
            int y=30;
            if (i<lifecounter)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }
            else
            {
                canvas.drawBitmap(life[1],x,y,null);
            }
        }
        canvas.drawText("Score :"+score,20,60,scorePaint);

    }

    public  int getRandom(int _min,int _max)
    {
        Random random=new Random();
        return random.nextInt(_max-_min+1)+_min;
    }

    public boolean hitballchecker(int x,int y)
    {
        if (fishx<x && x<(fishx+fish[0].getWidth()) &&fishy<y &&y<(fishy+fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            touch=true;
            fishSpeed=-22;
        }
        return true;
    }
}
