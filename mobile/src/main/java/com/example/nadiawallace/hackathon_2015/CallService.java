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

    private static final String DEFAULT_TEXT_MESSAGE = "I use WristWatch. This an automated message" +
            "that I have signaled the app that I am uncomfortable and would like help";

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
        actionPhoneNumberMap.put(PossibleAction.CALL_POLICE, Arrays.asList("555"));
        actionPhoneNumberMap.put(PossibleAction.EMERGENCY_TEXT, contactPresets.getEmergencyContacts());
        actionPhoneNumberMap.put(PossibleAction.PRESENT_TEXT, contactPresets.getPresentContacts());
    }

    //Helper functions for Intent passing
    private void makePhoneCall(PossibleAction action){
        List<String> numbersToCall = actionPhoneNumberMap.get(action);
        for(String phoneNumber: numbersToCall){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel: "+phoneNumber));
            startActivity(callIntent);
        }
    }

    private void sendTextMessage(PossibleAction action, String msg){
        List<String> numbersToCall = actionPhoneNumberMap.get(action);
        for(String contact: numbersToCall){
            //send text message
        }
    }

    private void sendLocation(){
        LocationHandler locationHandler = new LocationHandler();
        String loc = locationHandler.getLocationLink();
        sendTextMessage(PossibleAction.EMERGENCY_TEXT, String.format("%s My location is: %s",
                                                                     DEFAULT_TEXT_MESSAGE,loc));
    }

    //Callback functions for gestures
    //Twist + snap
    @Override
    public void onSnap() {
        sendLocation();
    }

    //Twist + flick
    @Override
    public void onFlick() {
        sendTextMessage(PossibleAction.PRESENT_TEXT, DEFAULT_TEXT_MESSAGE);
    }

    //Two twists
    @Override
    public void onTwist() {
        makePhoneCall(PossibleAction.CALL_POLICE);
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
