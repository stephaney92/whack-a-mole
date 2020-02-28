package com.example.whackamole;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;


public class picture_activity extends AppCompatActivity {
    private RadioButton moleButton;
    private RadioButton smithersButton;
    private RadioButton cowButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //connects this class to the layout view
        setContentView(R.layout.activity_changepicture);

        moleButton = findViewById(R.id.moleButton);
        smithersButton = findViewById(R.id.smithersButton);
        cowButton = findViewById(R.id.cowButton);

        Intent i = new Intent();


    }
    @Override
    public void onBackPressed(){
        int pic;
        if(moleButton.isChecked()){
            pic = 1;
        }
        else if (smithersButton.isChecked()){
             pic = 2;
        }
        else
            pic = 3;


        //used to ship info back and forth
        Intent i = new Intent();
        i.putExtra("PICTURE", pic);
        //when color activity gives a pic  it ships result back to main activity
        setResult(RESULT_OK, i);
        finish();
    }
}
