package com.example.pddetect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class loadingscreen extends AppCompatActivity {
    String url = "https://pd-detect-api.herokuapp.com/finaldetect";
    Float finalScore;
    int weightage;
    int totalScore;
    HashMap<String, Integer> hyScale = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingscreen);

        finalScore = new Float(0);
        weightage = 5;
        totalScore = 56;
        hyScale.put("Healthy Control", 0);
        hyScale.put("2 severity", 1);
        hyScale.put("2.5 severity", 2);
        hyScale.put("3 severity", 3);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                String type = intent.getStringExtra("type");
                if(type.equals("pd")){
                    // No shared pref yet - fetch from cloud
                    fetchData();

                    // Read from Shared Pref
                    Context context = getApplicationContext();
                    SharedPreferences results = context.getSharedPreferences("Results", Context.MODE_PRIVATE);
                    String data = results.getString("PD Result", "");

                    // Next Activity
                    if(data.equals("Healthy Control")){
                        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
                        Intent negativeintent =new Intent(getApplicationContext(), positivescreen.class);
                        startActivity(negativeintent);
                    }
                    else if(data.equals("Parkinson's Disease")){
                        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
                        Intent negativeintent =new Intent(getApplicationContext(), negativescreen.class);
                        startActivity(negativeintent);
                    }
                    else{
                        Intent loadintent=new Intent(getApplicationContext(), homepage.class);
                        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
                        startActivity(loadintent);
                    }
                }

                if(type.equals("severity")){
                    // Read from Shared Pref
                    Context context = getApplicationContext();
                    SharedPreferences results = context.getSharedPreferences("Results", Context.MODE_PRIVATE);
                    String hyData = results.getString("HY Result", "");
                    int sumScore = getIntent().getIntExtra("score", 0);

                    // Score calculation
                    finalScore = new Float(sumScore);
                    finalScore += (hyScale.get(hyData) * weightage);
                    finalScore = finalScore/totalScore * 100;

                    String data = "Score : " + String.format("%.02f", finalScore) + "%";
                    String grade = "Grade : " + finalSeverity(finalScore);
                    hyData = "HY Score : " + hyData;

                    Intent severityResultIntent = new Intent(getApplicationContext(), severityresult.class);
                    severityResultIntent.putExtra("result", data);
                    severityResultIntent.putExtra("grade", grade);
                    severityResultIntent.putExtra("hyData", hyData);
                    startActivity(severityResultIntent);
                }
                finish();
            }
        },3000);
    }

    public String finalSeverity(float finalScore){
        if (finalScore == 0)
            return "No Signs";
        else if (finalScore <= 30)
            return "Mild";
        else if (finalScore <= 60)
            return "Moderate";
        else
            return "Severe";
    }

    public void fetchData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Intialize Shared Pref
                            Context context = getApplicationContext();
                            SharedPreferences results = context.getSharedPreferences("Results", Context.MODE_PRIVATE);
                            SharedPreferences.Editor resultsEditor = results.edit();

                            JSONObject jsonObject = new JSONObject(response).getJSONObject("result");
                            String[] classes = {"PD Result", "FOG Result", "HY Result"};

                            // Store in Shared Pref
                            for (String key: classes) {
                                String data = jsonObject.getString(key);
                                resultsEditor.putString(key, data);
                            }
                            resultsEditor.commit();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}

