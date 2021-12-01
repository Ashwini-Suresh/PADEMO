/**
 * @file CreateProfileDialog
 * @brief The java class which contains the functionalities of the dialog fragment for creating a profile
 * @author Karthika V T
 */
package com.example.personalaccounthmi.dialogfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.AvatarSelectListener;
import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.view.AvatarAdapter;

import java.util.ArrayList;

public class createDialog extends DialogFragment implements AvatarSelectListener {


    private EditText name;
    private String avatar;
    ArrayList<String> mAvatarList;
    RecyclerView recyclerView1;
    private LinearLayoutManager layoutManager;
    private AvatarAdapter avatarAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        View rootView = inflater.inflate(R.layout.createdialog, container , false);


        ImageButton close = rootView.findViewById(R.id.closebutton);
        Button cancel = rootView.findViewById(R.id.cancelbtn);
        Button save = rootView.findViewById(R.id.savebtn);
        name = rootView.findViewById(R.id.Name);
        recyclerView1 = rootView.findViewById(R.id.avatarList);

        Context context = getContext();

        layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
       // recyclerView1.setHasFixedSize(true);



        MainActivityInterface mainActivityInterface = MainActivityInterface.getInstance(getContext());
        try {
            mAvatarList = (ArrayList<String>) mainActivityInterface.getAvatarList();
            Log.i("avatarList "," "+mAvatarList);
        } catch (Exception e) {
            Log.i("Exception","did not get avatar list");
        }
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            avatarAdapter = new AvatarAdapter(context,mAvatarList, this);
            recyclerView1.setAdapter(avatarAdapter);
            recyclerView1.setLayoutManager(layoutManager);
        }, 1000);




        close.setOnClickListener(v -> dismiss());


        cancel.setOnClickListener(v -> dismiss());




        save.setOnClickListener(v -> {
            String username = String.valueOf(name.getText());
            mainActivityInterface.addProfile(username , avatar);
            Toast.makeText(getContext(), "Profile Saved successfully", Toast.LENGTH_SHORT).show();
            dismiss();


        });

        return rootView;
    }

    @Override
    public void onAvatarClick(String avatar) {
        this.avatar = avatar;

    }

//    public void show(Fragment parentFragment, String createDialog) {
//    }
}
