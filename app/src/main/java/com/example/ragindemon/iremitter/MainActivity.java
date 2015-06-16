package com.example.ragindemon.iremitter;

import android.hardware.ConsumerIrManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "ConsumerIrTest";
    private ConsumerIrManager mCIR;
    private TextView show_pattern;
    private Button pattern1, pattern2, pattern3, pattern4;
    private int frequency = 38000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //frequency = (int) (1000000 / (frequency * 0.241246));

        show_pattern = (TextView) findViewById(R.id.show_patter);
        show_pattern.setVisibility(View.INVISIBLE);
        pattern1 = (Button) findViewById(R.id.pattern1);
        pattern1.setOnClickListener(pattern1ClickListener);
        pattern2 = (Button) findViewById(R.id.pattern2);
        pattern2.setOnClickListener(pattern2ClickListener);
        pattern3 = (Button) findViewById(R.id.pattern3);
        pattern3.setOnClickListener(pattern3ClickListener);
        pattern4 = (Button) findViewById(R.id.pattern4);
        pattern4.setOnClickListener(pattern4ClickListener);
        mCIR = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
        if (!mCIR.hasIrEmitter()) {
            Log.e(TAG, "No IR Emitter found\n");
            show_pattern.setText("No IR Emitter found");
            return;
        } else {
            StringBuilder builder = new StringBuilder();
            ConsumerIrManager.CarrierFrequencyRange[] freqs = mCIR.getCarrierFrequencies();
            builder.append("IR Carrier Frequencies:\n");
            for (ConsumerIrManager.CarrierFrequencyRange range : freqs) {
                builder.append(String.format("    %d - %d\n", range.getMinFrequency(),
                        range.getMaxFrequency()));
            }
            show_pattern.setText(builder.toString());
        }

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

    View.OnClickListener pattern1ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //patron 1
            int[] pattern = {0x60, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x30, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x450};
            show_pattern.setText(Arrays.toString(pattern));
            mCIR.transmit(frequency, pattern);
        }
    };

    View.OnClickListener pattern2ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //patron 2
            int[] pattern = {0x60, 0x18, 0x30, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x30, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x438};
            show_pattern.setText(Arrays.toString(pattern));
            mCIR.transmit(frequency, pattern);
        }
    };

    View.OnClickListener pattern3ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //patron 3
            int[] pattern = {0x60, 0x18, 0x18, 0x18, 0x30, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x30, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x438};
            show_pattern.setText(Arrays.toString(pattern));
            mCIR.transmit(frequency, pattern);
        }
    };

    View.OnClickListener pattern4ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //patron 4
            int[] pattern = {0x60, 0x18, 0x30, 0x18, 0x30, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x30, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x18, 0x041f};
            show_pattern.setText(Arrays.toString(pattern));
            mCIR.transmit(frequency, pattern);
        }
    };
}
