package com.example.personalaccounthmi.dialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.personalaccounthmi.MainActivityInterface;
import com.example.personalaccounthmi.R;

public class createDialog extends PersonalAccountDialog {

    /**
     * declaring the layout elements
     */
    private ImageButton close;
    private ImageView avatar1;
    private ImageView avatar2;
    private ImageView avatar3;
    private ImageView avatar4;
    private ImageView avatar5;
    private ImageView avatar6;
    private ImageView avatar7;
    private ImageView avatar8;
    private Button cancel;
    private Button save;
    private EditText name;
    private String avatar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //View object is created to infleate the layout file in the fragment
        View rootview = inflater.inflate(R.layout.createdialog, container , false);
        close = rootview.findViewById(R.id.closebutton);
        cancel = rootview.findViewById(R.id.cancelbtn);
        save = rootview.findViewById(R.id.savebtn);
        name = rootview.findViewById(R.id.Name);
        avatar1 = rootview.findViewById(R.id.avatar1);
        avatar2 = rootview.findViewById(R.id.avatar2);
        avatar3 = rootview.findViewById(R.id.avatar3);
        avatar4 = rootview.findViewById(R.id.avatar4);
        avatar5 = rootview.findViewById(R.id.avatar5);
        avatar6 = rootview.findViewById(R.id.avatar6);
        avatar7 = rootview.findViewById(R.id.avatar7);
        avatar8 = rootview.findViewById(R.id.avatar8);

        /**
         * @brief creating object of MainactivityInterface which is a singleton class to access the bind service class with the fragment
         */
        MainActivityInterface mainActivityInterface = MainActivityInterface.getInstance(getContext());

        //on clicking avatar images the assignes string value is passed to database
        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { avatar = "avatar1"; }
        });

        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { avatar = "avatar2"; }
        });
        avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { avatar = "avatar3"; }
        });
        avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { avatar = "avatar4"; }
        });
        avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { avatar = "avatar4"; }
        });
        avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { avatar = "avatar5"; }
        });
        avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { avatar = "avatar6"; }
        });
        avatar7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { avatar = "avatar7"; }
        });
        avatar8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { avatar = "avatar8"; }
        });

        //on clicking the close button the current dialog is closed by calling the dismiss function
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //on clicking the cancel button the current dialog is closed
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        //on clicking the next button the string value entered is oassed to the avatar creating dialog
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(name.getText());

                /**
                 * addProfile function is used save the profile entered by the user and after saving the profle the dialog box will be dismissed
                 */

                mainActivityInterface.addProfile(username , avatar);
                Toast.makeText(getContext(), "Profile Saved succesfully", Toast.LENGTH_SHORT).show();
                dismiss();


            }
        });

        //return the fragment object
        return rootview;
    }

    public void show(Fragment parentFragment, String createDialog) {
    }
}
