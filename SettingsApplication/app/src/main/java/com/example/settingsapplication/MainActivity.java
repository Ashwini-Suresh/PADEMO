/**
 * @file        MainActivity.java
 * @brief       This class handle all actions related to SettingsApplication.
 *              This includes handling the view and presenter class and whole actions.
 *
 * @author      Ashwini.
 */
package com.example.settingsapplication;

import static com.example.settingsapplication.Common.SettingsConstants.ACTIVE_THEME;
import static com.example.settingsapplication.Common.SettingsConstants.AUTO_PLAY_STATUS;
import static com.example.settingsapplication.Common.SettingsConstants.DISPLAY;
import static com.example.settingsapplication.Common.SettingsConstants.FM;
import static com.example.settingsapplication.Common.SettingsConstants.TIME_FORMAT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.settingsapplication.Common.SettingsConstants;

public class MainActivity extends AppCompatActivity implements MainAcitivityContract.View, View.OnClickListener {

    /**
     * @brief     This variable holds the value for presenter..
     */
    MainAcitivityContract.Presenter presenter;

    EditText mFMStation;
    Button mAutoPlayStatusON, mAutoplayStatusOFF, mTimeON, mTimeOFF, mTheme1, mTheme2, mTheme3,
            mDisplayManual, mDisplayAutomatic, mSave, mRefresh,mFM1,mFM2,mFM3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * hide taskbar
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        /**
         * @brief    creating presenter object
         */
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

                String fm = mFMStation.getText().toString();
                presenter.doSave(fm);

            }
        });

        /**
         * @brief the action perform when click the refresh button
         */
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String r = mFMStation.getText().toString();
                presenter.doRefresh(r);
            }
        });

    }


    /**
     * @param selected
     * @param notSelectedOne
     * @param notSelectedTwo
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
        switch ( selected.getId()) {
            case R.id.buttonAutoPlayStatusOn:
            case R.id.buttonAutoPlayStatusOff:
                key=AUTO_PLAY_STATUS;
                break;
            case R.id.buttonTimeOn:
            case R.id.buttonTimeOFF:
                key=TIME_FORMAT;
                break;
            case R.id.buttonTheme1:
            case R.id.buttonTheme2:
            case R.id.buttonTheme3:
                key=ACTIVE_THEME;
                break;
            case R.id.buttonDisplay_manual:
            case R.id.buttonDisplay_Automatic:
                key=DISPLAY;
                break;
            case R.id.buttonFM1:
            case R.id.buttonFM3:
            case R.id.buttonFM2:
                key=FM;
                break;

        }
                presenter.setSettingsStatus(key, settingsStatus);

        }
      //  setting1, settings2, settings3


    private void setSettingsStatus(String settingsKey, String settingsValue) {


    }


    @Override
    public void onSave(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    /**
     *  button click
     * @param view
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
                changeStatus(mTimeOFF,mTimeON , null);
                break;
            case R.id.buttonTheme1:
                changeStatus(mTheme1, mTheme2,mTheme3);
                break;
            case R.id.buttonTheme2:
                changeStatus(mTheme2, mTheme1,mTheme3);
                break;
            case R.id.buttonTheme3:
                changeStatus(mTheme3, mTheme2,mTheme1);
                break;
            case R.id.buttonDisplay_manual:
                changeStatus(mDisplayManual,mDisplayAutomatic , null);
                break;
            case R.id.buttonDisplay_Automatic:
                changeStatus(mDisplayAutomatic,mDisplayManual , null);
                break;
            case R.id.buttonFM1:
                changeStatus(mFM1, mFM2,mFM3);
                break;
            case R.id.buttonFM2:
                changeStatus(mFM2,mFM1,mFM3);
                break;
            case R.id.buttonFM3:
                changeStatus(mFM3,mFM1,mFM2);
                break;

            default:

        }
    }
}