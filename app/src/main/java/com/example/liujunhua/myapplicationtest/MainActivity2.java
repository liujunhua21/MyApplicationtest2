package com.example.liujunhua.myapplicationtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by liujunhua on 17-9-9.
 */

public class MainActivity2 extends AppCompatActivity {

    private TextView mTvDisplay2;
    private TextView mTvResult2;
    private StringBuilder mDisplayStr2 = new StringBuilder();
    private SharedPreferences mSp;
    private SharedPreferences.Editor mEditor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration newConfig = getResources().getConfiguration();

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_port2);
           // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏

        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_land2);

        }

        mTvDisplay2 = (TextView) findViewById(R.id.tv_display2);
        mTvResult2 = (TextView) findViewById(R.id.tv_result2);
        mTvDisplay2.setMovementMethod(ScrollingMovementMethod.getInstance());

        mSp = getApplication().getSharedPreferences("myInfo", 0);


        Map<String,String> map= (Map<String, String>) mSp.getAll();
        StringBuilder sb = new StringBuilder();
        mEditor= mSp.edit();
        for (String in : map.keySet()) {
            //map.keySet()返回的是所有key的值
            String str = map.get(in);//得到每个key多对用value的值
            sb.append(str.replace("=","\n=")+"\n");

        }



        mTvDisplay2.setText(sb.toString());

        Button digit_continue = (Button) findViewById(R.id.digit_continue);
        Button digit_cleanup = (Button) findViewById(R.id.digit_cleanup);

        digit_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        digit_cleanup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvDisplay2.setText("");
                mEditor.clear();
                mEditor.commit();

            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("display", mDisplayStr2.toString());
        outState.putString("result", mTvResult2.getText().toString());
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mDisplayStr2.append(savedInstanceState.getString("display"));
        mTvResult2.setText(savedInstanceState.getString("result"));
    }


}


