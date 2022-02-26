package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    // Class Variables; are also called fields
    private TextView resultText;
    private Button calculateButton;
    private RadioButton malebutton;
    private RadioButton femalebutton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        setupButtonListener();
    }
    private void findviews(){
        resultText = findViewById(R.id.text_view_result);
        malebutton = findViewById(R.id.radio_button_male);
        femalebutton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_Calculate);
    }

    private void setupButtonListener() {
        calculateButton.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if(age >= 18) {
                    displayResult(bmiResult);
                }else {
                    displayGuidance(bmiResult);
                }

            }



        });
    }


    private double calculateBmi() {
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        // Converting the number 'Strings' into 'int' variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        // height in meters is the inches multiplied by 0.025
        int totalInches = (feet * 12) + inches;

        double heightInMeters = totalInches * 0.0254;

        //Bmi formula = weight in kg divided by height in meters squared
        return weight / (heightInMeters * heightInMeters);

    }


    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(bmi < 18.5) {
            //Display underweight
            fullResultString = bmiTextResult + " - You are underweight";
        }else if (bmi > 25) {
            //Display overweight
            fullResultString = bmiTextResult + " - You are overweight";

        }else {
            //Display healthy
            fullResultString = bmiTextResult + " - You are healthy weight";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (malebutton.isChecked()) {
            //Display boy guidance
            fullResultString = bmiTextResult + " - As you are 18, please consult with your doctor for the healthy range for boys";
        } else if (femalebutton.isChecked()) {
            //Display girl guidance
            fullResultString = bmiTextResult + " - As you are 18, please consult with your doctor for the healthy range for girls";
        }else {
            //Display general guidance
            fullResultString = bmiTextResult + " - As you are 18, please consult with your doctor for the healthy range";
        }
        resultText.setText(fullResultString);

    }

}
