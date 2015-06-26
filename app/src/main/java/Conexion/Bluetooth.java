package Conexion;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
import java.util.Set;

/**
 * Created by Gabriel Delgado on 26/06/2015.
 */
public class Bluetooth {

    private BluetoothAdapter bluetoothAdapter;

    public Bluetooth(BluetoothAdapter bluetoothAdapter) {

        this.bluetoothAdapter = bluetoothAdapter;
    }

    public void turnOffBT() throws Exception {

        try {

            if (isBTTurnedOn()) {

                bluetoothAdapter.disable();
            }
        } catch(Exception ex) {

            ex.printStackTrace();
            throw ex;
        }
    }

    public String getBTDeviceInf() throws IllegalFormatConversionException {

        String deviceInformation = "";
        try {

            if (isBTTurnedOn()) {

                deviceInformation = String.format("%s : %s", bluetoothAdapter.getName(), bluetoothAdapter.getAddress());
            }
        } catch (IllegalFormatConversionException ex) {

            ex.printStackTrace();
            throw ex;
        } finally {

            return deviceInformation;
        }
    }

    public void findDevices() throws Exception {

        try {

            bluetoothAdapter.startDiscovery();
        } catch(Exception ex) {

            ex.printStackTrace();
            throw ex;
        }
    }

    public ArrayList<String> getPairedDevices() throws Exception {

        ArrayList<String> list = new ArrayList<String>();
        try {

            Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
            for (BluetoothDevice device : devices) {

                list.add(device.getName());
            }
        } catch(Exception ex) {

            ex.printStackTrace();
            throw ex;
        } finally {
            return list;
        }
    }

    public boolean isBTTurnedOn() {

        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {

            return true;
        }

        return false;
    }

    public void connectBT(String deviceAddress) throws Exception {

        try {

            BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(deviceAddress);
//            mBluetoothConnectProgressDialog = ProgressDialog.show(this, "Connecting...", bluetoothDevice.getName() + " : " + bluetoothDevice.getAddress(), true, false);
//            Thread mBlutoothConnectThread = new Thread(this);
//            mBlutoothConnectThread.start();
        } catch(Exception ex) {

            ex.printStackTrace();
            throw ex;
        }
    }
}
