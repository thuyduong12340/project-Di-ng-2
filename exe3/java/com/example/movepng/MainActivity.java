package com.example.movepng;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgQLTH, imgChao, imgLogo;
    Animation runingChupt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgQLTH = findViewById(R.id.imgQLTH);
        //imgLogo = findViewById(R.id.imgLogo);
        imgChao = findViewById(R.id.imgChao);
        runingChupt = AnimationUtils.loadAnimation(MainActivity.this,R.anim.logo);
        imgChao.startAnimation(runingChupt);

        final AnimationDrawable runingcat = (AnimationDrawable) imgQLTH.getDrawable();
        runingcat.start();
        imgChao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runingChupt = AnimationUtils.loadAnimation(MainActivity.this, R.anim.chao);
                imgChao.startAnimation(runingChupt);

            }
        });


//        final Animation runingLogo = AnimationUtils.loadAnimation(MainActivity.this,R.anim.logo);
//        imgChuot.startAnimation(runingLogo);


    }

}
