package com.example.settingsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainAcitivityContract.View {

    MainAcitivityContract.Presenter presenter;
    EditText mFMStation;
    Button mAutoPlayStatusON,mAutoplayStatusOFF,mTimeON,mTimeOFF,mTheme1,mTheme2,mTheme3,mDisplayManual,mDisplayAutomatic,mSave,mRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide taskbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //creating presenter
        presenter=new MainActivityPresenter(this);


        // finding ui elements
        mFMStation=(EditText) findViewById(R.id.edittextFm);
        mAutoPlayStatusON=(Button) findViewById(R.id.buttonAutoPlayStatusOn);
        mAutoplayStatusOFF=(Button) findViewById(R.id.buttonAutoPlayStatusOff);
        mTimeON=(Button) findViewById(R.id.buttonTimeOn);
        mTimeOFF=(Button) findViewById(R.id.buttonTimeOFF);
        mTheme1=(Button) findViewById(R.id.buttonTheme1);
        mTheme2=(Button) findViewById(R.id.buttonTheme2);
        mTheme3=(Button) findViewById(R.id.buttonTheme3);
        mDisplayAutomatic=(Button) findViewById(R.id.buttonDisplay_Automatic);
        mDisplayManual=(Button) findViewById(R.id.buttonDisplay_manual);
        mSave=(Button) findViewById(R.id.buttonSave);
        mRefresh=(Button) findViewById(R.id.buttonRefresh);


       // click save
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String fm=mFMStation.getText().toString();
                        presenter.doSave(fm);

            }
        });

        //click Refresh Button
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String r=mFMStation.getText().toString();
                presenter.doRefresh(r);
            }
        });

    }

    @Override
    public void onSave(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }


}