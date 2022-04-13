package com.gamegards.allinonev3.Comman;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gamegards.allinonev3.Activity.Homepage;
import com.gamegards.allinonev3.Activity.LoginScreen;
import com.gamegards.allinonev3.R;
import com.gamegards.allinonev3.Utils.Funtions;
import com.gamegards.allinonev3.Utils.Variables;


public class DialogSettingOption {

    Activity context;
    private Boolean isSound;

    public DialogSettingOption(Activity context) {
        this.context = context;
    }

    public interface DealerInterface {

        void onClick(int pos);

    }

    public void showDialogSetting() {
        final Dialog dialog = new Dialog(context,
                android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        dialog.setContentView(R.layout.custom_dialog_setting_home);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme; //style id
        dialog.setTitle("Title...");
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View imgclose = (View) dialog.findViewById(R.id.imgclosetop);
//
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });


//        (dialog.findViewById(R.id.btnReport)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                new DialogReport(context).showReportDialog();
//
//            }
//        });


        /*etho comment mai kita*/

//        ((TextView) dialog.findViewById(R.id.tv_playerid)).setText("Player ID : #"+ SharePref.getU_id());
//
        (dialog.findViewById(R.id.btnPrivacy)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DialogWebviewContents(context).showDialog(Variables.PRIVACY_POLICY);

            }
        });

        (dialog.findViewById(R.id.btnHowtoplay)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DialogHelpSupport(context).showHelpDialog();

                dialog.dismiss();
            }
        });

        (dialog.findViewById(R.id.btnTermscond)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DialogWebviewContents(context).showDialog(Variables.TERMS_CONDITION);

            }
        });


        (dialog.findViewById(R.id.btnHelpandsupport)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DialogWebviewContents(context).showDialog(Variables.SUPPORT);

                dialog.dismiss();
            }
        });

        (dialog.findViewById(R.id.lnrlogoutdia)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("user_id", "");
                editor.putString("name", "");
                editor.putString("mobile", "");
                editor.putString("token", "");
                editor.apply();
                Intent intent = new Intent(context, LoginScreen.class);
                context.startActivity(intent);
                context.finish();

                dialog.dismiss();
            }
        });

        dialog.show();

        ImageView switchd = (ImageView) dialog.findViewById(R.id.iv_sound);
        SharedPreferences prefs = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE);
        String value = prefs.getString("issoundon", "1");

        isSound=true;

        if (value.equals("0")) {
            isSound=false;
            //   switchd.setChecked(true);
            switchd.setImageResource(R.drawable.icon_sound_off);

        } else {
            isSound=true;
            switchd.setImageResource(R.drawable.icon_sound);
//            switchd.setChecked(false);
        }

        switchd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSound){
                    isSound = false;
                    switchd.setImageResource(R.drawable.icon_sound_off);
                    SharedPreferences.Editor editor = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("issoundon", "0");
                    editor.apply();
                }else{
                    isSound = true;
                    switchd.setImageResource(R.drawable.icon_sound);
                    SharedPreferences.Editor editor = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("issoundon", "1");
                    editor.apply();
                }

            }
        });


//        switchd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if (isChecked) {
//                    SharedPreferences.Editor editor = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
//                    editor.putString("issoundon", "0");
//                    editor.apply();
//
//
//                    // Toast.makeText(PublicTable.this, "On", Toast.LENGTH_LONG).show();
//
//                } else {
//                    // Toast.makeText(PublicTable.this, "Off", Toast.LENGTH_LONG).show();
//                    SharedPreferences.Editor editor = context.getSharedPreferences(Homepage.MY_PREFS_NAME, MODE_PRIVATE).edit();
//                    editor.putString("issoundon", "1");
//                    editor.apply();
//
//                }
//            }
//        });


        LinearLayout lnr_language = dialog.findViewById(R.id.lnr_language);
        lnr_language.removeAllViews();

        AddViewToLanguage(lnr_language, "English");

    }

    private void AddViewToLanguage(ViewGroup viewGroup, String text) {

        View view = Funtions.CreateDynamicViews(R.layout.item_language, viewGroup, context);

        TextView textView = view.findViewById(R.id.tv_language);
        textView.setText("" + text);
        textView.setTag(text);


    }


}
