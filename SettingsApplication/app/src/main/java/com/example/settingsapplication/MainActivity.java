/**
 * @file MainActivity.java
 * @brief This class handle all actions related to SettingsApplication.
 * This includes handling the view and presenter class and whole actions.
 * @author Ashwini.
 */
package com.example.settingsapplication;

import static com.example.settingsapplication.Common.SettingsConstants.ACTIVE_THEME;
import static com.example.settingsapplication.Common.SettingsConstants.AUTO_PLAY_STATUS;
import static com.example.settingsapplication.Common.SettingsConstants.DISPLAY;
import static com.example.settingsapplication.Common.SettingsConstants.FM;
import static com.example.settingsapplication.Common.SettingsConstants.TIME_FORMAT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.settingsapplication.Common.SettingsConstants;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import common.IPersonalAccount;

/**
 * @brief This is the main class
 */
public class MainActivity extends AppCompatActivity implements MainAcitivityContract.View, View.OnClickListener {

    // create object for presenter
    MainAcitivityContract.Presenter presenter;
    IPersonalAccount mCommon;
    // Intitializing Button
    private Button mAutoPlayStatusON;
    private Button mAutoplayStatusOFF;
    private Button mTimeON;
    private Button mTimeOFF;
    private Button mTheme1;
    private Button mTheme2;
    private Button mTheme3;
    private Button mDisplayManual;
    private Button mDisplayAutomatic;
    private Button mSave;
    private Button mRefresh;
    private Button mFM1;
    private Button mFM2;
    private Button mFM3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide taskbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        //creating presenter object

        presenter = new MainActivityPresenter(this);

        /**
         * finding ui elements
         */

        mAutoPlayStatusON = (Button) findViewById(R.id.buttonAutoPlayStatusOn);
        mAutoplayStatusOFF = (Button) findViewById(R.id.buttonAutoPlayStatusOff);
        mTimeON = (Button) findViewById(R.id.buttonTimeOn);
        mTimeOFF = (Button) findViewById(R.id.buttonTimeOFF);
        mTheme1 = (Button) findViewById(R.id.buttonTheme1);
        mTheme2 = (Button) findViewById(R.id.buttonTheme2);
        mTheme3 = (Button) findViewById(R.id.buttonTheme3);
        mDisplayAutomatic = (Button) findViewById(R.id.buttonDisplay_Automatic);
        mDisplayManual = (Button) findViewById(R.id.buttonDisplay_manual);
        mFM1 = (Button) findViewById(R.id.buttonFM1);
        mFM2 = (Button) findViewById(R.id.buttonFM2);
        mFM3 = (Button) findViewById(R.id.buttonFM3);
        mSave = (Button) findViewById(R.id.buttonSave);
        mRefresh = (Button) findViewById(R.id.buttonRefresh);


        /**
         * @brief click on save button
         */
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //defining finction to bind with service

            Intent intent = new Intent();
            intent.setClassName("com.example.servicelist","com.example.servicelist.MyService");
            //bindService(intent,serviceConnection,BIND_AUTO_CREATE);
            if (getApplicationContext().bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)){
                Log.i("Binding","Success");
            }else{
                Log.i("Binding","failed");
            }





        /**
         * @brief the action perform when click on the refresh button
         */
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mCommon = IPersonalAccount.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    /**
     * @param selected       : This is a selected Button in the ui
     * @param notSelectedOne : This is the unSelected Button in the ui
     * @param notSelectedTwo : This is the unSelected Button in the ui
     * @brief The changeStatus function having 3 parameters to change the ui status
     * where one parameter is selected button and other two buttons are unselected.
     * when click on one button the status of that button indicated in green color
     * and others are in black color.
     */
    private void changeStatus(Button selected, Button notSelectedOne, Button notSelectedTwo) {
        selected.setBackgroundColor(Color.parseColor(SettingsConstants.GREEN_COLOR));
        selected.setSelected(true);

        notSelectedOne.setBackgroundColor(Color.parseColor(SettingsConstants.BLACK_COLOR));
        notSelectedOne.setSelected(false);
        if (notSelectedTwo != null) {
            notSelectedTwo.setBackgroundColor(Color.parseColor(SettingsConstants.BLACK_COLOR));
            notSelectedTwo.setSelected(false);
        }
        String key = null;
        String settingsStatus = (String) selected.getText();
        switch (selected.getId()) {
            case R.id.buttonAutoPlayStatusOn:
            case R.id.buttonAutoPlayStatusOff:
                key = AUTO_PLAY_STATUS;
                break;
            case R.id.buttonTimeOn:
            case R.id.buttonTimeOFF:
                key = TIME_FORMAT;
                break;
            case R.id.buttonTheme1:
            case R.id.buttonTheme2:
            case R.id.buttonTheme3:
                key = ACTIVE_THEME;
                break;
            case R.id.buttonDisplay_manual:
            case R.id.buttonDisplay_Automatic:
                key = DISPLAY;
                break;
            case R.id.buttonFM1:
            case R.id.buttonFM3:
            case R.id.buttonFM2:
                key = FM;
                break;

        }
        presenter.setSettingsStatus(key, settingsStatus);

    }

    /**
     *
     * @param settingsKey
     * @param settingsValue :
     */
    private void setSettingsStatus(String settingsKey, String settingsValue) {


    }


    public void setStatus(Button selected, Button notSelectedOne, Button notSelectedTwo) {
        selected.setBackgroundColor(Color.parseColor(SettingsConstants.GREEN_COLOR));
        selected.setSelected(true);

        notSelectedOne.setBackgroundColor(Color.parseColor(SettingsConstants.BLACK_COLOR));
        notSelectedOne.setSelected(false);
        if (notSelectedTwo != null) {
            notSelectedTwo.setBackgroundColor(Color.parseColor(SettingsConstants.BLACK_COLOR));
            notSelectedTwo.setSelected(false);
        }
    }


    @Override
    public void onSave(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

   @Override
    public void loadSettings() {

    }


    /**
     * @param view
     * @brief This is the onclick function to handle the function for clicking a button
     * here when a button is pressed that button id is passed in the switch statement
     * then calling the changeStatus function to change the status
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAutoPlayStatusOn:
                changeStatus(mAutoPlayStatusON, mAutoplayStatusOFF, null);
                break;
            case R.id.buttonAutoPlayStatusOff:
                changeStatus(mAutoplayStatusOFF, mAutoPlayStatusON, null);
                break;
            case R.id.buttonTimeOn:
                changeStatus(mTimeON, mTimeOFF, null);
                break;
            case R.id.buttonTimeOFF:
                changeStatus(mTimeOFF, mTimeON, null);
                break;
            case R.id.buttonTheme1:
                changeStatus(mTheme1, mTheme2, mTheme3);
                break;
            case R.id.buttonTheme2:
                changeStatus(mTheme2, mTheme1, mTheme3);
                break;
            case R.id.buttonTheme3:
                changeStatus(mTheme3, mTheme2, mTheme1);
                break;
            case R.id.buttonDisplay_manual:
                changeStatus(mDisplayManual, mDisplayAutomatic, null);
                break;
            case R.id.buttonDisplay_Automatic:
                changeStatus(mDisplayAutomatic, mDisplayManual, null);
                break;
            case R.id.buttonFM1:
                changeStatus(mFM1, mFM2, mFM3);
                break;
            case R.id.buttonFM2:
                changeStatus(mFM2, mFM1, mFM3);
                break;
            case R.id.buttonFM3:
                changeStatus(mFM3, mFM1, mFM2);
                break;

            default:

        }
    }

    protected void onResume() {

        super.onResume();
        loadSettings();
    }

   /* public void loadSettings() {
        HashMap<String, String> mSettingsMap = presenter.getSettings();

        Set set = mSettingsMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key is: " + mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
            if (mentry.getKey().equals(AUTO_PLAY_STATUS)) {
                if (mentry.getValue().equals("ON")) {
                    setStatus(mAutoPlayStatusON, mAutoplayStatusOFF, null);


                } else {
                    setStatus(mAutoplayStatusOFF, mAutoPlayStatusON, null);
                }
            } else if (mentry.getKey().equals(TIME_FORMAT)) {
                if (mentry.getValue().equals("ON")) {
                    setStatus(mTimeON, mTimeOFF, null);


                } else {
                    setStatus(mTimeOFF, mTimeON, null);

                }

                if (mentry.getKey().equals(ACTIVE_THEME)) {
                    if (mentry.getValue().equals("THEME1")) {
                        setStatus(mTheme1, mTheme2, mTheme3);


                    } else if (mentry.getValue().equals("THEME2")) {
                        setStatus(mTheme2, mTheme1, mTheme3);
                    } else {
                        setStatus(mTheme3, mTheme2, mTheme1);
                    }
                }
                if (mentry.getKey().equals(DISPLAY)) {
                    if (mentry.getValue().equals("MANUAL")) {
                        setStatus(mDisplayManual, mDisplayAutomatic, null);


                    } else {
                        setStatus(mDisplayAutomatic, mDisplayManual, null);
                    }
                }
                if (mentry.getKey().equals(FM)) {
                    if (mentry.getValue().equals("BIG_FM")) {
                        setStatus(mFM1, mFM2, mFM3);


                    } else if (mentry.getValue().equals("RADIO_CITY")) {
                        setStatus(mFM2, mFM1, mFM3);
                    } else {
                        setStatus(mFM3, mFM1, mFM2);
                    }
                }
            }

        }
    }*/


}