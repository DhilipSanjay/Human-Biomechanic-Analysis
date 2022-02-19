package com.example.pddetectv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText userid,passwordlogin;
    String appname,apppassword;
    Button homeintentbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        homeintentbutton=findViewById(R.id.button);
        userid=findViewById(R.id.userid);
        passwordlogin=findViewById(R.id.pswd);
        homeintentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences profileCheck = getSharedPreferences("profile", MODE_PRIVATE);
                String nameValue = profileCheck.getString("name","");
                String password = profileCheck.getString("password","");
                appname=userid.getText().toString();
                apppassword=passwordlogin.getText().toString();
                if(nameValue.equals(appname)&&password.equals(apppassword))
                {
                    Toast.makeText(getApplicationContext(), "Welcome "+nameValue, Toast.LENGTH_SHORT).show();
                    Intent homeintent=new Intent(login.this,MainActivity.class);
                    startActivity(homeintent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "UserID or Password Incorrect", Toast.LENGTH_SHORT).show();
                }
                /*
                Intent homeintent=new Intent(login.this,MainActivity.class);
                startActivity(homeintent);
                */

            }
        });
    }
}
