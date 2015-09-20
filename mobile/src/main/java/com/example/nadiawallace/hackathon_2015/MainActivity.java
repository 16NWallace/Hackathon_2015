package com.example.nadiawallace.hackathon_2015;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcel;
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

import watch.nudge.phonegesturelibrary.AbstractPhoneGestureActivity;

//Class that executes when you build and run the app
public class MainActivity extends AbstractPhoneGestureActivity  {


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
    public void onWindowClosed() {
        Toast.makeText(this,"Gesture window closed.",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTwist() {
        //Toast.makeText(this,"Just twist it",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSnap() {
        //Toast.makeText(this,"Feeling snappy!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent callServiceIntent = new Intent(this,CallService.class);
        callServiceIntent.putExtra("presets", presets);
        startService(callServiceIntent);
    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public void onFlick() {
        Toast.makeText(this,"Got a flick!",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onTiltX(float v) {

    }

    @Override
    public void onTilt(float v, float v1, float v2) {

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

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friend_phone_number = friend_phone_textbox.getText().toString();
                emergency_phone_1_number  = emergency_phone_1_textbox.getText().toString();
                emergency_phone_2_number  = emergency_phone_2_textbox.getText().toString();
                emergency_phone_3_number  = emergency_phone_3_textbox.getText().toString();
            }
        });

        Intent callServiceIntent = new Intent(this,CallService.class);
        Parcel contactParcel = Parcel.obtain();
        contactParcel.writeStringArray(new String[]{this.friend_phone_number,
                this.emergency_phone_1_number,
                this.emergency_phone_2_number,
                this.emergency_phone_3_number});
        ContactPresets userContacts = new ContactPresets(contactParcel);
        callServiceIntent.putExtra("presets", userContacts);
        startService(callServiceIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
