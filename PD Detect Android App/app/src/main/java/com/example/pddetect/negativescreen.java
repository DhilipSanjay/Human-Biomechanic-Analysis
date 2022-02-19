package com.example.pddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class negativescreen extends AppCompatActivity {
    LottieAnimationView homeicon4 ;
    Button  severitybutton, homebutton;
    TextView fogText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.negativescreen);
        fogText = findViewById(R.id.fogText);
        homeicon4 = findViewById(R.id.home4);
        severitybutton = findViewById(R.id.button1);
        homebutton = findViewById(R.id.button2);
        Context context = getApplicationContext();
        SharedPreferences results = context.getSharedPreferences("Results", Context.MODE_PRIVATE);
        String data = results.getString("FOG Result", "");
        fogText.setText(data);

        homeicon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeintent4=new Intent(negativescreen.this, homepage.class);
                startActivity(homeintent4);
            }
        });
         severitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent severityintent=new Intent(negativescreen.this, severityquestion.class);
                startActivity(severityintent);
            }
        });

       homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homebuttonintent=new Intent(negativescreen.this, homepage.class);
                startActivity(homebuttonintent);
            }
        });
    }
}