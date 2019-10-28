package com.borschevskydenis.secondlab;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.borschevskydenis.secondlab.DialogFragment.ExitDialogFragment;

public class MainActivity extends AppCompatActivity {

    final static String EXIT = "exit";
    final static String TEXT = "text";
    final int REQUEST_CODE = 1;

    private TextView answerText;
    private EditText editTextMessage;
    private Button sendMessageButton;

    private TextView textOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerText = findViewById(R.id.textViewFragment);
        sendMessageButton = findViewById(R.id.buttonFragment);
        editTextMessage = findViewById(R.id.editTextFragment);
        textOrientation = findViewById(R.id.textView2);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(TEXT, editTextMessage.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                if (data.getStringExtra(EXIT) != null && data.getStringExtra(EXIT).equals(EXIT)) {
                    finishAndRemoveTask();
                }
                answerText.setText(data.getStringExtra(TEXT));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingItem:
                return true;
            case R.id.informationItem:
                return true;
            case R.id.exitItem:
                ExitDialogFragment exitDialogFragment = new ExitDialogFragment();
                exitDialogFragment.show(getSupportFragmentManager(), "ExitDialogFragment");
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (textOrientation.getText().equals("")) {
            textOrientation.setText("Landscape View");
        } else {
            textOrientation.setText("");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextMessage.setText("");
    }
}
