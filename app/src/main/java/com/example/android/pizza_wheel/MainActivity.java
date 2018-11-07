package com.example.android.pizza_wheel;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.pizza_wheel.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button roll_wheel;
    ImageView pizza_iv;
    TextView textView;

    Random random;
    int degree = 0, degree_old = 0;

    private static final float SLICE = 22.6f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rollWhell(View view){
        roll_wheel = findViewById(R.id.roll_button);
        pizza_iv = findViewById(R.id.pizza);
        textView = findViewById(R.id.text_view);
        random = new Random();
        degree_old = degree % 360;
        degree = random.nextInt(3600) + 720;
        RotateAnimation rotate = new RotateAnimation(degree_old, degree, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                textView.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText(currentSlice(360 - (degree % 360)));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        pizza_iv.startAnimation(rotate);

    }

    public void call (View view){
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:88005553535"));
        startActivity(call);
    }
    private String currentSlice(int degrees){
        String text = "";

        if (degrees >= (SLICE * 1) && degrees < (SLICE * 3)){
            text = "Пепперони";
        }
        if (degrees >= (SLICE * 3) && degrees < (SLICE * 5)){
            text = "Пицца с грибами";
        }
        if (degrees >= (SLICE * 5) && degrees < (SLICE * 7)){
            text = "Итальяно";
        }
        if (degrees >= (SLICE * 7) && degrees < (SLICE * 9)){
            text = "Вегетарианская";
        }
        if (degrees >= (SLICE * 9) && degrees < (SLICE * 11)){
            text = "Крабовая";
        }
        if (degrees >= (SLICE * 11) && degrees < (SLICE * 13)){
            text = "Гавайская";
        }
        if (degrees >= (SLICE * 13) && degrees < (SLICE * 15)){
            text = "Томатная";
        }
        if ((degrees >= (SLICE * 15) && degrees < SLICE * 360) || (degrees >= 0 && degrees < (SLICE * 1))) {
            text = "Пицца дня";
        }
        return (text);
    }
}
