package com.project.android.marrigelaws;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;


/**
 * Created by 13zqn on 2017/7/3.
 */

public class DialogUtil {
    public static void showDialog(final Context ctx, String msg, boolean goAdd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
                .setMessage(msg).setCancelable(false);
        if (goAdd)
        {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(ctx,LawyerServe.class);
                    ctx.startActivity(intent);
                }
            });
        }else{
            builder.setPositiveButton("确定",null);
        }
        builder.create().show();
    }
    public static void showDialog(Context ctx, View view){
        new AlertDialog.Builder(ctx).setView(view).setCancelable(false)
                .setPositiveButton("确定",null).create().show();
    }
}

