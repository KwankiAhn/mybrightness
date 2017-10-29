package com.example.kwankiahn.mybrightness;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "MYBRIGHTNESS";
    private Context context;
    private ButtonListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() Start");
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final BrightnessDriverNotification notification = new BrightnessDriverNotification(context);
        final ToggleButton toggle = (ToggleButton)findViewById(R.id.toggleButton);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle.isChecked()) {
                    notification.start(context);
                } else {
                    notification.stop(context);
                    //getApplicationContext().unregisterReceiver(listener);
                }
            }
        });
        //listener = new ButtonListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() Start");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() Start");
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() Start");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() Start");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() Start");
        /* I would not unregister with exiting app (even on destroy),
        * finish operation by user explicitly (ie. exit button) would delete all */
        super.onDestroy();
    }
}
