package com.example.bt_call_audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity<MyActivity> extends AppCompatActivity {
    private Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sw = (Switch)findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (bluetoothAdapter != null) {
                    boolean enable = bluetoothAdapter.isEnabled();
                    if (isChecked) {
                        if (enable == false) {
                            if (bluetoothAdapter.enable() == false) {
                                sw.setChecked(false);
                            }else{
                                //BT is enabled lets show discovered devices
                                if(bluetoothAdapter.isDiscovering() == false){
                                    Intent sendIntent = new Intent();
                                    sendIntent.setAction(Intent.ACTION_SEND);
                                    sendIntent.setType("text/plain");
                                    sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);

                                    startActivity(sendIntent);
                                }
                            }
                        }
                    } else {
                        if (enable == true) {
                            boolean disable = bluetoothAdapter.disable();
                            if (!disable) {
                                sw.setChecked(true);
                            }
                        }
                    }
                } else {
                    sw.setChecked(false);
                }
            }
        });
    }
}
