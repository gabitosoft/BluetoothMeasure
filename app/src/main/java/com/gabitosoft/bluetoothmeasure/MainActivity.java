package com.gabitosoft.bluetoothmeasure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.bluetooth.*;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

    private BluetoothAdapter bluetooth;
    private TextView status;
    private ToggleButton toggleButton;
    private Context context;

    private int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetooth = BluetoothAdapter.getDefaultAdapter();
        status = (TextView)findViewById(R.id.textView);
        context = this;
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        toggleButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (bluetooth != null) {

                            if(toggleButton.isChecked()) {

                                Toast.makeText(context, "CHECKED", Toast.LENGTH_SHORT).show();
                                if (!bluetooth.isEnabled()) {

                                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//                                    String mydeviceaddress = bluetooth.getAddress();
//                                    String mydevicename = bluetooth.getName();
//                                    status.setText(mydevicename + " : " + mydeviceaddress);
                                }
                            } else {

                                Toast.makeText(context, "UNCHECKED", Toast.LENGTH_SHORT).show();
                                if (bluetooth.isEnabled()) {
//                                    status.setText("Bluetooth is not enabled");
//                                    bluetooth.disable();
                                }
                            }
                        } else {

                            Toast.makeText(context, "Bluetooth Adapter Undefined", Toast.LENGTH_LONG).show();
                        }
                    }
                });
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
