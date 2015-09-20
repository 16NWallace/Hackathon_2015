package com.example.nadiawallace.hackathon_2015;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.pm.LabeledIntent;

import android.content.IntentFilter;

import android.net.Uri;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.text.method.BaseKeyListener;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import watch.nudge.phonegesturelibrary.AbstractPhoneGestureService;

public class CallService extends AbstractPhoneGestureService {

    //ContactPresets extends/implements Parcelable
    private List<String> contactPresets;
    private List<String> emergencyContacts;
    private List<String> presentContacts;

    private Map<PossibleAction, List<String>> actionPhoneNumberMap;

    private static final String DEFAULT_TEXT_MESSAGE = "I use WristWatch. This an automated message" +
            "that I have signaled the app that I am uncomfortable and would like help";

//    private BroadcastReceiver powerButtonReceiver= new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals(Intent.EXTRA_KEY_EVENT)) {
//                int eventCode = intent.getIntExtra(Intent.EXTRA_KEY_EVENT, 0);
//                if(eventCode == KeyEvent.KEYCODE_POWER){
//                    makePhoneCall(PossibleAction.CALL_POLICE);
//                }
//            }
//        }
//    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        contactPresets = intent.getExtras().getStringArrayList("phone_list");
        presentContacts = new ArrayList<>();
        emergencyContacts = new ArrayList<>();


        presentContacts.add(contactPresets.get(0));
        startGestureOnWatch(new Intent());



        emergencyContacts.add(contactPresets.get(1));
        emergencyContacts.add(contactPresets.get(2));
        emergencyContacts.add(contactPresets.get(3));


        fillActionPhoneNumberMap();
//        IntentFilter filter = new IntentFilter(Intent.EXTRA_KEY_EVENT);
//        registerReceiver(powerButtonReceiver, filter);
        return super.onStartCommand(intent, flags, startId);
    }


    private void fillActionPhoneNumberMap(){
        actionPhoneNumberMap = new HashMap<>();
        actionPhoneNumberMap.put(PossibleAction.CALL_POLICE, Arrays.asList("555"));
        actionPhoneNumberMap.put(PossibleAction.EMERGENCY_TEXT, emergencyContacts);
        actionPhoneNumberMap.put(PossibleAction.PRESENT_TEXT, presentContacts);
    }

    //Helper functions for Intent passing


    private void sendTextMessage(PossibleAction action, String msg) {
        if (msg == null) {
            msg=DEFAULT_TEXT_MESSAGE;
        }
        List<String> numbersToCall = actionPhoneNumberMap.get(action);
        for (String contact : numbersToCall) {
            Uri uri = Uri.parse("smsto:" + contact);
            SmsManager.getDefault().sendTextMessage(contact, null, msg, null, null);
//            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
//            it.putExtra("sms_body", DEFAULT_TEXT_MESSAGE);
//            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(it);
        }
    }

    protected void makePhoneCall(PossibleAction action){
        List<String> numbersToCall = actionPhoneNumberMap.get(action);
        for(String phoneNumber: numbersToCall){
            Intent callIntent = new Intent(Intent.ACTION_CALL);

            callIntent.setData(Uri.parse("tel: "+phoneNumber));
            callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(callIntent);
        }
    }


    private void sendLocation(){
        Context ctx = getApplicationContext();
//        LocationHandler locationHandler = new LocationHandler(ctx);
//        String loc = locationHandler.getLocationLink();

        //PLACEHOLDER LOCATION
        String loc = "https://www.google.com/maps/place/42.3593032,+-71.0935221/";
        System.out.println(loc);
        sendTextMessage(PossibleAction.EMERGENCY_TEXT, String.format("%s My location is: %s",
                DEFAULT_TEXT_MESSAGE,loc));
    }

    //Callback functions for gestures
    //Twist + snap
    @Override
    public void onSnap() {

        Toast.makeText(this, "snap!", Toast.LENGTH_LONG).show();
        //sendLocation();
        String new_text = String.format("%s My location is %s", DEFAULT_TEXT_MESSAGE, " https://www.google.com/maps/place/42.3593032,+-71.0935221/");
        sendTextMessage(PossibleAction.EMERGENCY_TEXT, new_text);

    }

    //Twist + flick
    @Override
    public void onFlick() {

        Toast.makeText(this, "Flick!", Toast.LENGTH_LONG).show();
        sendTextMessage(PossibleAction.PRESENT_TEXT, DEFAULT_TEXT_MESSAGE);
    }

    //Two twists
    @Override
    public void onTwist() {
        makePhoneCall(PossibleAction.CALL_POLICE);
        Toast.makeText(this, "Twistin' the night away", Toast.LENGTH_LONG).show();
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
