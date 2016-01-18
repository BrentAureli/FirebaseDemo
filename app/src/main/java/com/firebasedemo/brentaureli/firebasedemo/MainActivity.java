package com.firebasedemo.brentaureli.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String FIREBASE_URL = "https://luminous-heat-605.firebaseio.com/";

    private Firebase firebaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(FIREBASE_URL);

        EditText inputText = (EditText) findViewById(R.id.messageText);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    sendMessage();
                }
                return true;
            }
        });

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    public void sendMessage(){
        EditText textInput = (EditText) findViewById(R.id.messageText);
        String message = textInput.getText().toString();
        if(!message.equals("")){
            Random rand = new Random();
            String author = "TestUser" + rand.nextInt(1000);
            ChatMessage cMessage = new ChatMessage(author, message);
            firebaseRef.push().setValue(cMessage);
            textInput.setText("");
        }
    }
}
