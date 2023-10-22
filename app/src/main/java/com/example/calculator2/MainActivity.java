package com.example.calculator2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result , calculation ;
    MaterialButton buttonC , buttonOpenBra , buttonCloseBra;
    MaterialButton buttonDivide , buttonMultiply , buttonMinus , buttonPlus ,buttonEqual ;
    MaterialButton button0 , button1 , button2 , button3, button5, button4, button6, button7, button8, button9;
    MaterialButton  buttonAc , buttonDot ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculation = findViewById(R.id.calculation);
        result = findViewById(R.id.result);

        assignId(buttonC , R.id.cbutton);
        assignId(buttonOpenBra , R.id.openbra);
        assignId(buttonCloseBra , R.id.closebra);
        assignId(buttonDivide , R.id.divide);
        assignId(buttonMultiply , R.id.multiply);
        assignId(buttonMinus , R.id.minus);
        assignId(buttonPlus , R.id.addition);
        assignId(button0 , R.id.zero);
        assignId(button1 , R.id.one);
        assignId(button2 , R.id.two);
        assignId(button3 , R.id.three);
        assignId(button4 , R.id.four);
        assignId(button5 , R.id.five);
        assignId(button6 , R.id.six);
        assignId(button7 , R.id.sevenbutton);
        assignId(button8 , R.id.eight);
        assignId(button9 , R.id.nine);
        assignId(buttonAc , R.id.acbutton);
        assignId(buttonDot , R.id.point);
        assignId(buttonEqual , R.id.equal);
    }

    void assignId(MaterialButton btn , int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = calculation.getText().toString();
//        calculation.setText(buttonText);
        if (buttonText.equals("AC")) {
            calculation.setText("");
            result.setText("0");
            return ;
        }
        if (buttonText.equals("=")) {
            calculation.setText(result.getText());
            return ;
        }
        if (buttonText.equals("C")) {
//                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            if (calculation.length()==1)
            {
                System.out.println("0");
            }
            else {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
        }


        else {
            dataToCalculate.replace("0","1");
            dataToCalculate = dataToCalculate + buttonText;
        }
        calculation.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")){
            result.setText(finalResult);
        }
//        return 0;
    }
    String getResult(String data)
    {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return  finalResult ;
        }
        catch (Exception e)
        {
            return "Err";
        }
    }

}