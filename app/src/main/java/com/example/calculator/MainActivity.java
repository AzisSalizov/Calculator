package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Double first, second;
    private boolean isOperationClick;
    private String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.text_view);

    }

    public void onNumberClick(View view) {
        String text = ((MaterialButton) view).getText().toString();
        if (text.equals("AC")) {
            textView.setText("0");
            first = null;
            second = null;
            operation = null;
        } else if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(text);
        } else {
            textView.append(text);
        }
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        if (first == null) {
            first = Double.valueOf(textView.getText().toString());
        } else {
            second = Double.valueOf(textView.getText().toString());
            Double result = operation(first, second, operation);
            textView.setText(result.toString());
            first = result;
            second = null;
        }
        operation = ((MaterialButton) view).getText().toString();
        isOperationClick = true;
    }

    private Double operation(Double first, Double second, String operation) {
        switch (operation) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "X":
                return first * second;
            case "/":
                if (second != 0) {
                    return first / second;
                } else {
                    return 0.0;
                }
            default:
                return first;
        }
    }
}
