package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText num1;
    private EditText num2;
    private Button add;
    private TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText) findViewById(R.id.editTextNumber);
        num2 = (EditText) findViewById(R.id.editTextNumber2);
        add = (Button) findViewById(R.id.button);
        answer = (TextView) findViewById(R.id.textView2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number1 = 0;
                int number2 = 0;
                if (!num1.getText().toString().equals("Enter First Number"))
                    number1 = Integer.parseInt(num1.getText().toString());
                if (!num2.getText().toString().equals(null))
                    number2 = Integer.parseInt(num2.getText().toString());
                int sum = number1 + number2;
                answer.setText("The answer is " + String.valueOf(sum));
            }
        });
    }
}