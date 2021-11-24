package com.example.personalaccounthmi.dialogfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.personalaccounthmi.dialogfragment.PersonalAccountDialog;
import com.example.personalaccounthmi.R;

public class createDialog extends PersonalAccountDialog {

    /**
     * declaring the layout elements
     */
    private ImageButton close;
    private Button cancel;
    private Button next;
    private EditText name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //View object is created to infleate the layout file in the fragment
        View rootview = inflater.inflate(R.layout.createdialog, container , false);
        close = rootview.findViewById(R.id.closebutton);
        cancel = rootview.findViewById(R.id.cancelbtn);
        next = rootview.findViewById(R.id.nextbtn);
        name = rootview.findViewById(R.id.Name);

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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(name.getText());

                /**
                 * this function used to create the avatar selecting dialog the entered username is passed to the function
                 */
                openAvatar(username);

            }
        });

        //return the fragment object
        return rootview;
    }

    public void show(Fragment parentFragment, String createDialog) {
    }
}
