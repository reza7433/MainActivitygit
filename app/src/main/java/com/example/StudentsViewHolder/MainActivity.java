package com.example.StudentsViewHolder;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ActivityForResult;
import com.example.ActivityFragment;
import com.example.BroadCastActivity;
import com.example.MaterialActivity;
import com.example.PermisionActivity;
import com.example.R;
import com.example.TabLayoutActivity;
import com.example.mainactivity.ThreadActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1001;
    Button btnSqlite;
    Toolbar toolbar;
    Button button;
    Button btnrecycler;
    Button btnActivityResult;
    Button btnFragment;
    Button btnlayout;
    Button btnThread;
    Button btnPermision;
    Button btnBroadcast;
    Button btnmaterial;
    public static final String TOOLBARTAG = "title";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThread = (Button) findViewById(R.id.btn_main_thread);
        btnlayout = (Button) findViewById(R.id.btn_main_tablayout);
        btnrecycler = (Button) findViewById(R.id.recyclerAndDB);
        btnSqlite = (Button) findViewById(R.id.btn_main_sqlite);
        btnActivityResult = (Button) findViewById(R.id.btn_main_ActivityForResult);
        btnFragment = (Button) findViewById(R.id.btn_main_Fragment);
        btnlayout = (Button) findViewById(R.id.btn_main_tablayout);
        btnPermision = (Button) findViewById(R.id.btn_main_permision);
        btnBroadcast = (Button) findViewById(R.id.btn_main_broadcast);
        btnmaterial = (Button) findViewById(R.id.btn_main_materialDesign);


        btnrecycler.setOnClickListener
                (new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                        intent.putExtra(TOOLBARTAG, "ریسایکلرویو");
                        startActivity(intent);
                    }

                });

        btnSqlite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SqliteActivity.class);
                intent.putExtra(TOOLBARTAG, "دیتابیس");
                startActivity(intent);
            }

        });

        btnActivityResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityForResult.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });


        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityFragment.class);
                startActivity(intent);

            }
        });


        btnlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, TabLayoutActivity.class);
                startActivity(intent);


            }
        });


        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
                startActivity(intent);


            }
        });

        btnPermision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PermisionActivity.class);
                startActivity(intent);

            }
        });


        btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BroadCastActivity.class);
                startActivity(intent);
            }
        });

        btnmaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MaterialActivity.class);
                startActivity(intent);
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Toast.makeText(this, data.getExtras().getString("result"), Toast.LENGTH_SHORT).show();
        }
    }
}
