package com.example.liujunhua.myapplicationtest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by topwise on 17-10-30.
 */
/*对于给定字符串显示成特定的字符串
* */
public class ShowUtils {
    private  static ShowUtils showUtils;
    private static int TMP_NUM = 0; //数字
    private static int TMP_PX = 1;//符号
    public  static  ShowUtils Interface(){
        if (showUtils==null){
            showUtils = new ShowUtils();
        }
        return  showUtils;
    }
    private List<TPWshow> mList = new ArrayList<>();
    public void setDisplayStr(StringBuilder stringBuilder){
        mList.clear();
        String str = stringBuilder.toString();
        //String  -- > list
        String tmp = "";
        for(int i = 0 ; i < stringBuilder.length() ; i++){
            boolean  cut = false;
            String px = "";
            switch (stringBuilder.charAt(i)){
                case '+':
                    cut = true;
                    px = "+";
                    break;
                case '-':
                    cut = true;
                    px = "-";
                    break;
                case '×':
                    cut = true;
                    px = "×";
                    break;
                case '÷':
                    cut = true;
                    px = "÷";
                    break;
                case '.':
                    cut = true;
                    px = ".";
                    break;
                default:
                    tmp=tmp+stringBuilder.charAt(i);
                    break;
            }
            if (cut){//代表一次符号开始
                mList.add(new TPWshow(tmp,TMP_NUM));
                mList.add(new TPWshow(px,TMP_PX));
                cut = false;
                tmp = "";
            }
        }
        mList.add(new TPWshow(tmp,TMP_NUM));
        Log.d("liujunhua","start ");
        for(int i = 0; i < mList.size() ; i++){

            Log.d("liujunhua",mList.size()+"-->"+mList.get(i).type_id+","+mList.get(i).pre);
        }
        Log.d("liujunhua","end ");

    }

    public String getDisplay(){
        String dis = "";
        for(int i = 0 ;i < mList.size(); i++){
            dis = dis +mList.get(i).getLast();
        }
        return dis;
    }
    

    private  class TPWshow{
        public  String pre;
        public  int type_id;
        String last;
        TPWshow(String pre,int id){
            this.pre = pre;
            this.type_id = id;
        }
        public  String getLast(){
            if(type_id == TMP_PX){
                return pre;
            }else {
                cualatue();
            }
            return last;
        }
        private  void cualatue(){
            int length = pre.length();
            String tmp="";
            int num = 0;
            for(int i =length-1; i>=0;i--) {
                num ++;
                if(num%3 == 0)
                    tmp=","+pre.charAt(i)+tmp;
                else tmp=pre.charAt(i)+tmp;
            }
            if (tmp.startsWith(",")) {
                tmp = tmp.substring(1, tmp.length());
            }
            last = tmp;


        }

    }
}
