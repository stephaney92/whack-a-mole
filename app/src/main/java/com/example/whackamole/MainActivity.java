package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    private Drawable moleImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    public Handler handler;
    public int count;
    public updateCount update;
    public boolean on;
    private TextView pointCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointCounter = findViewById(R.id.pointCounter);
        gridLayout = findViewById(R.id.girdLayout);
        //finds the image in the drawable folder
        moleImage = getDrawable(R.drawable.mole);
        //the circles
        imageViews = new ImageView[16];
        handler = new Handler();
        count = 0;
        on = false;
        update = new updateCount();

        rand = new Random();
        //random int less than 16
        moleLocation = rand.nextInt(16);
        for(int i=0; i<16; i++){
            //taking image view that's created and sticking it in the variable imageViews[i]
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.moleview, null);
            //forces the image views to be the same size as the mole hole
            imageViews[i].setMinimumHeight(270);
            imageViews[i].setMinimumWidth(270);
            //taking the view and adding it to the grid
            if (i == moleLocation){
                //taking the mole image and adding to the view
                imageViews[i].setImageDrawable(moleImage);
            }
            gridLayout.addView(imageViews[i]);
        }
    }
    public void startPressed(View v){
        //if the button is pressed already clicking it again will stop it
        if (on) {
            on = false;
            //removes handler and stops counter
            handler.removeCallbacks(update);

        } else {
            //else if its on keep and has not been clicked a second time counter keeps going
            on = true;
            //creates handler starts counter
            handler.postDelayed(update, 1000);
        }

    }
    public void molePressed(View v){
        if(v.equals(imageViews[moleLocation])) {
            count++;
            pointCounter.setText(count + "");

        }

    }
    public class updateCount implements Runnable{

        public void run(){
            int lastLocation;
            //the last place the mole was located
            lastLocation = moleLocation;
            //starts the mole in a random location
            moleLocation = rand.nextInt(16);
            //adds mole images to each circle(imageview) in random locations
            imageViews[moleLocation].setImageDrawable(moleImage);
            //the last place the mole was it gets deleted, sets location to null
            imageViews[lastLocation].setImageDrawable(null);
            handler.postDelayed(update, 1000);


        }
    }

}
