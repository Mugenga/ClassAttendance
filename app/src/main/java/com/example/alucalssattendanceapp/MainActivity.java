package com.example.alucalssattendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    StudentsDatabase mydatabase;
    private android.nfc.NfcAdapter NfcAdapter;
    PendingIntent nfcPendingIntent;
    IntentFilter[] intentFiltersArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User_details details =new  User_details();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,details).commit();

        //Check if NFC is available on device
        NfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(NfcAdapter != null) {
            //Handle some NFC initialization here
            Toast.makeText(this, "NFC Yuppy",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "NFC not available on this device",
                    Toast.LENGTH_SHORT).show();
        }

        NfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // Check if the smartphone has NFC
        if (NfcAdapter == null) {
            Toast.makeText(this, "NFC not supported", Toast.LENGTH_LONG).show();
            finish();
        }

        // Check if NFC is enabled
        if (!NfcAdapter.isEnabled()) {
            Toast.makeText(this, "Enable NFC before using the app", Toast.LENGTH_LONG).show();
        }

        Intent nfcIntent = new Intent(this, getClass());
        nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        nfcPendingIntent =
                PendingIntent.getActivity(this, 0, nfcIntent, 0);

        // Create an Intent Filter limited to the URI or MIME type to
        // intercept TAG scans from.
        IntentFilter tagIntentFilter =
                new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            //tagIntentFilter.addDataScheme("http");
            // tagIntentFilter.addDataScheme("vnd.android.nfc");
            tagIntentFilter.addDataScheme("tel");
            //tagIntentFilter.addDataType("text/plain");
            intentFiltersArray = new IntentFilter[]{tagIntentFilter};
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        NfcAdapter.enableForegroundDispatch(
                this,
                nfcPendingIntent,
                intentFiltersArray,
                null);
        handleIntent(getIntent());
    }

    @Override
    protected void onPause() {
        super.onPause();
        NfcAdapter.disableForegroundDispatch(this);
    }

    private void handleIntent(Intent i) {

        Log.d("NFC", "Intent [" + i + "]");

        //getTag(i);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages =
                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                }
                // Process the messages array.

            }
        }
    }
}
