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

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.settingsapplication.Common.SettingsConstants;
import com.example.settingsapplication.Presenter.MainActivityPresenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @brief This is the main class which implements the View ans onclick listner
 */
public class MainActivity extends AppCompatActivity implements MainAcitivityContract.View, View.OnClickListener {

    /**
     * creating object for presenter class
     */
    private static MainAcitivityContract.Presenter presenter;
    /**
     * Intitializing Buttons
     */
    private Button mAutoPlayStatusON;
    private Button mAutoplayStatusOFF;
    private Button mTimeON;
    private Button mTimeOFF;
    private Button mTheme1;
    private Button mTheme2;
    private Button mTheme3;
    private Button mDisplayManual;
    private Button mDisplayAutomatic;
    private Button mFM1;
    private Button mFM2;
    private Button mFM3;
    private Button mGoto;
    private RelativeLayout lyot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         *  Hide taskbar
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);


        /**
         * Creating presenter object
         */

        presenter = new MainActivityPresenter(this, getApplicationContext());

        /**
         * Finding all the ui elements
         */

        mAutoPlayStatusON = findViewById(R.id.buttonAutoPlayStatusOn);
        mAutoplayStatusOFF = findViewById(R.id.buttonAutoPlayStatusOff);
        mTimeON = findViewById(R.id.buttonTimeOn);
        mTimeOFF = findViewById(R.id.buttonTimeOFF);
        mTheme1 = findViewById(R.id.buttonTheme1);
        mTheme2 = findViewById(R.id.buttonTheme2);
        mTheme3 = findViewById(R.id.buttonTheme3);
        mDisplayAutomatic = findViewById(R.id.buttonDisplay_Automatic);
        mDisplayManual = findViewById(R.id.buttonDisplay_manual);
        mFM1 = findViewById(R.id.buttonFM1);
        mFM2 = findViewById(R.id.buttonFM2);
        mFM3 = findViewById(R.id.buttonFM3);
        Button mSave = findViewById(R.id.buttonSave);
        Button mRefresh = findViewById(R.id.buttonRefresh);
        mGoto = findViewById(R.id.buttonGoto);
       lyot=(RelativeLayout)findViewById(R.id.reltve1);

        /**
         *  When click on ave button it calls a  function in presenter class to save the updated settings.
         */
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.doSave();
            }
        });
        /**
         *  Click on a Refresh button ,it calls a function in presenter class to get saved settings
         */
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSettings();
            }
        });

        /**
         * when click on the goto HMI button it will takes the application to PersonalAccountHMI
         */
        mGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                PackageManager manager = getPackageManager();
                try {
                    i = manager.getLaunchIntentForPackage("com.example.personalaccounthmi");
                    if (i == null)
                        throw new PackageManager.NameNotFoundException();
                    i.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {

                }
            }
        });

    }

    /**
     * @param selected       : This is a selected Button in the ui
     * @param notSelectedOne : This is the unSelected Button in the ui
     * @param notSelectedTwo : This is the unSelected Button in the ui
     * @brief : The changeStatus function having 3 parameters to change the ui status
     * where one parameter is selected button and other two buttons are unselected.
     * when click on one button the status of that button indicated in green color
     * and others are in black color.
     */
    private void changeStatus(Button selected, Button notSelectedOne, Button notSelectedTwo) {
        selected.setBackgroundColor(Color.parseColor(SettingsConstants.GOLD_COLOR));
        selected.setSelected(true);

        notSelectedOne.setBackgroundColor(Color.parseColor(SettingsConstants.RED_COLOR));
        notSelectedOne.setSelected(false);
        if (notSelectedTwo != null) {
            notSelectedTwo.setBackgroundColor(Color.parseColor(SettingsConstants.RED_COLOR));
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
     * @param selected       : This is a selected Button in the ui
     * @param notSelectedOne : This is the unSelected Button in the ui
     * @param notSelectedTwo : This is the unSelected Button in the ui
     * @brief :The setStatus function having 3 parameters to set the ui status
     * where one parameter is selected button and other two buttons are unselected.
     * when click on one button the status of that button indicated in green color
     * and others are in black color.
     */

    public void setStatus(Button selected, Button notSelectedOne, Button notSelectedTwo) {

        Log.d("Main_Activity", "setStatus: Selected_button "+selected.getText());
        selected.setBackgroundColor(Color.parseColor(SettingsConstants.GOLD_COLOR));
        selected.setSelected(true);

        notSelectedOne.setBackgroundColor(Color.parseColor(SettingsConstants.RED_COLOR));
        notSelectedOne.setSelected(false);
        if (notSelectedTwo != null) {
            notSelectedTwo.setBackgroundColor(Color.parseColor(SettingsConstants.RED_COLOR));
            notSelectedTwo.setSelected(false);
        }
    }


    /**
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
                lyot.setBackgroundResource(R.drawable.img);
                break;
            case R.id.buttonTheme2:
                changeStatus(mTheme2, mTheme1, mTheme3);
                lyot.setBackgroundResource(R.drawable.theme2);
                break;
            case R.id.buttonTheme3:
                changeStatus(mTheme3, mTheme2, mTheme1);
                lyot.setBackgroundResource(R.drawable.theme3);
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


    /**
     * @brief: The onResume method calls the LoadSettings function to load the current
     * setting of SettingsApplication
     */
    protected void onResume() {

        super.onResume();
        loadSettings();
    }

    /**
     * @brief To load the settings of the SettingsApplication,This uses HAshMap to load Settings
     */
    public void loadSettings() {
        HashMap<String, String> mSettingsMap = presenter.getSettings();

        Set set = mSettingsMap.entrySet();
        for (Object o : set) {
            Map.Entry mentry = (Map.Entry) o;
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
            }
            else if (mentry.getKey().equals(DISPLAY)) {
                if (mentry.getValue().equals("Manual")) {

                   setStatus(mDisplayManual, mDisplayAutomatic, null);
                } else {
                    setStatus(mDisplayAutomatic, mDisplayManual, null);
                }
            }
            else if (mentry.getKey().equals(ACTIVE_THEME)) {
                if (mentry.getValue().equals("Theme 1")) {
                    setStatus(mTheme1, mTheme2, mTheme3);
                    lyot.setBackgroundResource(R.drawable.img);

                } else if (mentry.getValue().equals("Theme 2")) {
                    setStatus(mTheme2, mTheme1, mTheme3);
                    lyot.setBackgroundResource(R.drawable.theme2);
                } else  {
                    setStatus(mTheme3, mTheme2, mTheme1);
                    lyot.setBackgroundResource(R.drawable.theme3);
                }
            }
            else if (mentry.getKey().equals(FM)) {
                Log.d("MainActivity", "loadSettings:   FM condition Called");
                if (mentry.getValue().equals("BIG_FM")) {
                    setStatus(mFM1, mFM2, mFM3);
                } else if (mentry.getValue().equals("RADIO CITY")) {
                    setStatus(mFM2, mFM1, mFM3);
                } else  {
                    setStatus(mFM3, mFM1, mFM2);
                }
            }
        }
    }

}