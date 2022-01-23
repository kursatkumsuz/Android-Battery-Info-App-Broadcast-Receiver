package com.kursatdrhistoryapp.batteryinfoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView chargetext, healthtext , pluggedtext , statustext , presenttext, temptext ,techtext, volttext;
ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volttext = findViewById(R.id.voltText);
        temptext = findViewById(R.id.tempText);
        techtext = findViewById(R.id.tecText);
        healthtext = findViewById(R.id.healthText);
        pluggedtext = findViewById(R.id.pluggedText);
        statustext = findViewById(R.id.statusText);
        presenttext = findViewById(R.id.presentText);
        progressbar = findViewById(R.id.progressBar);
        chargetext = findViewById(R.id.chargeText);
        registerReceiver(this.batteryInfoReceiver , new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int batteryHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH , 0);
            int batteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL , 0);
            int batteryPlugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED , 0);
            int batteryStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            boolean batteryPresent = intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
            String batteryTech = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            int batteryTempt = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE , 0);
            int batteryVolt = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE , 0);

            //progressbar
            chargetext.setText("Charge Level:" + "%" + batteryLevel);
            progressbar.setProgress(batteryLevel);

            //setTexts
            healthtext.setText("Health" + "\n" + batteryHealth);
            pluggedtext.setText("Plugged" + "\n" + batteryPlugged);
            statustext.setText("Status" + "\n" + batteryStatus);
            presenttext.setText("Present" + "\n" + batteryPresent);
            temptext.setText("Temperature" + "\n" + batteryTempt);
            techtext.setText("Technology" + "\n" + batteryTech);
            volttext.setText("Voltage" + "\n" + batteryVolt);
        }
    };
}