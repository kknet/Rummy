package com.gamegards.allinonev3.Comman;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gamegards.allinonev3.Interface.ApiRequest;
import com.gamegards.allinonev3.Interface.Callback;
import com.gamegards.allinonev3.R;
import com.gamegards.allinonev3.SampleClasses.Const;
import com.gamegards.allinonev3.Utils.Funtions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DialogForgetPassword {

    Context context;
    public DialogForgetPassword(Context context) {
        this.context = context;
    }

    public interface DealerInterface{

        void onClick(int pos);

    }

    public void showReportDialog() {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_forget_password);
        dialog.setTitle("Title...");

        TextView tv_heading = dialog.findViewById(R.id.tv_heading);

        tv_heading.setText("Forget Password!");

        final EditText edtForgetMobile = (EditText) dialog.findViewById(R.id.edtForgetMobile);

        dialog.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!Funtions.checkisStringValid(Funtions.getStringFromEdit(edtForgetMobile)))
                {
                    Funtions.showToast(context,"Please enter Mobile Number.");
                    return;
                }
                else if (Funtions.getStringFromEdit(edtForgetMobile).length() < 10)
                {
                    Funtions.showToast(context,"Please Valid Mobile Number.");
                    return;
                }

                CALL_API_ForgetPassword(Funtions.getStringFromEdit(edtForgetMobile));


                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.bt_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void CALL_API_ForgetPassword(String mobile){

        HashMap params = new HashMap();
        params.put("mobile",""+mobile);

        ApiRequest.Call_Api(context, Const.forgetpassword, params, new Callback() {
            @Override
            public void Responce(String resp, String type, Bundle bundle) {

                if(resp != null)
                {

                    try {
                        JSONObject jsonObject = new JSONObject(resp);
                        String code = jsonObject.optString("code");
                        String message = jsonObject.optString("message");

                        if(code.equals("200"))
                        {
                        }
                        Funtions.showToast(context,""+message);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }


}
