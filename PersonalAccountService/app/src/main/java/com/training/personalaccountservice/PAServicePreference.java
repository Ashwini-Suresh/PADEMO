/**
 * @file        PAServicePreference.java
 * @brief       PAServicePreference perform action on sharedPreference.
 *
 * @author      Akshay K B
 */
package com.training.personalaccountservice;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @brief: PAServicePreference perform action on sharedPreference. Here it is to store previous active profiles id
 */
public class PAServicePreference {

    /**
     * Storing class name to String TAG to addToPreference to file name of shared preference
     */
    private  static final String TAG=PAServicePreference.class.getSimpleName();

    /**
     * Declaring variable for Key to identify latest active id.
     */
    private int mLatest;

    /**
     * Declaring object of SharedPreference.
     */
    private final SharedPreferences mSharedPreferences;
    /**
     * Variable that hold object of PAServicePreference.
     */
    private static volatile PAServicePreference INSTANCE=null;

    /**
     * @brief: Singleton instance for PAServicePreference.
     * @param context:This context is required for creating SharedPreference.
     * @return : Return current instance.
     */
    public static PAServicePreference getInstance(Context context){
        if (INSTANCE==null){
            synchronized (PAServicePreference.class){
                if(INSTANCE==null)
                    INSTANCE=new PAServicePreference(context);
            }
        }
        return INSTANCE;
    }

    /**
     * @brief: Private constructor to make this class singleton.
     * @param context : Context is required for creating SharedPreference.
     */
    private PAServicePreference(Context context){
        mSharedPreferences = context.getSharedPreferences(context.getPackageName()+"."+TAG,Context.MODE_PRIVATE);
        //initialising latest as zero
        mLatest =0;
        //adding id 1 to preference as it is added to database on creation
        addToPreference(1);
    }

    /**
     * @brief: Adding profile id to shared preference
     * @param id : Profile id of active profile
     */
    public void addToPreference(int id){
        deleteIfPresent(id);
        if (mLatest == 4) {
            replaceData();
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putInt("prev" + mLatest, id);
            editor.apply();
        }else {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            mLatest++;
            editor.putInt("prev" + mLatest, id);
            editor.apply();

        }
    }

    /**
     * @brief: To delete if present on adding new id to preference.
     * @param id : Profile id to remove from sharedPreference data.
     */
    private void deleteIfPresent(int id) {
        for(int i=1;i<=mLatest;i++){
            if(i==mLatest && mSharedPreferences.getInt("prev"+i,0)==id){
                mLatest--;
            }else if(mSharedPreferences.getInt("prev"+i,0)==id){
                reArrange(i);
            }
        }

    }

    /**
     * @brief: Values from this keyNum will be moved one position down.
     * @param fromKeyNum : From which key value re arrange.
     */
    private void reArrange(int fromKeyNum) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for(int j = fromKeyNum; j<mLatest; j++) {
            editor.putInt("prev" + j, mSharedPreferences.getInt("prev" + (j + 1), 0));
            editor.apply();
        }
        mLatest--;
    }

    /**
     * @brief:  To get active profile id.
     * @return : Returns integer variable stored last.
     */
    public int getActiveId(){
            return mSharedPreferences.getInt("prev"+ mLatest,0);
        }

    /**
     * @brief: To maintain only four item is stored in preference.
     * On adding new, first element will be replaced by 2nd and so on
     * and this new will be added to last.
     * This method performs replacement part.
     */
    private void replaceData(){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        for(int i=1;i<=3;i++){
            editor.putInt("prev"+i,mSharedPreferences.getInt("prev"+(i+1),0));
            editor.apply();
        }
    }

    /**
     * @brief: To remove Last id which is active profile.
     */
    public void removeCurrentId() {
        mLatest--;
    }
}

