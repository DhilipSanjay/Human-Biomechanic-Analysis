package com.example.pddetectv1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homefragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public homefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homefragment newInstance(String param1, String param2) {
        homefragment fragment = new homefragment();
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
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.fragment_homefragment, container, false);
        ImageView imageView=(ImageView) root.findViewById(R.id.imageview);
        TextView aboutdesc=(TextView) root.findViewById(R.id.aboutdescription1);
        TextView pddesc=(TextView) root.findViewById(R.id.aboutdescription2);
        TextView hydesc=(TextView) root.findViewById(R.id.aboutdescription3);
        TextView combineddesc=(TextView) root.findViewById(R.id.aboutdescription4);
        aboutdesc.setText("\tPD Detect is a mobile application developed for the Detection of Parkinson’s Disease using the motor based symptoms and also predict the severity of PD using Hoehn & Yahr Scale and other severity symptoms.");
        pddesc.setText("\tParkinson's disease is a brain disorder that leads to shaking, stiffness, and difficulty with walking, balance, and coordination. Parkinson's symptoms usually begin gradually and get worse over time. As the disease progresses, people may have difficulty walking and talking. They may also have mental and behavioral changes, sleep problems, depression, memory difficulties, and fatigue.");
        hydesc.setText("\tThe Hoehn and Yahr scale is a commonly used system for describing how the symptoms of Parkinson's disease progress. A modified Hoehn and Yahr scale was proposed with the addition of stages 1.5 and 2.5.");
        combineddesc.setText("\tParkinson's disease is detected using the output from 3 accelerometers placed at the ankle (shank), on the thigh, and on the hip & 16 sensors measuring force (in Newton) placed at the feet. The data is stored and processed in cloud.");

        return root;
    }
}