package com.example.personalaccounthmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;

import com.google.android.material.tabs.TabLayout;

import Common.IMyAidlInterface;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MainactivityInterface.View{

    MainactivityInterface.Presenter presenter;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private IMyAidlInterface mCommon;
    private RecyclarAdapter adapter;
    private ArrayList<ProfileData> list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        //get reference of tab layout and adapter class
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);
        recyclerView = findViewById(R.id.recyclarview);
        layoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        // It is used to join TabLayout with ViewPager.
        tabLayout.setupWithViewPager(viewPager);

        //creating object of adapter class.
        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Fragment1(),"ALL PROFILES");
        vpAdapter.addFragment(new Fragment2(),"EDIT PROFILES");
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
                adapter = new RecyclarAdapter(MainActivity.this,list);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

        },500);
    }

    private void bindTOAIDLService() {
        Intent intent = new Intent("com.training.personalaccountservice.ServiceImp");
        intent.setClassName("com.training.personalaccountservice","com.training.personalaccountservice.ServiceImp");
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
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