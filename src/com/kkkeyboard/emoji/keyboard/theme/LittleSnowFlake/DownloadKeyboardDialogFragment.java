package com.kkkeyboard.emoji.keyboard.theme.LittleSnowFlake;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by xuebin on 14-6-18.
 */
public class DownloadKeyboardDialogFragment extends DialogFragment {
    private static final String KEYBOARD_PACKAGE_NAME = "com.kitkatandroid.keyboard";
    public static final String DISPLAY_MESSAGE_ARGUMENT = "display_message_argument";
    public static final String POSITIVE_BUTTON_NAME = "dialog_positive_button_name";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getArguments().getInt(DISPLAY_MESSAGE_ARGUMENT))
                .setPositiveButton(getArguments().getString(POSITIVE_BUTTON_NAME),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GooglePlayWrapper.findApplicationFromGooglePlay(getActivity(),
                                        GooglePlayWrapper.PLAY_APP_PREFIX + KEYBOARD_PACKAGE_NAME);
                            }
                        }
                )
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
        return builder.create();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().finish();
    }
}