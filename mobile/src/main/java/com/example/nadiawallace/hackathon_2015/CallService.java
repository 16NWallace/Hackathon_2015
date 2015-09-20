package com.example.nadiawallace.hackathon_2015;

import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import watch.nudge.phonegesturelibrary.AbstractPhoneGestureService;

public class CallService extends AbstractPhoneGestureService {

    //ContactPresets extends/implements Parcelable
    private ContactPresets contactPresets;
    private Map<PossibleAction, List<String>> actionPhoneNumberMap;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        contactPresets = intent.getExtras().getParcelable("key");
        fillActionPhoneNumberMap();
        return super.onStartCommand(intent, flags, startId);
    }

    private void fillActionPhoneNumberMap(){
        actionPhoneNumberMap = new HashMap<>();
        actionPhoneNumberMap.put(PossibleAction.CALL_POLICE, new ArrayList<String>(Arrays.asList("911")));
        actionPhoneNumberMap.put(PossibleAction.EMERGENCY_CALL, contactPresets.getEmergencyContacts());
        actionPhoneNumberMap.put(PossibleAction.EMERGENCY_TEXT, contactPresets.getEmergencyContacts());
        actionPhoneNumberMap.put(PossibleAction.PRESENT_CALL, contactPresets.getPresentContacts());
        actionPhoneNumberMap.put(PossibleAction.PRESENT_TEXT, contactPresets.getPresentContacts());
    }

    //Helper functions for Intent passing
    private void makePhoneCall(){
        //Intent callIntent = new Intent(this, Intent.);
    }

    private void sendTextMessage(){

    }

    private void sendLocation(){
        //LocationHandler locationHandler = new LocationHandler();
        //locationHandler.handle();
    }

    //Callback functions for gestures
    @Override
    public void onSnap() {
        makePhoneCall();
    }

    @Override
    public void onFlick() {
        sendTextMessage();
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
