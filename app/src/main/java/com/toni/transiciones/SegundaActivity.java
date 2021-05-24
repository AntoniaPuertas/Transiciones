package com.toni.transiciones;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

public class SegundaActivity extends BaseClass {
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        explodeTransition(this, img1);
        fadeTransition(this, img2);
        rotateView(img4);
        switchAnimation(img2,img3,new Intent(
                this, SegundaActivity.class),this);
    }
}