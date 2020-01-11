package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    TextView showValue;
    Double operand = null;
    String lastOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showValue = findViewById(R.id.text_view);

    }

    public void clean(View v ){
        operand = null;
        lastOperation = "=";
        showValue.setText("0");
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        showValue.setText(operand.toString());
        showValue.setText(lastOperation);
    }


    public void onAction(Double number, String operation) {
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
        }
        switch (lastOperation) {
            case "=":
                operand = number;
                break;
            case "/":
                if (number == 0) {
                    operand = 0.0;
                } else {
                    operand /= number;
                }
                break;
            case "*":
                operand *= number;
                break;
            case "+":
                operand += number;
                break;
            case "-":
                operand -= number;
                break;
        }

        showValue.setText(lastOperation);


    }

    public void onNumberClick(View view) {

        Button button = (Button) view;
        showValue.setText(button.getText());


    }


    public void onActionButton(View v) {
        Button button = (Button) v;
        String str = button.getText().toString();
        String num = showValue.getText().toString();
        try {
            onAction(Double.valueOf(num), str);
        } catch (NumberFormatException nfe) {

        }
        lastOperation = str;
        showValue.setText(lastOperation);


    }

    public void onEqualButton(View v) {
        Button button = (Button) v;
        String str = button.getText().toString();
        String num = showValue.getText().toString();

        try {
            onAction(Double.valueOf(num), str);
        } catch (NumberFormatException nfe) {}
        showValue.setText(operand.toString());
        operand=null;

    }


}

