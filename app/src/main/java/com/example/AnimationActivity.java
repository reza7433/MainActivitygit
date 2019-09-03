package com.example;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class AnimationActivity extends AppCompatActivity {

    Button btnAnimation, btnxmlAnimation;
    ImageView logo;
    ConstraintLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);


        btnAnimation = (Button) findViewById(R.id.btn_animation);
        btnxmlAnimation = (Button) findViewById(R.id.btn_animation_xml);
        logo = (ImageView) findViewById(R.id.img_animation);
        parent = (ConstraintLayout) findViewById(R.id.parent);


        btnAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // alphaAnimation();
                //translateAnimation();
                // scaleAnimation();
                //rotateAnimation();
//                setAnimation();
                customAnimation();
            }
        });


        btnxmlAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // alphaXmlAnimation();
                translateXmlAnimation();
            }
        });

    }


    private void alphaAnimation() {

        AlphaAnimation alphaAnimation = new AlphaAnimation(2f, 0f);
        alphaAnimation.setFillAfter(false);
        alphaAnimation.setDuration(3000);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        alphaAnimation.setRepeatCount(3);
        logo.startAnimation(alphaAnimation);

    }

    private void translateAnimation() {
        //  TranslateAnimation translateAnimation=new TranslateAnimation(0,0,0,500);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0
                , Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_PARENT, 1);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(new BounceInterpolator());
        translateAnimation.setRepeatCount(5);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.setY(500);
                logo.setX(0);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void alphaXmlAnimation() {
        AlphaAnimation alphaAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        logo.startAnimation(alphaAnimation);

    }

    private void translateXmlAnimation() {
        TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
        logo.startAnimation(translateAnimation);
    }

    private void scaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(5);
        logo.startAnimation(scaleAnimation);
    }

    private void rotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(false);
        rotateAnimation.setDuration(1000);
        logo.startAnimation(rotateAnimation);
    }

    private void setAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setRepeatCount(2);
        scaleAnimation.setDuration(5000);


        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(false);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(3);
        logo.startAnimation(rotateAnimation);


        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        logo.startAnimation(animationSet);

    }

    private void customAnimation() {
        int colorFrom = getResources().getColor(R.color.colorPrimary);
        int colorTo = getResources().getColor(R.color.colorAccent);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        valueAnimator.setDuration(1000); // milliseconds
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                parent.setBackgroundColor((int) animator.getAnimatedValue());

            }
        });

        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

}

