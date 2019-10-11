package com.borschevskydenis.secondlab;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;

    private TextView answerText;
    private EditText editTextMessage;
    private Button sendMessageButton;
    private TextView textOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerText = findViewById(R.id.textView);
        sendMessageButton = findViewById(R.id.button);
        editTextMessage = findViewById(R.id.editText);
        textOrientation = findViewById(R.id.textView2);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("text", editTextMessage.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE) {
                String text = data.getStringExtra("text");
                answerText.setText(text);
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (textOrientation.getText().equals("")) {
            textOrientation.setText("Landscape View");
        } else {
            textOrientation.setText("");
        }
    }
}
