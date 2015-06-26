package com.gabitosoft.bluetoothmeasure;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import Conexion.Bluetooth;

/**
 * Created by Gabriel Delgado on 29-05-15.
 */
public class FragmentConfiguration extends Fragment {

    private ToggleButton toggleButton;
    private TextView textViewDevice;
    private ListView listView;
    private ArrayAdapter listAdapter;
    private Button buttonSearch;
    private Bluetooth bluetooth;

    private final int REQUEST_ENABLE_BT = 1;
    private final int REQUEST_CONNECT_DEVICE = 2;
    private final int RESULT_ERROR = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = null;
        try {

            //Initializing visual components
            rootView = inflater.inflate(R.layout.configuration_layout, container, false);
            textViewDevice = (TextView)rootView.findViewById(R.id.textView);
            toggleButton = (ToggleButton)rootView.findViewById(R.id.toggleButton);
            listView = (ListView)rootView.findViewById(R.id.listView);
            listAdapter = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, new ArrayList<String>());
            listView.setAdapter(listAdapter);
            buttonSearch = (Button)rootView.findViewById(R.id.button);
            buttonSearch.setEnabled(false);

            // Initializing bluetooth components
            bluetooth = new Bluetooth(BluetoothAdapter.getDefaultAdapter());

            final Context context = rootView.getContext();

            if (bluetooth.isBTTurnedOn()) {
                textViewDevice.setText(bluetooth.getBTDeviceInf());
                listAdapter.addAll(bluetooth.getPairedDevices());
                toggleButton.setChecked(true);
                buttonSearch.setEnabled(true);
            }

            toggleButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if(toggleButton.isChecked()) {

                                if (!bluetooth.isBTTurnedOn()) {

                                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                                }
                            } else {

                                bluetooth.turnOffBT();
                                textViewDevice.setText("");
                                listAdapter.clear();
                                buttonSearch.setEnabled(false);
                            }
                        } catch (Exception ex) {

                            ex.printStackTrace();
                            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            buttonSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {

                            listAdapter.clear();
                            bluetooth.findDevices();
                            listAdapter.addAll(bluetooth.getPairedDevices());
                        } catch (Exception ex) {

                            ex.printStackTrace();
                            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            );

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent connectBtIntent = new Intent(FragmentConfiguration.this.getActivity(), getClass());
                        startActivityForResult(connectBtIntent, REQUEST_CONNECT_DEVICE);
                    }
                }
            );
        } catch (Exception ex) {

            Log.e("FragmentConfiguration", "FragmentConfiguration.onCreateView() — Exception: " + ex.getMessage());
            ex.printStackTrace();
        } finally {

            return rootView;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {

            switch (requestCode) {

                case REQUEST_ENABLE_BT:
                    if (resultCode != RESULT_ERROR) {

                        textViewDevice.setText(bluetooth.getBTDeviceInf());
                        listAdapter.clear();
                        listAdapter.addAll(bluetooth.getPairedDevices());
                        buttonSearch.setEnabled(true);
                    } else {
                        toggleButton.setChecked(false);
                    }
                break ;

                case REQUEST_CONNECT_DEVICE:
                    Bundle extra = data.getExtras();
                    String deviceAddress = extra.getString("DeviceAddress");
                    bluetooth.connectBT(deviceAddress);
                break;
            }
        } catch(Exception ex) {

            Log.e("FragmentConfiguration", "FragmentConfiguration.onActivityResult() — Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
