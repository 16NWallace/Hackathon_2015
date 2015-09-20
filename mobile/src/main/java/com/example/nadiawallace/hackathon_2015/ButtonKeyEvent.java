package com.example.nadiawallace.hackathon_2015;

import android.view.KeyEvent;


/**
 * Created by kccandon on 9/19/15.
 */
public class ButtonKeyEvent implements KeyEvent.Callback{
    public static final int MULTIPLE_BUTTON = 3;
    private final CallService svc;

    public ButtonKeyEvent(CallService svc){
        this.svc = svc;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        if(count>=MULTIPLE_BUTTON && keyCode == KeyEvent.KEYCODE_POWER) {
            svc.makePhoneCall(PossibleAction.CALL_POLICE);
            return true;
        } else {
            return false;
        }

    }



}
