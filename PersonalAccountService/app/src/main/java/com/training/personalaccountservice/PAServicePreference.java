package com.training.personalaccountservice;

import android.content.Context;
import android.content.SharedPreferences;

public class PAServicePreference {

    private  static final String TAG=PAServicePreference.class.getSimpleName();

    int prevNum;
    private final SharedPreferences sharedPreferences;

    private static volatile PAServicePreference INSTANCE=null;

    public static PAServicePreference getInstance(Context context){
        if (INSTANCE==null){
            synchronized (PAServicePreference.class){
                if(INSTANCE==null)
                    INSTANCE=new PAServicePreference(context);
            }
        }
        return INSTANCE;
    }
    private PAServicePreference(Context context){
        sharedPreferences= context.getSharedPreferences(context.getPackageName()+"."+TAG,Context.MODE_PRIVATE);
        prevNum=0;
        add(1);
    }


    void add(int id){
        if (prevNum == 4) {
            replaceData(sharedPreferences);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("prev" + prevNum, id);
            editor.apply();
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            prevNum++;
            editor.putInt("prev" + prevNum, id);
            editor.apply();

        }
    }
    int getActiveId(){
            return sharedPreferences.getInt("prev"+prevNum,0);
        }


    public void replaceData(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        for(int i=1;i<=3;i++){
            editor.putInt("prev"+i,preferences.getInt("prev"+(i+1),0));
            editor.apply();
        }
    }
}

