package com.gabitosoft.bluetoothmeasure;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Gabriel Delgado on 29-05-15.
 */
public class FragmentConfiguration extends Fragment {

    private BluetoothAdapter bluetooth;
    private TextView status;
    private ToggleButton toggleButton;
    //private Context context;

    private int REQUEST_ENABLE_BT = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.configuration_layout, container, false);

//        bluetooth = BluetoothAdapter.getDefaultAdapter();
//        status = (TextView)findViewById(R.id.textView);
//
//        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
//
//        toggleButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        try {
//
//                            if (bluetooth != null) {
//
//                                if(toggleButton.isChecked()) {
//
//                                    //Toast.makeText(context, "CHECKED", Toast.LENGTH_SHORT).show();
//                                    if (!bluetooth.isEnabled()) {
//
//                                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//                                        String mydeviceaddress = bluetooth.getAddress();
//                                        String mydevicename = bluetooth.getName();
//                                        status.setText(mydevicename + " : " + mydeviceaddress);
//                                    }
//                                } else {
//
//                                    Toast.makeText(context, "UNCHECKED", Toast.LENGTH_SHORT).show();
//                                    if (bluetooth.isEnabled()) {
//                                        status.setText("Bluetooth is not enabled");
//                                        bluetooth.disable();
//                                    }
//                                }
//                            } else {
//
//                                //Toast.makeText(context, "Bluetooth Adapter Undefined", Toast.LENGTH_LONG).show();
//                            }
//                        } catch(Exception ex) {
//
//                            //Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });

        return rootView;
    }
}
