package com.example.pddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class severityresult extends AppCompatActivity {
    TextView resultscore, severityGrade, hyScore;
    Button homenavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.severityresult);
        String data = getIntent().getStringExtra("result");
        String grade = getIntent().getStringExtra("grade");
        String hyData = getIntent().getStringExtra("hyData");

        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
        resultscore = findViewById(R.id.severityPercent);
        severityGrade = findViewById(R.id.severityGradeText);
        hyScore = findViewById(R.id.hyScoreText);
        homenavigation = findViewById(R.id.button2);

        resultscore.setText(data);
        severityGrade.setText(grade);
        hyScore.setText(hyData);

        homenavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homenavigationintent = new Intent(severityresult.this,homepage.class);
                startActivity(homenavigationintent);
            }
        });
    }
}