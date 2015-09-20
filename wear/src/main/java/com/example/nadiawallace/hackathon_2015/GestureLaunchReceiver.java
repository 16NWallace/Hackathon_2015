package com.example.nadiawallace.hackathon_2015;

import watch.nudge.gesturelibrary.AppControllerReceiverService;

public class GestureLaunchReceiver extends AppControllerReceiverService {
    @Override
    protected Class getWatchActivityClass() {
        //return watch.nudge.gesturelibrary.DefaultWindowedGestureActivity.class;
        return watch.nudge.gesturelibrary.DefaultWindowedGestureActivity.class;

    }
}
