package com.toni.transiciones;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseClass extends AppCompatActivity {
    //constante para indicar el tipo de transicion
    private static final String TRANSITION_TYPE = "Transition Type";

    //constantes para compartir las transiciones
    private static final String ANDROID_TRANSITION = "switchAndroid";
    private static final String BLUE_TRANSITION = "switchBlue";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //leer el tipo de transicion del intent
        if(getIntent().hasExtra(TRANSITION_TYPE)){
            switch (getIntent().getStringExtra(TRANSITION_TYPE)){
                case "Explode":
                    getWindow().setEnterTransition(new Explode());
                    break;
                case "Slide":
                    getWindow().setEnterTransition(new Slide());
                    break;
                case "Fade":
                    getWindow().setEnterTransition(new Fade());
                    break;
                default:
                    break;
            }
        }
    }

    //relanza la activity con explode
    protected void explodeTransition(final Context context, ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, context.getClass());
                intent.putExtra(TRANSITION_TYPE, "Explode");
                getWindow().setExitTransition(new Explode());
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity)context).toBundle());
            }
        });
    }

    //relanza la actividad con fade
    protected void fadeTransition(final Context context, ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                // Relaunch the activity with the transition information.
                Intent intent = new Intent(context,context.getClass());
                intent.putExtra(TRANSITION_TYPE,"Fade");
                getWindow().setExitTransition(new Fade());
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation(
                                (Activity)context).toBundle());
            }
        });
    }

    //animacion de 360 grados
    protected void rotateView(ImageView imageView){
        // Create the object animator with desired options.
        final ObjectAnimator animator = ObjectAnimator.ofFloat(
                imageView, View.ROTATION, 0f, 360f);
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start it when the view is clicked.
                animator.start();
            }
        });
    }

    //transicion de elementos compartidos entre dos actividades con dos elementos comunes
    protected void switchAnimation(final ImageView androidImage,
                                   final ImageView otherImage,
                                   final Intent intent,
                                   final Context context){
        androidImage.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                // Set the transition details and start the second activity.
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity)context,
                                Pair.create((View)androidImage,
                                        ANDROID_TRANSITION),
                                Pair.create((View)otherImage,
                                        BLUE_TRANSITION));

                startActivity(intent,options.toBundle());
            }
        });
    }


}

