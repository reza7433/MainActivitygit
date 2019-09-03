package com.example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.Fragment.IntroFirst;
import com.example.Fragment.IntroSecond;
import com.example.StudentsViewHolder.MainActivity;
import com.github.paolorotolo.appintro.AppIntro;

public class IntroActivity extends AppIntro {


    private static final String INTROSEEN = "intro_seen";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("clicksite", MODE_PRIVATE);
        Boolean introseen = sharedPreferences.getBoolean(INTROSEEN, false);
        if (introseen) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        }


        addSlide(new IntroFirst());
        addSlide(new IntroSecond());
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        showSkipButton(true);
        setProgressButtonEnabled(true);


    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        skipintro();
        startActivity(intent);
        finish();
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        skipintro();
        startActivity(intent);
        finish();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    private void skipintro() {
        sharedPreferences = getSharedPreferences("clicksite", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(INTROSEEN, true);
        editor.apply();


    }
}
