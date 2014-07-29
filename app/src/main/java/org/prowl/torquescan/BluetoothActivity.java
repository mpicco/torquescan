package org.prowl.torquescan;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.prowl.torque.remote.ITorqueService;

import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.UUID;
import java.util.Vector;

/*
 * Scan a group of PIDs for a response
 */
public class BluetoothActivity extends Activity {

    private static final String TAG = BluetoothActivity.class.getSimpleName();

    private static final Vector<String> tipsShown = new Vector();
    private ITorqueService torqueService;

    public static final String SCAN = "Start Scan";
    public static final String SEND = "Email results";

    private TextView textView;
    private Handler handler;
    private NumberFormat nf;

    private Timer updateTimer;

    // bluetooth data
    BluetoothAdapter mBluetoothAdapter;
    BluetoothServerSocket mBluetoothServerSocket;
    public static final int REQUEST_TO_START_BT = 100;
    public static final int REQUEST_FOR_SELF_DISCOVERY = 200;
    UUID MY_UUID = UUID.fromString("D04E3068-E15B-4482-8306-4CABFA1726E7");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.main, null);
        textView = (TextView)view.findViewById(R.id.textview);
        textView.setText("Bluetooth Server.");

        // Max of 2 digits for readings.
        nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

        handler = new Handler();

        setContentView(view);

        // initialize Bluetooth and retrieve info about the BT radio interface
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        if (mBluetoothAdapter == null) {
            textView.setText("Device does not support Bluetooth");
            return;
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                textView.setText("Bluetooth supported but not enabled");
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_TO_START_BT);
            } else {
                textView.setText("Bluetooth supported and enabled");
            }
        }

    }

    private class AcceptThread extends Thread {
        private BluetoothServerSocket mServerSocket;

        public AcceptThread() {
            try {
                mServerSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("ClassicBluetoothServer", MY_UUID);
            }
            catch (IOException e) {
                final IOException ex = e;
                runOnUiThread(new Runnable() {
                    public void run() {
                        textView.setText(ex.getMessage());
                    }
                });
            }
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            textView.setText(textView.getText() + "\n\nWaiting for Bluetooth Client ...");
                        }
                    });

                    socket = mServerSocket.accept(); // blocking call

                } catch (IOException e) {
                    Log.v(TAG, e.getMessage());
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work in a separate thread
                    new ConnectedThread(socket).start();

                    try {
                        mServerSocket.close();
                    } catch (IOException e) {
                        Log.v(TAG, e.getMessage());
                    }
                    break;
                }
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mSocket;
        private final OutputStream mOutStream;
        private int bytesRead;
        final private String FILE_TO_BE_TRANSFERRED = "btlogo.jpeg";
        final private String PATH = Environment.getExternalStorageDirectory().toString() + "/btserver/";

        public ConnectedThread(BluetoothSocket socket) {
            mSocket = socket;
            OutputStream tmpOut = null;

            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
            mOutStream = tmpOut;
        }


        public void run() {

            // transfer a file
            if (mOutStream != null) {

                runOnUiThread(new Runnable() {
                    public void run() {
                        textView.setText(textView.getText() + "\nbefore sending text.. ");
                    }
                });
                while (true) {
                    try {
                        float[] vals = {0f, 0f, 0f};
                        String[] pids = {"0c", "0d", "11"}; // RPM, SPEED, THROTTLE
                        //String[] pids = {"ff1223"}; // Accelerometer

                        try {
                            vals = torqueService.getPIDValues(pids);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                        // TODO: compute gear position
                        float gear = 0;

                        // convert to MPH from KPH

                        vals[1] = vals[1]/1.61f;

                        // create and send the BT message

                        String msg = String.format("%02.0f\t%03.0f\t%02.0f\t%1.0f", vals[0]/100, vals[1], vals[2], gear);
                        final int len = 11;  // TODO: This needs to be updated here and in the HUD client whenever msg changes
                        byte[] buf = msg.getBytes();

                        mOutStream.write(buf, 0, len);

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage());
                        try {
                            mSocket.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
            new AcceptThread().start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Bind to the torque service
        Intent intent = new Intent();
        intent.setClassName("org.prowl.torque", "org.prowl.torque.remote.TorqueService");
        boolean successfulBind = bindService(intent, connection, 0);

        if (successfulBind) {
            // Goodness & light...

        } else {
            textView.setText("Unable to connect to Torque plugin service");
        }
        textView.setKeepScreenOn(true);


    }


    @Override
    protected void onPause() {
        super.onPause();

        if (updateTimer != null)
            updateTimer.cancel();

        tip("Press menu for options");
        unbindService(connection);
    }


    // Utility methods
    //
    public String toHex(int i) {
        String pidHex = Integer.toString(i,16);
        if (pidHex.length() % 2 == 1) {
            pidHex="0"+pidHex;
        }
        return pidHex;
    }


    /**
     * Bits of service code. You usually won't need to change this.
     */
    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName arg0, IBinder service) {
            torqueService = ITorqueService.Stub.asInterface(service);

            new AcceptThread().start();

        };
        public void onServiceDisconnected(ComponentName name) {
            torqueService = null;
        };
    };


    public void tip(String tip) {
        if (!tipsShown.contains(tip)) {
            tipsShown.add(tip);
            toast(tip, null);
        }
    }

    public void toast(final String message) {
        toast(message, null);
    }


    public void toast(final String message, Context c) {
        // If not visible, then don't toast.
        // if (!isForeground)
        //    return;
        if (c == null)
            c = this;
        final Context context = c;

        handler.post(new Runnable() { public void run() {
            try {
                // try { Thread.sleep(100); } catch(InterruptedException e) { }
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            } catch(Throwable e) {
                // Do nothing
            }
        }});

    }

    public void popupMessage(final String title, final String message) {
        handler.post(new Runnable() {
            public void run() {
                try {
                    final AlertDialog adialog = new AlertDialog.Builder(BluetoothActivity.this).create();

                    adialog.setButton("OK", new OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adialog.dismiss();

                        }
                    });

                    ScrollView svMessage = new ScrollView(BluetoothActivity.this);
                    TextView tvMessage = new TextView(BluetoothActivity.this);

                    SpannableString spanText = new SpannableString(message);

                    Linkify.addLinks(spanText, Linkify.ALL);
                    tvMessage.setText(spanText);
                    tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f);
                    tvMessage.setMovementMethod(LinkMovementMethod.getInstance());

                    svMessage.setPadding(14, 2, 10, 12);
                    svMessage.addView(tvMessage);

                    adialog.setTitle(title);
                    adialog.setView(svMessage);

                    adialog.show();
                } catch (Throwable e) {
                    e.printStackTrace();
                    //DebugConfig.debug(e);
                }
            }
        });
    }

}