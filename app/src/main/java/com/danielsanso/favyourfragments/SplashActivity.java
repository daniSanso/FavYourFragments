package com.danielsanso.favyourfragments;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class SplashActivity extends AppCompatActivity {
    private GifImageView gifImageView;
    private ProgressBar progressBar;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // getSupportActionBar().hide();//quita el action bar
        gifImageView = (GifImageView) findViewById(R.id.gifimageview);
        progressBar = (ProgressBar)findViewById(R.id.progressBar) ;
        progressBar.setVisibility(progressBar.VISIBLE);


        try{
            InputStream inputStream =getAssets().open("SplashF.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch (IOException ex)
        {

        }
        //esperamos 5 segundos?

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, login.class));
                SplashActivity.this.finish();
            }
        },4000); //Estos son 5 segundos
    }


    //Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
    //img.startAnimation(anim);
}
