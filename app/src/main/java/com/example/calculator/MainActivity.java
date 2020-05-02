package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //variables to hold the operands and type of calculations
    private Double operand1=null;
    private Double operand2=null;
    private String pendingOperation="=";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result=findViewById(R.id.result);
        newNumber=findViewById(R.id.newNumber);
        displayOperation=findViewById(R.id.operation);

        Button button0=findViewById(R.id.button0);
        Button button1=findViewById(R.id.button1);
        Button button2=findViewById(R.id.button2);
        Button button3=findViewById(R.id.button3);
        Button button4=findViewById(R.id.button4);
        Button button5=findViewById(R.id.button5);
        Button button6=findViewById(R.id.button6);
        Button button7=findViewById(R.id.button7);
        Button button8=findViewById(R.id.button8);
        Button button9=findViewById(R.id.button9);
        Button buttonPlus=findViewById(R.id.buttonPlus);
        Button buttonMultiply=findViewById(R.id.buttonMultiply);
        Button buttonDivide=findViewById(R.id.buttonDivide);
        Button buttonMinus=findViewById(R.id.buttonMinus);
        Button buttonDot=findViewById(R.id.buttonDot);
        Button buttonEquals=findViewById(R.id.buttonEquals);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b=(Button)v;
                newNumber.append(b.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);


        View.OnClickListener opListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b=(Button)v;
                String operation=b.getText().toString();
                String value=newNumber.getText().toString();
                try{
                    Double doubleValue=Double.valueOf(value);
                    performOperation(doubleValue,operation);
                }catch(NumberFormatException e){
                    newNumber.setText("");
                }
                pendingOperation=operation;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonDivide.setOnClickListener(opListener);
        buttonEquals.setOnClickListener(opListener);
        buttonMinus.setOnClickListener(opListener);
        buttonMultiply.setOnClickListener(opListener);
        buttonPlus.setOnClickListener(opListener);


    }
    void performOperation(Double value,String operation){
        Log.d(TAG, "performOperation: initially operand1 is "+operand1+" and operand 2 is "+operand2);
        Log.d(TAG, "performOperation: inside,operation is"+operation+" and value is "+value);
        if(null==operand1){
            operand1=value;
            Log.d(TAG, "inside if clause: value of operand 1 is "+operand1);
        }else{
            operand2=value;
            Log.d(TAG, "inside else clause: value of operand 2 is "+operand2);
            if(pendingOperation.equals("=")){
                pendingOperation=operation;
            }
            switch(pendingOperation){
                case "=":
                    operand1=operand2;
                    break;
                case "/":
                    if(operand2==0){
                        operand1=0.0;
                    }else{
                        operand1/=operand2;
                    }
                    break;
                case "*":operand1 *=operand2;
                    break;
                case "-":operand1-=operand2;
                    break;
                case "+":operand1+=operand2;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}
