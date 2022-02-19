package com.example.pddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class homepage extends AppCompatActivity {
    Button pdbutton;
    LottieAnimationView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        pdbutton=findViewById(R.id.button);

        menu=findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(homepage.this,menu);
                popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getTitle().equals("Team Info"))
                        {
                            Intent teamintent=new Intent(homepage.this, teaminfo.class);
                            startActivity(teamintent);
                        }
                        else if (menuItem.getTitle().equals("About"))
                        {
                            Intent aboutintent=new Intent(homepage.this, about.class);
                            startActivity(aboutintent);
                        }
                        else
                        {
                            Intent profileintent=new Intent(homepage.this, register.class);
                            startActivity(profileintent);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        pdbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear shared preference
                Context context = getApplicationContext();
                SharedPreferences results = context.getSharedPreferences("Results", Context.MODE_PRIVATE);
                SharedPreferences.Editor resultsEditor = results.edit();
                String[] classes = {"PD Result", "FOG Result", "HY Result"};
                for (String key: classes) {
                    resultsEditor.putString(key, "");
                }
                Intent loadIntent = new Intent(homepage.this, loadingscreen.class);
                loadIntent.putExtra("type", "pd");
                startActivity(loadIntent);
            }
        });

    }
}