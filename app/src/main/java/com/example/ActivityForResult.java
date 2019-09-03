package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class ActivityForResult extends AppCompatActivity {

    Button btnresult;
    private boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);

        btnresult = (Button) findViewById(R.id.btn_ActivityForResult_test);

        btnresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String result = "test result";
                Intent intent = new Intent();
                intent.putExtra("result", result);
                setResult(RESULT_OK, intent);
                finish();


            }
        });


    }
}
