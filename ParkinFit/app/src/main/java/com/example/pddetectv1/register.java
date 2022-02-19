package com.example.pddetectv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    Button submit;
    TextView name, age, height, weight,passwrd;
    TextView gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       // setContentView(R.layout.activity_register);
        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.spinnertext);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        passwrd=findViewById(R.id.password);

        final AutoCompleteTextView genderAuto = findViewById(R.id.spinnertext);

        ArrayList<String> genderList = getgender();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(register.this, android.R.layout.simple_spinner_item, genderList);
        fillDetails();
        genderAuto.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences profileData = getSharedPreferences("profile",MODE_PRIVATE);
                SharedPreferences.Editor profileEdit = profileData.edit();
                String nameval,genderval,passwordval;
                int ageval;
                float heightval,weightval;
                try{
                    nameval=name.getText().toString();
                    genderval=gender.getText().toString();
                    passwordval=passwrd.getText().toString();
                    ageval=Integer.parseInt(age.getText().toString());
                    heightval=Float.parseFloat(height.getText().toString());
                    weightval=Float.parseFloat(weight.getText().toString());

                    Pattern uppercase = Pattern.compile("[A-Z]");
                    Pattern lowercase = Pattern.compile("[a-z]");
                    Pattern digit = Pattern.compile("[0-9]");

                    if(genderval.equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Select Gender", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(ageval<=80 && ageval>=20)
                        {
                            if(heightval<=200&&heightval>=120)
                            {
                                if(weightval<=120&&weightval>=35)
                                {
                                    if(!lowercase.matcher(passwordval).find()||!uppercase.matcher(passwordval).find()||!digit.matcher(passwordval).find())
                                    {
                                        Toast.makeText(getApplicationContext(), "Password must contain one upper case, one lower case and one digit value", Toast.LENGTH_LONG).show();
                                    }
                                    else {

                                        profileEdit.putString("name", nameval);
                                        profileEdit.putInt("age", ageval);
                                        profileEdit.putString("gender", genderval);
                                        profileEdit.putFloat("height", heightval);
                                        profileEdit.putFloat("weight", weightval);
                                        profileEdit.putString("password", passwordval);
                                        profileEdit.commit();
                                        Toast.makeText(getApplicationContext(), "Profile Created Successfilly", Toast.LENGTH_SHORT).show();

                                        Intent homeIntent = new Intent(register.this, MainActivity.class);
                                        startActivity(homeIntent);
                                    }

                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Enter Proper Weight", Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Enter Proper Height", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Enter Proper Age", Toast.LENGTH_LONG).show();
                        }
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Fill all the details!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
        });
    }
    private ArrayList<String> getgender()
    {
        ArrayList<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");
        return gender;
    }

    private void fillDetails(){
        SharedPreferences profileCheck = getSharedPreferences("profile", MODE_PRIVATE);
        String nameValue = profileCheck.getString("name","");
        if(!nameValue.equals("")){
            // Home button enable & visible
            submit.setText("UPDATE PROFILE");

            Integer ageValue = profileCheck.getInt("age", 0);
            String genderValue = profileCheck.getString("gender","");
            Float heightValue = profileCheck.getFloat("height",0);
            Float weightValue = profileCheck.getFloat("weight",0);
            String passwordretrival=profileCheck.getString("password","");

            name.setText(nameValue);
            age.setText(ageValue.toString());
            gender.setText(genderValue);
            height.setText(heightValue.toString());
            weight.setText(weightValue.toString());
            passwrd.setText(passwordretrival);

        }
    }

}