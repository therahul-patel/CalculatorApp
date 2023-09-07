package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentNumber = "";
    private String currentOperator = "";
    private double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Set click listeners for digit buttons (0-9)
        setDigitClickListeners();

        // Set click listeners for operator buttons (+, -, *, /)
        setOperatorClickListeners();

        // Handle the equals button
        handleEquals();
    }

    private void setDigitClickListeners() {
        int[] digitButtonIds = {
            R.id.button0, R.id.button1, R.id.button2,
            R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9
        };

        for (int id : digitButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String digit = button.getText().toString();
                    currentNumber += digit;
                    resultTextView.setText(currentNumber);
                }
            });
        }
    }

    private void setOperatorClickListeners() {
        int[] operatorButtonIds = {
            R.id.buttonAdd, R.id.buttonSubtract,
            R.id.buttonMultiply, R.id.buttonDivide
        };

        for (int id : operatorButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!currentNumber.isEmpty()) {
                        currentOperator = button.getText().toString();
                        result += Double.parseDouble(currentNumber);
                        currentNumber = "";
                    }
                }
            });
        }
    }

    private void handleEquals() {
        Button equalsButton = findViewById(R.id.buttonEquals);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.isEmpty() && !currentOperator.isEmpty()) {
                    double number = Double.parseDouble(currentNumber);
                    switch (currentOperator) {
                        case "+":
                            result += number;
                            break;
                        case "-":
                            result -= number;
                            break;
                        case "*":
                            result *= number;
                            break;
                        case "/":
                            if (number != 0) {
                                result /= number;
                            } else {
                                resultTextView.setText("Error");
                                return;
                            }
                            break;
                    }
                    resultTextView.setText(String.valueOf(result));
                    currentNumber = "";
                    currentOperator = "";
                }
            }
        });
    }
}
