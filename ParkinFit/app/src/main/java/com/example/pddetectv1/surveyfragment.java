package com.example.pddetectv1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link surveyfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class surveyfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView question;
    RadioButton opt1,opt2,opt3,opt4;
    ArrayList<RadioButton> options = new ArrayList<RadioButton>();
    Button Prev, nxt,result;
    RadioGroup selected;
    ProgressBar questionProgress;
    int n;
    String json;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Integer[] score;
    int currentProgress;
    int totalScore = 36;


    public surveyfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment surveyfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static surveyfragment newInstance(String param1, String param2) {
        surveyfragment fragment = new surveyfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup surveyroot=(ViewGroup) inflater.inflate(R.layout.fragment_surveyfragment, container, false);
        n = 0;
        currentProgress = 0;

        json = new String("{\"questions\": [\n" +
                "{\"question\": \"Cognitive Impairment\", \"choices\": [\"No Sign\", \"Cognitive dysfunction, mild interference with normal activities\", \"Cognitive dysfunction, moderate interference with normal activities\", \"Cognitive dysfunction precludes patient\\u2019s normal activities\"]},\n" +
                "{\"question\": \"Mood Disorders\", \"choices\": [\"No Sign\", \"Episodes of depressed mood, not lasting more than a day\", \"Depressed mood sustain over days, minimal interference with normal activities\", \"Depressed mood precludes patient\\u2019s normal activities\"]}, \n" +
                "{\"question\": \"Sleep Problems\", \"choices\": [\"No Sign\", \"Sleep problems, trouble getting a full night of sleep\", \"Sleep problems, but sleeps more than half the night\", \"Do not sleep for most of the night \"]}, \n" +
                "{\"question\": \"Dizziness, Fatigue\", \"choices\": [\"No Sign\", \"Feelings occur, no troubles doing things \", \"Feelings occur, some troubles doing things \", \"Feelings preclude doing things\"]}, \n" +
                "{\"question\": \"Tremor\", \"choices\": [\"No Sign\", \"Tremor occurs, No trouble in doing any activities\", \"Tremor occurs, problems with only a few activities \", \"Tremor occurs, problems with many or all activities \"]}, \n" +
                "{\"question\": \"Freezing of Gait\", \"choices\": [\"No Sign\", \"Freezing on starting of walking\", \"Freezing once during walking\", \"Freezing multiple times during walking\"]}, \n" +
                "{\"question\": \"Rigidity\", \"choices\": [\"No Sign\", \"Rigidity detected, but full ROM is easily achieved\", \"Rigidity detected, and full ROM is achieved with effort\", \"Rigidity detected, and full ROM is not achieved\"]}, \n" +
                "{\"question\": \"Gait\", \"choices\": [\"No Sign\", \"Independent walking with minor gait impairment. \", \"Requires an assistance device for safe walking.\", \"Cannot walk at all or only with another person\\u2019s assistance\"]}, \n" +
                "{\"question\": \"Postural Stability\", \"choices\": [\"No Sign\", \"Mildly Unstable\", \"Moderately Unstable\", \"Unstable\"]}, \n" +
                "{\"question\": \"Slowed Movement\", \"choices\": [\"No Sign\", \"Mild global slowness and poverty of spontaneous movements.\", \"Moderate global slowness and poverty of spontaneous movements.  \", \" Slight global slowness and poverty of spontaneous movements. \"]}, \n" +
                "{\"question\": \"Loss of Smell\", \"choices\": [\"No Sign\", \"Slight\", \"Moderate\", \"No smell detected\"]}, \n" +
                "{\"question\": \"Low or Hoarse Voice\", \"choices\": [\"No Sign\", \"Slightly low, not hoarse\", \"Slightly hoarse\", \"More hoarseness\"]}\n" +
                "]\n" +
                "}");

        selected =(RadioGroup) surveyroot.findViewById(R.id.answergroup);
        result = (Button) surveyroot.findViewById(R.id.button3);
        questionProgress =(ProgressBar) surveyroot.findViewById(R.id.progressBar);
        questionProgress.setMax(120);
        result.setVisibility(View.INVISIBLE);
        score = new Integer[12];
        for(int i=0;i<12;i++)
            score[i]=-1;

        question =(TextView) surveyroot.findViewById(R.id.textview4);
        opt1 =(RadioButton) surveyroot.findViewById(R.id.option1);
        opt2 = (RadioButton) surveyroot.findViewById(R.id.option2);
        opt3 = (RadioButton) surveyroot.findViewById(R.id.option3);
        opt4 = (RadioButton) surveyroot.findViewById(R.id.option4);
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);
        Prev = (Button) surveyroot.findViewById(R.id.button);
        nxt = (Button) surveyroot.findViewById(R.id.button2);

        questionCall(n);

        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateScore();
            }
        });

        Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n = n-1;
                currentProgress = (n+1)*10;
                questionProgress.post(new Runnable() {
                    @Override
                    public void run() {
                        questionProgress.setProgress(currentProgress);
                        questionCall(n);
                    }
                });
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(updateScore()) {
                    int sumScore = 0;
                    for (int i = 0; i < 12; i++) {
                        sumScore += score[i];
                    }
                    Float finalScore = new Float(sumScore);
                    finalScore = finalScore / totalScore * 100;

                    SharedPreferences scoreData = getActivity().getSharedPreferences("score", Context.MODE_PRIVATE);
                    SharedPreferences.Editor scoreEdit = scoreData.edit();
                    try {
                        scoreEdit.putFloat("finalScore", finalScore);
                        scoreEdit.commit();
                        Toast.makeText(getActivity(), "Survey Submitted. Check the Result Tab!", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return surveyroot;
    }

    public boolean updateScore(){
        int selectedid = selected.getCheckedRadioButtonId();

        if(selectedid==-1)
        {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "Select any of the option", Toast.LENGTH_SHORT).show();
                }
            });
            return false;
        }
        else {
            currentProgress = (n+1)*10;
            if (selectedid == opt1.getId())
                score[n] = 0;
            else if (selectedid == opt2.getId())
                score[n] = 1;
            else if (selectedid == opt3.getId())
                score[n] = 2;
            else if (selectedid == opt4.getId())
                score[n] = 3;


            if(n != score.length-1) {
                n = n + 1;
                questionCall(n);
            }

            questionProgress.post(new Runnable() {
                @Override
                public void run() {
                    questionProgress.setProgress(currentProgress);
                }
            });
            return true;
        }
    }

    public void questionCall(int n)
    {
        questionProgress.post(new Runnable() {
            @Override
            public void run() {
                questionProgress.setProgress(currentProgress);
            }
        });

        try{
            jsonObject = new JSONObject(json);
            jsonArray = jsonObject.getJSONArray("questions");
        } catch (Exception e){
            e.printStackTrace();
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RadioButton currentOpt;
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
                    opt1.setText(choices.getString(0));
                    opt2.setText(choices.getString(1));
                    opt3.setText(choices.getString(2));
                    opt4.setText(choices.getString(3));

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
                if(n==score.length-1)
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
        });
    }
}