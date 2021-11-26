package com.example.personalaccounthmi.dialogfragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.personalaccounthmi.*;


import Common.IMyAidlInterface;

public class createAvatar extends PersonalAccountDialog {

    //declare layout elements
    private ImageButton close;
    private Button back;
    private Button save;
    private ImageView avatar1;
    private ImageView avatar2;
    private ImageView avatar3;
    private IMyAidlInterface mCommon;
    private String username;
    private String avatar;

    /**
     * crating constructor for the createAvatar class.
     * @param username is passed from the createDialog class to this class and is saved in the database
     */
    public createAvatar(String username) {
        this.username = username;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.createavatar,container,false);
        close = rootview.findViewById(R.id.close);
        back = rootview.findViewById(R.id.back);
        save = rootview.findViewById(R.id.save);
        avatar1 = rootview.findViewById(R.id.avatar1);
        avatar2 = rootview.findViewById(R.id.avatar2);
        avatar3 = rootview.findViewById(R.id.avatar3);

        bindTOAIDLService(getContext());



        //on clicking close button the current dialog is closed
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

                /**
                 * this function is declared in PersonalAccountDialog class to the all dialogs.
                 */
                Dismiss();
            }
        });

        //on clicking avatar images the assignes string value is passed to database
        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = "avatar1";
            }
        });
        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = "avatar3";
            }
        });
        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = "avatar2";
            }
        });

        //on clicking back button the current dialog is closed and the previous dialog is shown
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //on clicking save button the entered usernama and the corresponding string of the image will be passed to the databaase
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mCommon.addProfile(username , avatar);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getContext(), "Profile Saved succesfullu", Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });

        //return fragment view
        return rootview;
    }


    /**
     * @brief this function is called bind the HMI application with the service application. The communication between the application is done by the Stub object
     * @param context
     */
    private void bindTOAIDLService(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.example.servicelist","com.example.servicelist.MyService");
        context.bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);

    }
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mCommon = IMyAidlInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}

