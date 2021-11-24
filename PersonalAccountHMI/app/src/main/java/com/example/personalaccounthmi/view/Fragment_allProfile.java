package com.example.personalaccounthmi.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.dialogfragment.createDialog;
import com.example.personalaccounthmi.view.CustomAdapter;

import java.util.ArrayList;

import Common.IMyAidlInterface;


public class Fragment_allProfile extends Fragment {

    //declaring layout elements
    private ImageButton create;
    private TextView name;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public CustomAdapter adapter;
    private ArrayList<ProfileData> list;
    private IMyAidlInterface mCommon;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //creating View object to inflate fragment view
        View rootview = inflater.inflate(R.layout.fragment_allprofile, container , false);
        recyclerView=rootview.findViewById(R.id.recyclarview);

        Context context = rootview.getContext();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        create = rootview.findViewById(R.id.create);

        //function to be called to bind with service
        bindTOAIDLService(context);
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    list=(ArrayList<ProfileData>) mCommon.getAll();
                    Log.i("fragmnt","get");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                adapter=new CustomAdapter(context,list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
                // recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
        },2000);



        //on clicking creata button
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


        // Inflate the layout for this fragment
        return rootview;
    }

    //function to open the profile creating dialog
    private void openDialog() {
        createDialog dialog = new createDialog();
        dialog.show(getFragmentManager(),"CreateDialog");
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
