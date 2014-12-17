package com.kkkeyboard.emoji.keyboard.theme.LittleSnowFlake;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by xuebin on 14-6-19.
 */
public class GooglePlayWrapper {
    public static final String PLAY_APP_PREFIX = "market://details?id=";
    static public boolean findApplicationFromGooglePlay(Context context, String query) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(query));
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
