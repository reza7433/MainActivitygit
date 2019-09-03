package com.example;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class PermisionActivity extends AppCompatActivity {

    private static final int REQUEST_OPEN_GALLERY = 10001;
    private static final int PERMISION_REQUEST = 10002;
    ImageView imgPicture;
    Button btnChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permision);

        imgPicture = (ImageView) findViewById(R.id.img_permision);
        btnChoose = (Button) findViewById(R.id.btn_permision_gallery);


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                        ;
                    final Dialog dialog = new Dialog(PermisionActivity.this);
                    dialog.setContentView(R.layout.dialog);
                    TextView txtOk = dialog.findViewById(R.id.txt_dialog_ok);
                    txtOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISION_REQUEST);

                        }
                    });
                    dialog.show();

                } else {
                    creatFilePath();

                }

            }


        });

    }

    private void creatFilePath() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_OPEN_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_OPEN_GALLERY && requestCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            final String picturePath = cursor.getString(columnIndex);
            cursor.close();
            // Toast.makeText(PermisionActivity.this,picturePath , Toast.LENGTH_SHORT).show();
            imgPicture.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISION_REQUEST && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            //Toast.makeText(this, "permision granted", Toast.LENGTH_SHORT).show();
            creatFilePath();

        } else {
            Toast.makeText(this, "permision denied", Toast.LENGTH_SHORT).show();
        }
    }
}