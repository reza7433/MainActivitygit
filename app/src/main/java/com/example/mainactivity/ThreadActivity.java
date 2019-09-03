package com.example.mainactivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.MyAsyncTask;
import com.example.R;

public class ThreadActivity extends AppCompatActivity {


    MyAsyncTask myAsyncTask;
    ProgressBar progressBar;
    String name = "";
    Thread thread;
    TextView txtpercent;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        txtpercent = (TextView) findViewById(R.id.txt_thread_percent);
        handler = new Handler();

        MyAsyncTask myAsyncTask = new MyAsyncTask(progressBar, txtpercent);
        myAsyncTask.execute();


       /* progressBar=(ProgressBar)findViewById(R.id.progress_Thread);
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<=1000000; i++) {

                    progressBar.setProgress(i);
                    final int finalI = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtpercent.setText(finalI +"");
                         }
                    });

                }

            }
        });


        thread.start();

    }*/
    }
}