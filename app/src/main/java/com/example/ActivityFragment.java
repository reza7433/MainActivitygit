package com.example;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.Fragment.FirstFragment;
import com.example.Fragment.SecondFragment;


public class ActivityFragment extends AppCompatActivity {


    Button btnadd, btnreplace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        btnadd = (Button) findViewById(R.id.btn_fragment_add);
        btnreplace = (Button) findViewById(R.id.btn_fragment_replace);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.linear_fragment_parent, new FirstFragment());
                transaction.commit();

            }
        });

        btnreplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.linear_fragment_parent, new SecondFragment());
                transaction.commit();

            }
        });


    }
}
