package com.example.pddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class positivescreen extends AppCompatActivity {
    LottieAnimationView homeicon2 , homeicon3;
    Button homebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.positivescreen);
        homeicon2=findViewById(R.id.home2);

        homebutton=findViewById(R.id.button2);
        homeicon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeintent2=new Intent(positivescreen.this, homepage.class);
                startActivity(homeintent2);
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homebuttonintent=new Intent(positivescreen.this, homepage.class);
                startActivity(homebuttonintent);

            }
        });
    }
}