package com.example.pddetectv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class survey extends AppCompatActivity {
    TextView question;
    RadioButton opt1,opt2,opt3,opt4;
    ArrayList<RadioButton> options = new ArrayList<RadioButton>();
    Button Prev, nxt,result;
    RadioGroup selected;
    ProgressBar questionProgress;
    int n=0;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Integer[] score;
    int currentProgress=10;

    public String loadJSONFromAsset() {
        String json=null;
        try {
            InputStream is = this.getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);


        selected = findViewById(R.id.answergroup);
        result = findViewById(R.id.button3);
        questionProgress = findViewById(R.id.progressBar);
        questionProgress.setMax(120);
        questionProgress.setProgress(currentProgress);
        result.setVisibility(View.INVISIBLE);
        score = new Integer[12];
        for(int i=0;i<12;i++)
            score[i]=-1;

        question = findViewById(R.id.textview4);
        opt1 = findViewById(R.id.option1);
        opt2 = findViewById(R.id.option2);
        opt3 = findViewById(R.id.option3);
        opt4 = findViewById(R.id.option4);
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);
        Prev = findViewById(R.id.button);
        nxt = findViewById(R.id.button2);

        try{
            jsonObject = new JSONObject(loadJSONFromAsset());
            jsonArray = jsonObject.getJSONArray("questions");

        } catch (Exception e) {
            e.printStackTrace();
        }

        questionCall(0);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedid = selected.getCheckedRadioButtonId();

                if(selectedid==-1)
                {
                    Toast.makeText(getApplicationContext(), "Select any of the option", Toast.LENGTH_SHORT).show();
                }
                else {
                    currentProgress = currentProgress+10;
                    questionProgress.setProgress(currentProgress);
                    if (selectedid == opt1.getId())
                        score[n] = 0;
                    else if (selectedid == opt2.getId())
                        score[n] = 1;
                    else if (selectedid == opt3.getId())
                        score[n] = 2;
                    else if (selectedid == opt4.getId())
                        score[n] = 3;

                    n = n + 1;
                    questionCall(n);
                }
            }
        });
        Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentProgress = currentProgress-10;
                questionProgress.setProgress(currentProgress);
                n = n-1;
                questionCall(n);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sumScore = 0;
                for (int s : score){
                    sumScore += s;
                }
                /*Intent loadintent = new Intent(survey.this, MainActivity.class);
                loadintent.putExtra("type", "severity");
                loadintent.putExtra("score", sumScore);
                startActivity(loadintent);
            */}
        });

    }
    public void questionCall(int n)
    {
        selected.clearCheck();
        question.setText("");
        opt1.setText("");
        opt2.setText("");
        opt3.setText("");
        opt4.setText("");

        try {
            JSONObject currentObj = jsonArray.getJSONObject(n);
            question.setText(currentObj.getString("question"));
            JSONArray choices = currentObj.getJSONArray("choices");
            for(int i=0; i<4; i++){
                options.get(i).setText(choices.getString(i));
            }
            if (score[n] != -1){
                options.get(score[n]).setChecked(true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(n==0)
        {
            Prev.setEnabled(false);
        }
        else
        {
            Prev.setEnabled(true);
        }
        if(n==11)
        {
            nxt.setVisibility(View.INVISIBLE);
            result.setVisibility(View.VISIBLE);
        }
        else
        {
            nxt.setVisibility(View.VISIBLE);
            result.setVisibility(View.INVISIBLE);
        }

    }
}