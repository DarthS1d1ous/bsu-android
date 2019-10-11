package com.borschevskydenis.secondlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView answerText;
    private EditText editTextMessage;
    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerText = findViewById(R.id.textView);
        sendMessageButton = findViewById(R.id.button);
        editTextMessage = findViewById(R.id.editText);

        Intent intent = getIntent();
        answerText.setText(intent.getStringExtra("text"));

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("text", editTextMessage.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
