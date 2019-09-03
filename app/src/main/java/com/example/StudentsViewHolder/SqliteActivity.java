package com.example.StudentsViewHolder;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.R;

public class SqliteActivity extends AppCompatActivity {
    EditText edtNAME, edtFamily, edtField;
    Button btnadd;
    MyDatabase myDatabase;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);


        setupviews();
    }

    private void setupviews() {
        myDatabase = new MyDatabase(getApplicationContext());
        edtNAME = (EditText) findViewById(R.id.edt_sqlite_name);
        edtFamily = (EditText) findViewById(R.id.edt_sqlite_family);
        edtField = (EditText) findViewById(R.id.edt_sqlite_field);
        btnadd = (Button) findViewById(R.id.btn_sqlite_add);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id = myDatabase.addData(edtNAME.getText().toString(), edtFamily.getText().toString(), edtField.getText().toString());
                if (id > 0) {
                    Toast.makeText(SqliteActivity.this, "اطلاعات با موفقیت ذخیره شد", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(SqliteActivity.this, "مشکلی در اطلاعات رخ داده است", Toast.LENGTH_SHORT).show();
                }
            }


        });


    }
}