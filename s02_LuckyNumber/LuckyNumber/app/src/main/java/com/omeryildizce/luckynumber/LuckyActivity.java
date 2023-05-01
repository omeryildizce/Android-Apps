package com.omeryildizce.luckynumber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckyActivity extends AppCompatActivity {
    TextView welcomeText, luckyNumberText;
    Button shareButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);

        shareButton = findViewById(R.id.shareResult);
        welcomeText = findViewById(R.id.textView2);
        luckyNumberText = findViewById(R.id.luckyNumbbrText);


        Intent intent= getIntent();
        String userName = intent.getStringExtra("name");

        Toast.makeText(this, "User Name " + userName, Toast.LENGTH_SHORT).show();

        int randomNumber = generateRandomNumber();
        luckyNumberText.setText(""+randomNumber);


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName, randomNumber);
            }
        });
    }

    public int generateRandomNumber(){
        Random random = new Random();
        int upperLimit = 1_000;
        return random.nextInt(upperLimit);
    }

    public void shareData(String userName, int luckyNumber){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, userName);
        intent.putExtra(Intent.EXTRA_TEXT, luckyNumber);
        startActivity(Intent.createChooser(intent, "Choose a platform"));
    }
}