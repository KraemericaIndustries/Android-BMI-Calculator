package com.kraemericaindustries.bmicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView resultText;
    private Button calculateButton;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private EditText stringAge;
    private EditText stringFeet;
    private EditText stringInches;
    private EditText stringWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        ready();
    }
    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        radioMale = findViewById(R.id.radio_button_male);
        radioFemale = findViewById(R.id.radio_button_female);
        stringAge = findViewById(R.id.edit_text_age);
        stringFeet = findViewById(R.id.edit_text_feet);
        stringInches = findViewById(R.id.edit_text_inches);
        stringWeight = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.calculate);
    }
    private void ready() {
        calculateButton.setOnClickListener(view -> {
            double bmiResult = calculateBmi();
            String ageText = stringAge.getText().toString();
            int age = Integer.parseInt(ageText);

            if(age >= 18) {
                displayResult(bmiResult);
            } else {
                displayGuidance(bmiResult);
            }
        });
    }
    private double calculateBmi() {
        //  Capture the values in Views from our App as Strings:
        String feetText = stringFeet.getText().toString();
        String inchesText = stringInches.getText().toString();
        String weightText = stringWeight.getText().toString();
        //  Converting numerical values captured as a type of String, to a type of 'int' (in order to make arithmetic possible):
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);
        //  Perform the needed arithmetic:
        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;
        //  Return the needed value:
        return weight / (heightInMeters * heightInMeters);
    }
    private void displayResult(double bmi) {
        //  Create an instance of the DecimalFormat class, with an argument of the desired number of decimal places:
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        //  Capture the result type of a double, format it by creating an instance of the DecimalFormat{} class, and save the String result to a variable:
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;

        if(bmi < 18.5) {
            fullResultString = bmiTextResult + " - You are underweight.";
        } else if (bmi > 25) {
            fullResultString = bmiTextResult + " - You are overweight.";
        } else {
            fullResultString = bmiTextResult + " - You are a healthy weight.";
        }
        resultText.setText(fullResultString);
    }
    private void displayGuidance(double bmi) {
        //  Create an instance of the DecimalFormat class, with an argument of the desired number of decimal places:
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        //  Capture the result type of a double, format it by creating an instance of the DecimalFormat{} class, and save the String result to a variable:
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;

        if(radioMale.isChecked()) {
            fullResultString = bmiTextResult + " - As you are under 18, consult your doctor for the healthy range for Boys";
        } else if(radioFemale.isChecked()) {
            fullResultString = bmiTextResult + " - As you are under 18, consult your doctor for the healthy range for Girls";
        } else {
            fullResultString = bmiTextResult + " - As you are under 18, consult your doctor for the healthy range.";
        }
        resultText.setText(fullResultString);
    }
}