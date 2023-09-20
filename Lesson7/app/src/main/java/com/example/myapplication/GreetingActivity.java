package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GreetingActivity extends AppCompatActivity {
    Button backButton;
    String name;
    String message;
    TextView textViewMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);
        backButton = findViewById(R.id.backButton);
        textViewMessage = findViewById(R.id.textView);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        Intent intent = getIntent();
        name = intent.getStringExtra("fullName");
        message = intent.getStringExtra("message");
        textViewMessage.setText(message);
    }

    public void goBack() {
        onBackPressed();
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        String feedback = "OK, Hello " + name + ". How are you?";
        data.putExtra("feedback", feedback);

        setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}
