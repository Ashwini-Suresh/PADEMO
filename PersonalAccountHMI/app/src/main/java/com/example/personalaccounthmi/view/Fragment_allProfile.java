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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.ProfileData;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.dialogfragment.createDialog;

import java.util.ArrayList;
import common.IPersonalAccount;


public class Fragment_allProfile extends Fragment {

    //declaring layout elements
    private ImageButton create;
    private TextView name;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public CustomAdapter adapter;
    private ArrayList<ProfileData> list;
   // private IPersonalAccount mIPersonalAccount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //creating View object to inflate fragment view
        View rootview = inflater.inflate(R.layout.fragment_allprofile, container , false);
        recyclerView=rootview.findViewById(R.id.recyclarview);

        //creating object of context
        Context context = rootview.getContext();

        //creating object of MainActivity Interface, a singleton class to pass bindService function
        MainActivityInterface mainActivityInterface =  MainActivityInterface.getInstance(context);

        layoutManager = new GridLayoutManager(context,4);
        recyclerView.setHasFixedSize(true);
        create = rootview.findViewById(R.id.create);


        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    list=(ArrayList<ProfileData>) mainActivityInterface.getAllProfile();
                    Log.i("fragmnt","get");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                adapter=new CustomAdapter(context,list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);
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


}
