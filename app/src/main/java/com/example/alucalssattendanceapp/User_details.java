package com.example.alucalssattendanceapp;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class User_details extends Fragment {
     Button btnsave;
    EditText editText,id,name,password;
    TextView gender, department, session;
    RadioGroup radios;
    Spinner one;

    public User_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_details, container, false);

        id = (EditText) view.findViewById(R.id.tap);
        editText = (EditText) view.findViewById(R.id.email);
        name = (EditText) view.findViewById(R.id.stdname);
        password = (EditText) view.findViewById(R.id.pwd);
        radios=(RadioGroup) view.findViewById(R.id.radiogroup);
        gender= (TextView)  view.findViewById(R.id.gender);
        department = (TextView) view.findViewById(R.id.dprtmnt);
        session = (TextView) view.findViewById(R.id.session);
        btnsave= view.findViewById(R.id.addbtn);
        one= view.findViewById(R.id.spinnerone);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked = radios.getCheckedRadioButtonId();

                if(editText.length()==0 || id.length() == 0 || name.length() == 0 || password.length() == 0 || checked==-1 ){
                    editText.setError("Email");
                    id.setError("Enter Reg number");
                    name.setError("Enter a valid name");
                    password.setError("Enter a valid password");
                    gender.setTextColor(Color.RED);
                    department.setTextColor(Color.RED);
                    session.setTextColor(Color.RED);
                }

                else {
                    NoifyMe nextFrag= new NoifyMe(); getActivity().getSupportFragmentManager().beginTransaction() .replace(R.id.frame, nextFrag, "findThisFragment") .addToBackStack(null) .commit();


                }
            }
        });
        return view;
    }

}
