package com.borschevskydenis.secondlab;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.borschevskydenis.secondlab.DialogFragment.ExitDialogFragment;

public class SecondActivity extends AppCompatActivity {

    private TextView answerText;
    private EditText editTextMessage;
    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerText = findViewById(R.id.textViewFragment);
        sendMessageButton = findViewById(R.id.buttonFragment);
        editTextMessage = findViewById(R.id.editTextFragment);

        Intent intent = getIntent();
        answerText.setText(intent.getStringExtra(MainActivity.TEXT));

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.TEXT, editTextMessage.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
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

    @Override
    public void finishAndRemoveTask() {
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.EXIT, MainActivity.EXIT);
        setResult(RESULT_OK, intent);
        super.finishAndRemoveTask();
    }
}
