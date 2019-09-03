package com.example.StudentsViewHolder;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.R;
import com.example.StudentsViewHolder.StudentsAdapter.StudentAdapter;

import java.util.ArrayList;
import java.util.List;


public class RecyclerActivity extends AppCompatActivity {

    //  TextView txtToolbarTitle;
    RecyclerView recyclerView;
    //  View toolbarview;
    List<Students> studentsList;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        setupView();

        // setToolbarTitle();

        // genarateFackData();

        getDataFromSqlite();

        Students students = new Students();
        students.setAds(true);
        studentsList.add(0, students);
        studentsList.add(5, students);
    }

    private void getDataFromSqlite() {
        Cursor cursor = myDatabase.getData();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            Students students = new Students();
            students.setId(cursor.getInt(0));
            students.setName(cursor.getString(1));
            students.setFamily(cursor.getString(2));
            students.setField(cursor.getString(3));
      /*  int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String family = cursor.getString(2);
        String field = cursor.getString(3);

    }*/

            studentsList.add(students);
        }
        recyclerView.setAdapter(new StudentAdapter(RecyclerActivity.this, studentsList));
    }

/*    private void genarateFackData() {

        studentsList=new ArrayList<>();

        for (int i=0; i<10; i++){
            Students students=new Students();
            students.setId(i);
            students.setName("student" +i);
            students.setFamily("test family" +i);
            students.setField("android developer" +i);

            studentsList.add(students);

        }*/

    // recyclerView.setAdapter(new StudentsAdapter(RecyclerActivity.this,studentsList));

/*  +

    private void setToolbarTitle() {
        //     String toolbarTitle = getIntent().getExtras().getString(MainActivity.TOOLBARTAG);
        //   txtToolbarTitle.setText(toolbarTitle);
    }
*/

    private void setupView() {
        myDatabase = new MyDatabase(getApplicationContext());
        studentsList = new ArrayList<>();

        //  txtToolbarTitle = (TextView) findViewById(R.id.txt_toolbar_title);
        recyclerView = (RecyclerView) findViewById(R.id.rv_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this));
        // toolbarview = (View) findViewById(R.id.view_main_toolbar);
        // txtToolbarTitle = (TextView) toolbarview.findViewById(R.id.txt_toolbar_title);


    }


}