package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button sendMessage;
    Button call;
    Button search;
    Button send;
    EditText editTextName;
    TextView feedBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call = findViewById(R.id.callButton);
        search = findViewById(R.id.searchButton);
        send = findViewById(R.id.sendButton);
        sendMessage = findViewById(R.id.sendMessage);
        editTextName = findViewById(R.id.editText);
        feedBack = findViewById(R.id.feedback);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText phoneNumber = findViewById(R.id.editCall);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("Tel:" + phoneNumber.getText().toString()));
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchTerm = findViewById(R.id.editSearch);
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, searchTerm.getText().toString());
                startActivity(intent);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText sendContent = findViewById(R.id.editSend);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, sendContent.getText().toString());
                intent.setType("text/plain");
                startActivity(intent);
            }
        });
    }
    public void sendMessage() {
        String fullName = this.editTextName.getText().toString();
        String message = "Hello, Please say hello to me!";
        Intent intent = new Intent(this, GreetingActivity.class);
        intent.putExtra("fullName", fullName);
        intent.putExtra("message", message);
        this.startActivityForResult(intent, 100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == 100) {
            String feedback = data.getStringExtra("feedback");
            this.feedBack.setText(feedback);
        }
        else {
            this.feedBack.setText("??");
        }
    }
}