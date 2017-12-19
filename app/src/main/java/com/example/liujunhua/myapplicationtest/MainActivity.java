package com.example.liujunhua.myapplicationtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.util.MeasureUnit;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;
import org.javia.arity.Util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = "MainActivity";


    private TextView mTvDisplay;
    private TextView mTvResult;
    private String ERROR="表达式错误";
    private StringBuilder mDisplayStr = new StringBuilder();
    private Symbols mSymbols;
    private String writeStr = "";
    private SharedPreferences mSp;
    private SharedPreferences.Editor mEditor;
    double result =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration newConfig = getResources().getConfiguration();

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_port1);

        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_land1);
        }

        initView();
        mSymbols = new Symbols();


    }

    private void initView() {
        mTvDisplay = (TextView) findViewById(R.id.tv_display);
        mTvDisplay.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTvResult = (TextView) findViewById(R.id.tv_result);
        mSp = getApplication().getSharedPreferences("myInfo", 0);
        String name = mSp.getString("name", "");
        mEditor= mSp.edit();
        mTvDisplay.setText(name);

        mEditor.putString("name", mDisplayStr.toString());
        mEditor.commit();

        Button digit_0 = (Button) findViewById(R.id.digit_0);
        Button digit_1 = (Button) findViewById(R.id.digit_1);
        Button digit_2 = (Button) findViewById(R.id.digit_2);
        Button digit_3 = (Button) findViewById(R.id.digit_3);
        Button digit_4 = (Button) findViewById(R.id.digit_4);
        Button digit_5 = (Button) findViewById(R.id.digit_5);
        Button digit_6 = (Button) findViewById(R.id.digit_6);
        Button digit_7 = (Button) findViewById(R.id.digit_7);
        Button digit_8 = (Button) findViewById(R.id.digit_8);
        Button digit_9 = (Button) findViewById(R.id.digit_9);
        Button digit_point = (Button) findViewById(R.id.digit_point);
        Button digit_equals = (Button) findViewById(R.id.digit_equals);
        Button digit_del = (Button) findViewById(R.id.digit_del);
        Button digit_plus = (Button) findViewById(R.id.digit_plus);
        Button digit_reduce = (Button) findViewById(R.id.digit_reduce);
        Button digit_ride = (Button) findViewById(R.id.digit_ride);
        Button digit_except = (Button) findViewById(R.id.digit_except);
        Button digit_clear = (Button) findViewById(R.id.digit_clear);
        Button digit_nagetive = (Button) findViewById(R.id.digit_nagetive);
        Button digit_down =(Button) findViewById(R.id.digit_down);


        digit_0.setOnClickListener(this);
        digit_1.setOnClickListener(this);
        digit_2.setOnClickListener(this);
        digit_3.setOnClickListener(this);
        digit_4.setOnClickListener(this);
        digit_5.setOnClickListener(this);
        digit_6.setOnClickListener(this);
        digit_7.setOnClickListener(this);
        digit_8.setOnClickListener(this);
        digit_9.setOnClickListener(this);
        digit_point.setOnClickListener(this);
        digit_equals.setOnClickListener(this);
        digit_del.setOnClickListener(this);
        digit_plus.setOnClickListener(this);
        digit_reduce.setOnClickListener(this);
        digit_ride.setOnClickListener(this);
        digit_except.setOnClickListener(this);
        digit_clear.setOnClickListener(this);
        digit_nagetive.setOnClickListener(this);
        digit_del.setOnLongClickListener(this);
        digit_down.setOnClickListener(this);




    }




    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("display", ShowUtils.Interface().getDisplay());
        outState.putString("result", mTvResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mDisplayStr.append(savedInstanceState.getString("display"));
        mTvDisplay.setText(mDisplayStr.toString());
        mTvResult.setText(savedInstanceState.getString("result"));
    }

    @Override
    public boolean onLongClick(View v) {
        if (v instanceof Button){
            Button button = (Button)v;
            switch (button.getId()){
                case R.id.digit_del:
                    mDisplayStr = new StringBuilder();
                    mTvDisplay.setText("");
                    break;
            }
        }
        return false;
    }


    @Override
    public void onClick(View v) {

        if (v instanceof Button){
            Button button = (Button)v;

            switch(button.getId()){
                case R.id.digit_0:
                    mDisplayStr.append("" + 0);
                   // mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_1:
                    mDisplayStr.append("" + 1);
                    //mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_2:
                    mDisplayStr.append("" + 2);
                    //mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_3:
                    mDisplayStr.append("" + 3);
                    //mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_4:
                    mDisplayStr.append("" + 4);
                    //mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_5:
                    mDisplayStr.append("" + 5);
                    //mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_6:
                    mDisplayStr.append("" + 6);
                    //mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_7:
                    mDisplayStr.append("" + 7);
                   // mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_8:
                    mDisplayStr.append("" + 8);
                    //mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_9:
                    mDisplayStr.append("" + 9);
                    //mTvDisplay.setText(addComma(mDisplayStr));
                    break;
                case R.id.digit_point:
                    writeStr = ".";
                    if (testSymbol())
                        mDisplayStr.append(".");
                    //mTvDisplay.setText(mDisplayStr);
                    break;
                case R.id.digit_nagetive:
                    mDisplayStr.append(""+"-");
                    //mTvDisplay.setText(mDisplayStr);
                    break;
                case R.id.digit_clear:
                    clear();
                    break;
                case R.id.digit_equals:
                    equals();
                    break;
                case R.id.digit_del:
                    if (mDisplayStr.length() > 0)
                        mDisplayStr.deleteCharAt(mDisplayStr.length()-1);
                    break;
                case R.id.digit_plus:
                    if (testSymbol())
                        mDisplayStr.append("+");
                   // mTvDisplay.setText(mDisplayStr);
                    break;
                case R.id.digit_reduce:
                    if (testSymbol())
                        mDisplayStr.append("-");
                   // mTvDisplay.setText(mDisplayStr);
                    break;
                case R.id.digit_ride:
                    writeStr = "×";
                    if (testSymbol())
                        mDisplayStr.append("×");
                    //mTvDisplay.setText(mDisplayStr);
                    break;
                case R.id.digit_except:
                    writeStr = "÷";
                    if (testSymbol())
                        mDisplayStr.append("÷");
                    //mTvDisplay.setText(mDisplayStr);
                    break;
                case R.id.digit_down:

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);

            }

            writeStr = "";
            //Log.d("liujunhua1011","--->:"+mDisplayStr);
            //setDisplay(mDisplayStr);
/*
            DecimalFormat df = new DecimalFormat("###,###");
            int i = Integer.parseInt(mDisplayStr.toString());
            String m = df.format(i);
            //System.out.println(m);
            mTvDisplay.setText(m);
            //mTvDisplay.setText(df.format(mDisplayStr.toString()));
            //mTvDisplay.setText(mDisplayStr.toString());
            */
            //mTvDisplay.setText(mDisplayStr);
            //inputWithCommaListener(mTvDisplay);
            //addComma(mDisplayStr);
            ShowUtils.Interface().setDisplayStr(mDisplayStr);
            mTvDisplay.setText(ShowUtils.Interface().getDisplay());

        }
    }

    //static List<String> stringList = new ArrayList<>();
//    private static String addComma(String str) {
//        // 将传进数字反转
//        String reverseStr = new StringBuilder(str).reverse().toString();
//        String strTemp = "";
//        for (int i=0; i<reverseStr.length(); i++) {
//
//            if (i*3+3 > reverseStr.length()){
//                strTemp += reverseStr.substring(i*3,reverseStr.length());
//                break;
//            }
//            strTemp += reverseStr.substring(i*3, i*3+3)+",";
//            char c = reverseStr.charAt(i);
//            if (c == '+' ||
//                    c == '-' ||
//                    c == '×' ||
//                    c == '÷'){
//
//                stringList.add(strTemp);
//                stringList.add("" + c);
//                break;
//            }
//            strTemp += reverseStr.substring(i*3, i*3+3)+",";
//        }
        // 将[789,456,] 中最后一个[,]去除
//        if (strTemp.endsWith(",")) {
//            strTemp = strTemp.substring(0, strTemp.length()-1);
//        }
//        // 将数字重新反转
//        String resultStr = new StringBuilder(strTemp).reverse().toString();
//        return resultStr;
//    }

    private static final int MAX_DIGITS = 20;

    private static final int ROUNDING_DIGITS = Math.max(17 - MAX_DIGITS, 0);

//    public static void inputWithCommaListener(final TextView textView) {
//
//        textView.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before,
//                                      int count) {
//
//                if (count != before) {
//                    String sss = "";
//                    String string = s.toString().replace(",", "");
//                    int b = string.length() / 3;
//                    if (string.length() >= 3 ) {
//                        int yushu = string.length() % 3;
//                        if (yushu == 0) {
//                            b = string.length() / 3 - 1;
//                            yushu = 3;
//                        }
//                        for (int i = 0; i < b; i++) {
//                            sss = sss + string.substring(0, yushu) + "," + string.substring(yushu, 3);
//                            string = string.substring(3, string.length());
//                        }
//                        sss = sss + string;
//                        textView.setText(sss);
//                    }
//                }
//                textView.setText(textView.getText().length());
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count,
//                                          int after) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                // TODO Auto-generated method stub
//            }
//
//        });
    //}


//    private void setDisplay(StringBuilder mDisplayStr) {
//        DecimalFormat df = new DecimalFormat("###,###");
//        String str = mDisplayStr.toString();
//        String totalStr = "";
//        String numStr = "";
//        boolean falg = false ; // 数字是否结束
//        boolean singleFlag = false;//符号结束符
//        for( int i = 0 ; i < str.length();i++){
//            switch (str.charAt(i)){
//                case '0':
//                case '1':
//                case '2':
//                case '3':
//                case '4':
//                case '5':
//                case '6':
//                case '7':
//                case '8':
//                case '9':
//                    falg = false;
//                    singleFlag = false;
//                    numStr = numStr+str.charAt(i);
//                    break;
//                case '÷':
//                case '-':
//                case '×':
//                case '+':
//                    falg = true;
//                    singleFlag = true;
//                    break;
//            }
//            if(i == str.length()-1) {
//                falg = true;
//            }
//
//
//            if(falg){
//                Log.d("liujunhua1011",":"+numStr.toString()+":");
//                int intt = Integer.valueOf(numStr.toString().trim());
//
//                String m = df.format(intt);
//                numStr = "";
//                totalStr = totalStr+m;
//                if(singleFlag) totalStr += str.charAt(i);
//            }
//
//
//        }
//
//        mTvDisplay.setText(totalStr);
//
//    }


    private boolean testSymbol() {
        //测试“.”
        if (writeStr.equals(".")){
            if (mDisplayStr.length() == 0){
                return true;
            } else {
                String opt = mDisplayStr.toString();
                for (int i = opt.length()-1;i >= 0;i --){
                    String c = String.valueOf(opt.charAt(i));

                    if (c.equals(".") || c.equals(")")){
                        return false;
                    }
                    if (isSympols(c)){
                        return true;
                    }
                }
                return true;
            }

        }


        if (mDisplayStr.length() == 0){
            if (writeStr.equals("×") || writeStr.equals("÷"))
                return false;

            return true;
        }
        String digit = mDisplayStr.substring(mDisplayStr.length() - 1, mDisplayStr.length());
        if (isSympols(digit)){
            return false;
        }
        return true;
    }

    private boolean isSympols(String digit){
        if (digit.equals("+") || digit.equals("-") || digit.equals("×") || digit.equals("÷")){
            return true;
        }

        return false;
    }


    private  void clear() {
        mDisplayStr = new StringBuilder();
        mTvDisplay.setText("");
        mTvResult.setText("0");
    }


    private int mNum = 0;
    private void equals() {

        try {
            result = mSymbols.eval(mDisplayStr.toString());
            String resultString = Util.doubleToString(result, MAX_DIGITS, ROUNDING_DIGITS);
            ShowresultUtils.Interface().setDisplayStr(resultString);
            mTvResult.setText("=" + ShowresultUtils.Interface().getDisplay());
            Log.i("liujunhua", "result 1== " + result);
            Log.i("liujunhua", "result 2== " + resultString);
            mEditor.putString("name"+mNum++,ShowUtils.Interface().getDisplay()+"="+ShowresultUtils.Interface().getDisplay());
            mEditor.commit();
        } catch (SyntaxException e) {
            Log.i(TAG, e.toString());
            mTvResult.setText(ERROR);
            mEditor.putString("name"+mNum++,mDisplayStr+"="+ERROR);
            mEditor.commit();


        }



    }





}
