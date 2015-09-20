package com.example.nadiawallace.hackathon_2015;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;

import android.content.IntentFilter;

import android.os.Parcel;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

import watch.nudge.phonegesturelibrary.AbstractPhoneGestureActivity;
import watch.nudge.phonegesturelibrary.AbstractPhoneGestureService;

//Class that executes when you build and run the app
public class MainActivity extends Activity {

    Context ctx;
    Button save_button;
    EditText friend_phone_textbox;
    String friend_phone_number;
    EditText emergency_phone_1_textbox;
    String emergency_phone_1_number;
    EditText emergency_phone_2_textbox;
    String emergency_phone_2_number;
    EditText emergency_phone_3_textbox;
    String emergency_phone_3_number;


    private ContactPresets presets;


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        save_button = (Button) findViewById(R.id.savebutton_id);
        friend_phone_textbox  = (EditText) findViewById(R.id.friendPhone_id);
        emergency_phone_1_textbox = (EditText) findViewById(R.id.emergencyPhone1_id);
        emergency_phone_2_textbox = (EditText) findViewById(R.id.emergencyPhone3_id);
        emergency_phone_3_textbox = (EditText) findViewById(R.id.emergencyPhone3_id);


        ctx = this.getApplicationContext();


        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friend_phone_number = friend_phone_textbox.getText().toString();
                emergency_phone_1_number = emergency_phone_1_textbox.getText().toString();
                emergency_phone_2_number = emergency_phone_2_textbox.getText().toString();
                emergency_phone_3_number = emergency_phone_3_textbox.getText().toString();

//                Parcel contactParcel = Parcel.obtain();
//                contactParcel.writeStringArray(new String[]{friend_phone_number,
//                        emergency_phone_1_number,
//                        emergency_phone_2_number,
//                        emergency_phone_3_number});

                //ContactPresets userContacts = new ContactPresets(contactParcel);
                final Intent callServiceIntent = new Intent(ctx, CallService.class);
                callServiceIntent.putStringArrayListExtra("phone_list", new ArrayList<String>(Arrays.asList(friend_phone_number,
                        emergency_phone_1_number,
                        emergency_phone_2_number,
                        emergency_phone_3_number)));

                startService(callServiceIntent);



                Toast.makeText(ctx,"Got it! You're all set :)",Toast.LENGTH_LONG).show();

            }
        });




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        //return super.onOptionsItemSelected(item);
        return true;
    }



    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }
}
