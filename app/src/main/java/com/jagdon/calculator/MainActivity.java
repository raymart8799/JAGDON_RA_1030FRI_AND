package com.jagdon.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    EditText display;
    double num1, num2;
    boolean add, sub, mul, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize display (EditText in this case)
        display = findViewById(R.id.display);

        // Number buttons
        findViewById(R.id.button_0).setOnClickListener(v -> appendToDisplay("0"));
        findViewById(R.id.button_1).setOnClickListener(v -> appendToDisplay("1"));
        findViewById(R.id.button_2).setOnClickListener(v -> appendToDisplay("2"));
        findViewById(R.id.button_3).setOnClickListener(v -> appendToDisplay("3"));
        findViewById(R.id.button_4).setOnClickListener(v -> appendToDisplay("4"));
        findViewById(R.id.button_5).setOnClickListener(v -> appendToDisplay("5"));
        findViewById(R.id.button_6).setOnClickListener(v -> appendToDisplay("6"));
        findViewById(R.id.button_7).setOnClickListener(v -> appendToDisplay("7"));
        findViewById(R.id.button_8).setOnClickListener(v -> appendToDisplay("8"));
        findViewById(R.id.button_9).setOnClickListener(v -> appendToDisplay("9"));
        findViewById(R.id.button_dot).setOnClickListener(v -> {
            if (!display.getText().toString().contains(".")) {
                appendToDisplay(".");
            }
        });

        // Clear button (Resets the display)
        findViewById(R.id.button_clear).setOnClickListener(v -> display.setText(""));

        // Operator buttons
        findViewById(R.id.button_plus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.button_minus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.button_multiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.button_divide).setOnClickListener(v -> setOperator("/"));

        // Equals button (Performs the operation)
        findViewById(R.id.button_equals).setOnClickListener(v -> calculateResult());
    }

    private void appendToDisplay(String value) {
        display.append(value);
    }

    private void setOperator(String operator) {
        if (display.getText().length() != 0) {
            num1 = Double.parseDouble(display.getText().toString());
            display.setText(""); // Clear the display for next input

            // Set which operator is pressed
            switch (operator) {
                case "+":
                    add = true;
                    break;
                case "-":
                    sub = true;
                    break;
                case "*":
                    mul = true;
                    break;
                case "/":
                    div = true;
                    break;
            }
        }
    }

    private void calculateResult() {
        if (display.getText().length() != 0) {
            num2 = Double.parseDouble(display.getText().toString());

            double result = 0;

            // Perform the calculation based on the operator
            if (add) {
                result = num1 + num2;
                add = false;
            } else if (sub) {
                result = num1 - num2;
                sub = false;
            } else if (mul) {
                result = num1 * num2;
                mul = false;
            } else if (div) {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error");
                    return;
                }
                div = false;
            }

            // Display the result
            display.setText(String.valueOf(result));
        }
    }
}
