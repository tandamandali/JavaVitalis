package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;

public class MyDialog {
    private static AlertDialog dialog;

    public static void showDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null); // You can add OnClickListener for positive button if needed
        dialog = builder.create();
        dialog.show();
    }

    public static void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}

