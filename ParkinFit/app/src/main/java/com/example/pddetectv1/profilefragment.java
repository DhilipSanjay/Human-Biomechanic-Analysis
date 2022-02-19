package com.example.pddetectv1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profilefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profilefragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button submit;
    TextView name;
    TextView age;
    TextView gender;
    TextView height;
    TextView weight;
    TextView passwrd;

    public profilefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profilefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profilefragment newInstance(String param1, String param2) {
        profilefragment fragment = new profilefragment();
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

        ViewGroup rootprofile=(ViewGroup) inflater.inflate(R.layout.fragment_profilefragment, container, false);

        submit =(Button) rootprofile.findViewById(R.id.submit);
        name =(TextView) rootprofile.findViewById(R.id.name);
        age =(TextView) rootprofile.findViewById(R.id.age);
        gender =(TextView) rootprofile.findViewById(R.id.spinnertext);
        height =(TextView) rootprofile.findViewById(R.id.height);
        weight =(TextView) rootprofile.findViewById(R.id.weight);
        passwrd=(TextView) rootprofile.findViewById(R.id.password);

        final AutoCompleteTextView genderAuto = (AutoCompleteTextView) rootprofile.findViewById(R.id.spinnertext);

        ArrayList<String> genderList = getgender();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, genderList);
        fillDetails();
        genderAuto.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences profileData = getActivity().getSharedPreferences("profile", Context.MODE_PRIVATE);
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
                        Toast.makeText(getActivity(), "Select Gender", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(getActivity(), "Password must contain one upper case, one lower case and one digit value", Toast.LENGTH_LONG).show();
                                    }
                                    else {

                                        profileEdit.putString("name", nameval);
                                        profileEdit.putInt("age", ageval);
                                        profileEdit.putString("gender", genderval);
                                        profileEdit.putFloat("height", heightval);
                                        profileEdit.putFloat("weight", weightval);
                                        profileEdit.putString("password", passwordval);
                                        profileEdit.commit();
                                        Toast.makeText(getActivity(), "Profile Updated Successfilly", Toast.LENGTH_LONG).show();
                                    }

                                }
                                else
                                {
                                    Toast.makeText(getActivity(), "Enter Proper Weight", Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Enter Proper Height", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Enter Proper Age", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Fill all the details!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                }
            });
        return rootprofile;
    }
    private ArrayList<String> getgender()
    {
        ArrayList<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");
        return gender;
    }

    private void fillDetails(){
        SharedPreferences profileCheck = getActivity().getSharedPreferences("profile", Context.MODE_PRIVATE);
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