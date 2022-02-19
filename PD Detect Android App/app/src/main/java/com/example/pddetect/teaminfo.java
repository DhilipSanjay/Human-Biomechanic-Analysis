package com.example.pddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class teaminfo extends AppCompatActivity {
    LottieAnimationView homeicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teaminfo);
        homeicon=findViewById(R.id.home6);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeintent=new Intent(teaminfo.this, homepage.class);
                startActivity(homeintent);
            }
        });
    }
}