package com.example.personalaccounthmitrial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import Common.IMyAidlInterface;

public class MainActivity extends AppCompatActivity implements MainActivityInterface.View{
    MainActivityInterface.Presenter presenter;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private IMyAidlInterface mCommon;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapter adapter;
    private ArrayList<ProfileData> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        //get reference of tab layout and adapter class
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(viewPager);
        recyclerView=findViewById(R.id.recyclarview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //creating object of adapter class.
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Fragment_allProfile(),"ALL PROFILES");
        vpAdapter.addFragment(new Fragment_editprofile(),"EDIT PROFILES");
        viewPager.setAdapter(vpAdapter);
        bindTOAIDLService();
        presenter.loadAllProfiles();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                try {
                    list = (ArrayList<ProfileData>) mCommon.getAll();

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                adapter = new CustomAdapter(MainActivity.this,list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

        },500);
    }

    private void bindTOAIDLService() {
        Intent intent = new Intent("com.example.servicelist.MyService");
        intent.setClassName("com.example.servicelist","com.example.servicelist.MyService");
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        /*if (getBaseContext().getApplicationContext().bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)){
            Log.i("Binding","Success");
        }else{
            Log.i("Binding","failed");
        }*/

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