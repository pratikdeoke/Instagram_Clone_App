package com.example.task3_loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    Button btn_turnOn, btn_getVisible, btn_paired_devices, btn_turnOff;
    ListView lv_paired_devices;
    ImageView img_bluetooth;
    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> bluetoothDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        ActivityCompat.requestPermissions(BluetoothActivity.this, new String[] {Manifest.permission.BLUETOOTH}, 990);

        btn_turnOn = findViewById(R.id.btn_bluetooth_turn_on);
        btn_turnOff = findViewById(R.id.btn_bluetooth_turn_off);
        btn_getVisible = findViewById(R.id.btn_bluetooth_get_visible);
        btn_paired_devices = findViewById(R.id.btn_bluetooth_paired_devices);
        lv_paired_devices = findViewById(R.id.lv_bluetooth_paired_devices);
        img_bluetooth = findViewById(R.id.img_bluetooth);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @SuppressLint("MissingPermission")
    public void turnOn(View view) {
        if (!bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 567);
            img_bluetooth.setImageResource(R.drawable.bluetooth_turn_on);
        } else {
            Toast.makeText(this, "Your Bluetooth is already ON", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    public void getVisible(View view) {
        if (bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(intent, 789);
            img_bluetooth.setImageResource(R.drawable.bluetooth_get_visible_vector);
        } else {
            Toast.makeText(this, "Please Turn On Your Bluetooth", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    public void pairedDevices(View view) {
        bluetoothDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();
        for (BluetoothDevice bd : bluetoothDevices) {
            list.add(bd.getName());
            ArrayAdapter adapter = new ArrayAdapter(BluetoothActivity.this, android.R.layout.simple_dropdown_item_1line, list);
            lv_paired_devices.setAdapter(adapter);
            lv_paired_devices.setBackgroundColor(Color.WHITE);
            img_bluetooth.setImageResource(R.drawable.bluetooth_paired_devices_vector);
        }
    }

    @SuppressLint("MissingPermission")
    public void turnOff(View view) {
        if (bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is Turned Off", Toast.LENGTH_SHORT).show();
            bluetoothAdapter.disable();
            img_bluetooth.setImageResource(R.drawable.bluetooth_turn_off);
        } else {
            Toast.makeText(this, "Your Bluetooth is already OFF", Toast.LENGTH_SHORT).show();
        }
    }
}