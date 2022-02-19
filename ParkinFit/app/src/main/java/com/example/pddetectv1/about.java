package com.example.pddetectv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class about extends AppCompatActivity {
    TextView aboutdesc,pddesc,hydesc,combineddesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutdesc=findViewById(R.id.aboutdescription1);
        pddesc=findViewById(R.id.aboutdescription2);
        hydesc=findViewById(R.id.aboutdescription3);
        combineddesc=findViewById(R.id.aboutdescription4);
        aboutdesc.setText("PD Detect is a mobile application developed for the Detection of Parkinson’s Disease using the motor based symptoms and also predict the severity of PD using Hoehn & Yahr Scale and other severity symptoms.");
        pddesc.setText("Parkinson's disease is a brain disorder that leads to shaking, stiffness, and difficulty with walking, balance, and coordination. Parkinson's symptoms usually begin gradually and get worse over time. As the disease progresses, people may have difficulty walking and talking. They may also have mental and behavioral changes, sleep problems, depression, memory difficulties, and fatigue.");
        hydesc.setText("The Hoehn and Yahr scale is a commonly used system for describing how the symptoms of Parkinson's disease progress. A modified Hoehn and Yahr scale was proposed with the addition of stages 1.5 and 2.5.");
        combineddesc.setText("Parkinson's disease is detected using the output from 3 accelerometers placed at the ankle (shank), on the thigh, and on the hip & 16 sensors measuring force (in Newton) placed at the feet. The data is stored and processed in cloud.");

    }
}