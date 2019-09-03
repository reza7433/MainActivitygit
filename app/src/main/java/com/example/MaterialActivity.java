package com.example;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.StudentsViewHolder.Students;
import com.example.StudentsViewHolder.StudentsAdapter.StudentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Students> studentsList;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentsList = new ArrayList<>();
        setContentView(R.layout.activity_material);
        recyclerView = (RecyclerView) findViewById(R.id.rv_material_testlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_material);
        navigationView = (NavigationView) findViewById(R.id.navigation_material);
        imgMenu = (ImageView) findViewById(R.id.img_material_menu);


        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });


        for (int k = 0; k < 20; k++) {

            Students students = new Students();
            students.setId(k);
            students.setName("name" + k);
            students.setFamily("family" + k);
            students.setField("android" + k);


            studentsList.add(students);

        }
        recyclerView.setAdapter(new StudentAdapter(this, studentsList));

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);

        } else {

            super.onBackPressed();
        }

    }
}
