package com.example.pddetectv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class splashscreen extends AppCompatActivity {
    ImageView medicalbackground;
    TextView ourappname;
    LottieAnimationView doctoranmie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        doctoranmie=findViewById(R.id.doctorlotties);
        medicalbackground=findViewById(R.id.bg);
        ourappname=findViewById(R.id.pdtext);
        medicalbackground.animate().translationY(-3000).setDuration(1000).setStartDelay(2000);
        doctoranmie.animate().translationY(1400).setDuration(1000).setStartDelay(2000);
        ourappname.animate().translationY(1400).setDuration(1000).setStartDelay(2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences profileCheck = getSharedPreferences("profile", MODE_PRIVATE);
                String name = profileCheck.getString("name","");
                if(name.equals(""))
                {
                    Intent registerIntent = new Intent(splashscreen.this,register.class);
                    startActivity(registerIntent);
                }
                else {
                    //Toast.makeText(splashscreen.this, "Welcome " + name, Toast.LENGTH_LONG).show();
                    Intent directHomeIntent=new Intent(splashscreen.this,login.class);
                    startActivity(directHomeIntent);
                }
                finish();
            }
        },3000);

    }
}