package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    TextView text;
    String str,end="";
    String oldNo;
    String newNo;
    boolean checkBracket=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.edit_text);
    }

    public void numberEvent(View view)
    {
        str = text.getText().toString();

        switch(view.getId())
        {
            case R.id.bt_0 :
                str += "0";
                break;
            case R.id.bt_1:
                str +="1";
                break;
            case R.id.bt_2:
                str += "2";
                break;
            case R.id.bt_3:
                str +="3";
                break;
            case R.id.bt_4:
                str += "4";
                break;
            case R.id.bt_5:
                str +="5";
                break;
            case R.id.bt_6:
                str += "6";
                break;
            case R.id.bt_7:
                str +="7";
                break;
            case R.id.bt_8:
                str += "8";
                break;
            case R.id.bt_9:
                str +="9";
                break;
            case R.id.bt_back:
                if (str=="")
                    break;
                str = str.replace(str.substring(str.length()-1), "");
                break;
            case R.id.bt_equals:

                str = str.replace("X","*");
//                str = str.replace("%","/100");

                Context rhino = Context.enter();

                rhino.setOptimizationLevel(-1);

                String finalResult = "";

                try{
                    Scriptable scriptable = rhino.initSafeStandardObjects();
                    finalResult = rhino.evaluateString(scriptable,str,"javascript",1,null).toString();
                }
                catch (Exception e)
                {
                    finalResult = "Error";
                }
                str=finalResult;
                break;
            case R.id.bt_mult:
                str += "X";
                break;
            case R.id.bt_div:
                if(str=="")
                    break;
                str +="/";
                break;
            case R.id.bt_per:
                if(str=="")
                    break;
                str += "%";
                break;
            case R.id.bt_sub:
                if(str=="-")
                    break;
                str +="-";
                break;
            case R.id.bt_add:
                str += "+";
                break;
            case R.id.bt_bracket:
                if(checkBracket)
                {
                    str +=")";
                    checkBracket=false;
                }else
                {
                   str +="(";
                   checkBracket=true;
                }
                break;
            case R.id.bt_AC:
                str="";
                break;
            case R.id.bt_dot:
                str +=".";
                break;

        }
        text.setText(str);
    }



}