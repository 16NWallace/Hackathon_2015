package com.example.nadiawallace.hackathon_2015;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.text.method.BaseKeyListener;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.widget.Button;

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
        ButtonKeyEvent bke = new ButtonKeyEvent(this);
        KeyListener powerButtonListener = new BaseKeyListener() {
            @Override
            public int getInputType() {
                return KeyEvent.KEYCODE_POWER;
            }
        };
        return super.onStartCommand(intent, flags, startId);
    }


    private void fillActionPhoneNumberMap(){
        actionPhoneNumberMap = new HashMap<>();
        actionPhoneNumberMap.put(PossibleAction.CALL_POLICE, Arrays.asList("911"));
        actionPhoneNumberMap.put(PossibleAction.EMERGENCY_CALL, contactPresets.getEmergencyContacts());
        actionPhoneNumberMap.put(PossibleAction.EMERGENCY_TEXT, contactPresets.getEmergencyContacts());
        actionPhoneNumberMap.put(PossibleAction.PRESENT_CALL, contactPresets.getPresentContacts());
        actionPhoneNumberMap.put(PossibleAction.PRESENT_TEXT, contactPresets.getPresentContacts());
    }

    //Helper functions for Intent passing
    protected void makePhoneCall(){
        //Intent callIntent = new Intent(this, Intent.);
    }

    private void sendTextMessage(PossibleAction action, String msg) {
        List<String> numbersToCall = actionPhoneNumberMap.get(action);
        for (String contact : numbersToCall) {
            Uri uri = Uri.parse("smsto:" + contact);
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            it.putExtra("sms_body", "The SMS text");
            startActivity(it);
        }
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
        sendTextMessage(PossibleAction.PRESENT_TEXT,"msg");
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
