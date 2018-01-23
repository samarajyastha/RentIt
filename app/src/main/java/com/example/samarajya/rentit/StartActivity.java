package com.example.samarajya.rentit;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

/**
 * Created by Samarajya on 11/30/2017.
 */

public class StartActivity extends AppCompatActivity  {

    ImageView imageView;
    Animation animation;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        imageView=findViewById(R.id.app_icon);
        animation= AnimationUtils.loadAnimation(this,R.anim.startup);
        imageView.startAnimation(animation);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3500);


    }



}
