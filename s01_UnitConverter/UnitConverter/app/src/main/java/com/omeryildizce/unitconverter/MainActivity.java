package com.omeryildizce.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView welcomeText, resultText;
    Button convert;
    EditText valueOfKilos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = findViewById(R.id.wellcomeText);
        resultText = findViewById(R.id.resultTextView);
        convert = findViewById(R.id.convertButton);
        valueOfKilos = findViewById(R.id.valueOfKilosEditTextText);

        convert.setOnClickListener(v -> {
                double kilos_entered = 0;
            if (!valueOfKilos.getText().toString().isEmpty()) {
                kilos_entered = Double.parseDouble(valueOfKilos.getText().toString());
            }
            resultText.setText(String.format("%.3f lb", convertToPaunds(kilos_entered)));

        });

    }

    public double convertToPaunds(double kilos) {
        return kilos * 2.20462;
    }
}