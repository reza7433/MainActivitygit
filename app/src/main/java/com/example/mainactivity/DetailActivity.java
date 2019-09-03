package com.example.mainactivity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.R;
import com.example.StudentsViewHolder.MyDatabase;

public class DetailActivity extends AppCompatActivity {
    TextView txtname, txtfamily, txtfield;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        setupvies();
        getmyintent();
        getDataFromDB();
    }

    private void getDataFromDB() {
        Cursor cursor = myDatabase.getSpecialData(25);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            txtname.setText(cursor.getString(1));
            txtfamily.setText(cursor.getString(2));
            txtfield.setText(cursor.getString(3));


        }
    }

    private void getmyintent() {

        String name = getIntent().getExtras().getString("name");
        String family = getIntent().getExtras().getString("family");
        String field = getIntent().getExtras().getString("field");

        txtname.setText(name);
        txtfamily.setText(family);
        txtfield.setText(field);

    }

    private void setupvies() {

        myDatabase = new MyDatabase(getApplicationContext());
        txtname = (TextView) findViewById(R.id.txt_detail_name);
        txtfamily = (TextView) findViewById(R.id.txt_detail_family);
        txtfield = (TextView) findViewById(R.id.txt_detail_field);


    }
}
