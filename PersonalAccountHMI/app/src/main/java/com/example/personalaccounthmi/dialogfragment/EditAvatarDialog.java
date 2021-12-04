/**
 * @file EditAvatarDialog.java
 * @brief This class describes the functionalities in the dialog fragment for editing the avatar image of a profile
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
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalaccounthmi.AvatarSelectListener;
import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.R;
import com.example.personalaccounthmi.view.AvatarAdapter;

import java.util.ArrayList;

public class EditAvatarDialog extends DialogFragment implements AvatarSelectListener {

    ArrayList<String> mAvatarList;
    RecyclerView recyclerView1;
    private LinearLayoutManager layoutManager;
    private AvatarAdapter avatarAdapter;
    String mAvatar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.editavatarlayout,container,false);
        ImageButton close = fragmentView.findViewById(R.id.closeAvatar);
        Button cancel = fragmentView.findViewById(R.id.cancelAvatar);
        Button save = fragmentView.findViewById(R.id.saveAvatar);
        recyclerView1 = fragmentView.findViewById(R.id.editAvatarList);
        Context context = getContext();

        layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);



        MainActivityInterface mainActivityInterface = MainActivityInterface.getInstance(getContext());
        try {
            mAvatarList = (ArrayList<String>) mainActivityInterface.getAvatarList();
        } catch (Exception e) {
            Log.i("Exception","did not get avatar list");
        }
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            avatarAdapter = new AvatarAdapter(context,mAvatarList, this);
            recyclerView1.setAdapter(avatarAdapter);
            recyclerView1.setLayoutManager(layoutManager);
        }, 200);






        close.setOnClickListener(v -> dismiss());

        cancel.setOnClickListener(v -> dismiss());


        save.setOnClickListener(v -> {
            if(mAvatar!=null){
                mainActivityInterface.updateProfileAvatar(mAvatar);
                Toast.makeText(getContext(), "AVATAR UPDATED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                dismiss();
            }else
                Toast.makeText(getContext(), "SELECT AVATAR", Toast.LENGTH_SHORT).show();
        });


        return fragmentView;
    }

    @Override
    public void onAvatarClick(String avatar) {
        mAvatar =avatar;
    }
}

