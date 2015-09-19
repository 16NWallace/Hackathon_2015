package com.example.nadiawallace.hackathon_2015;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import watch.nudge.phonegesturelibrary.AbstractPhoneGestureService;

public class CallService extends AbstractPhoneGestureService {
    public CallService() {
    }

    @Override
    public void onSnap() {

    }

    @Override
    public void onFlick() {

    }

    @Override
    public void onTwist() {

    }

    @Override
    public void onTiltX(float v) {

    }

    @Override
    public void onTilt(float v, float v1, float v2) {

    }

    @Override
    public void onWindowClosed() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
