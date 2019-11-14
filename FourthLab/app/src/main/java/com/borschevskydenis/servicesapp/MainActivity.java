package com.borschevskydenis.servicesapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editNumber;
    private TextView amountOfNumberView;
    private TextView allNumbersView;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumber = findViewById(R.id.editNumber);
        amountOfNumberView = findViewById(R.id.amountOfNumbers);
        allNumbersView = findViewById(R.id.allNumbers);
        submitButton = findViewById(R.id.submitButton);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("MainActivity"));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.valueOf(editNumber.getText().toString());

                Intent serviceIntent = new Intent(getApplicationContext(),MyIntentService.class);
                serviceIntent.putExtra("number",number);
                startService(serviceIntent);
                submitButton.setEnabled(false);
            }
        });

        allNumbersView.setMovementMethod(new ScrollingMovementMethod());
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String allNumbers = intent.getStringExtra("numbers");
            int count = intent.getIntExtra("count",0);

            amountOfNumberView.setText(String.valueOf(count));
            allNumbersView.setText(allNumbers);

            submitButton.setEnabled(true);
        }
    };
}
