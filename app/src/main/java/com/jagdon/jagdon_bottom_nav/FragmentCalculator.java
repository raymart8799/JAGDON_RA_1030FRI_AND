package com.jagdon.jagdon_bottom_nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentCalculator extends Fragment {

    // Declare variables
    private EditText display;
    private double num1, num2;
    private boolean add, sub, mul, div;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        // Initialize display (EditText in this case)
        display = view.findViewById(R.id.display);

        // Number buttons
        setUpNumberButtons(view);
        setUpOperatorButtons(view);

        // Clear button (Resets the display)
        view.findViewById(R.id.button_clear).setOnClickListener(v -> display.setText(""));

        // Equals button (Performs the operation)
        view.findViewById(R.id.button_equals).setOnClickListener(v -> calculateResult());

        return view;
    }

    private void setUpNumberButtons(View view) {
        // Set up number button listeners
        view.findViewById(R.id.button_0).setOnClickListener(v -> appendToDisplay("0"));
        view.findViewById(R.id.button_1).setOnClickListener(v -> appendToDisplay("1"));
        view.findViewById(R.id.button_2).setOnClickListener(v -> appendToDisplay("2"));
        view.findViewById(R.id.button_3).setOnClickListener(v -> appendToDisplay("3"));
        view.findViewById(R.id.button_4).setOnClickListener(v -> appendToDisplay("4"));
        view.findViewById(R.id.button_5).setOnClickListener(v -> appendToDisplay("5"));
        view.findViewById(R.id.button_6).setOnClickListener(v -> appendToDisplay("6"));
        view.findViewById(R.id.button_7).setOnClickListener(v -> appendToDisplay("7"));
        view.findViewById(R.id.button_8).setOnClickListener(v -> appendToDisplay("8"));
        view.findViewById(R.id.button_9).setOnClickListener(v -> appendToDisplay("9"));
        view.findViewById(R.id.button_dot).setOnClickListener(v -> {
            if (!display.getText().toString().contains(".")) {
                appendToDisplay(".");
            }
        });
    }

    private void setUpOperatorButtons(View view) {
        // Operator buttons
        view.findViewById(R.id.button_plus).setOnClickListener(v -> setOperator("+"));
        view.findViewById(R.id.button_minus).setOnClickListener(v -> setOperator("-"));
        view.findViewById(R.id.button_multiply).setOnClickListener(v -> setOperator("*"));
        view.findViewById(R.id.button_divide).setOnClickListener(v -> setOperator("/"));
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
