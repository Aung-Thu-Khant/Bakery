package com.example.butterbloom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {
    public static int SplashScreen = 3000;
    Animation lToRAnim;
    ImageView SplashImg;
    TextView txtWelcome;
    LottieAnimationView SplashAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        lToRAnim = AnimationUtils.loadAnimation(this,R.anim.lefttoright);

        SplashImg = findViewById(R.id.img_logo);
        txtWelcome = findViewById(R.id.txt_welcome);
        SplashAnim = findViewById(R.id.Anim_Splash);

        SplashImg.setAnimation(lToRAnim);
        txtWelcome.setAnimation(lToRAnim);
        SplashAnim.setAnimation(lToRAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SplashScreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}