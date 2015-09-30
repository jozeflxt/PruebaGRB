package com.grability.myapplication.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.MotionEvent;

import com.grability.myapplication.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Touch;

/**
 * Created by Carlos  Muñoz on 22/09/2015.
 */
@EActivity(R.layout.splash)
public class SplashScreen extends Activity {
    private Thread mSplashThread;

    @AfterViews
    void CrearHilo() {
        final SplashScreen sPlashScreen = this;
        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(5000);
                    }
                } catch (InterruptedException ex) {
                }

                finish();
                Intent intent = new Intent();
                intent.setClass(sPlashScreen, MainActivity_.class);
                startActivity(intent);
            }
        };
        mSplashThread.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        if (evt.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (mSplashThread) {
                mSplashThread.notifyAll();
            }
        }
        return true;
    }

}
