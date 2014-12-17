package com.kkkeyboard.emoji.keyboard.theme.LittleSnowFlake;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.kkkeyboard.emoji.keyboard.theme.LittleSnowFlake.R;

/**
 * Created by Bin Hsueh on 14-4-3.
 */
public class ThemeAppWidgetProvider extends AppWidgetProvider{
    private static final String TAG = "KKKeyboardWidget";
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(TAG, "in onUpdate()");

        final int N = appWidgetIds.length;

        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
            views.setOnClickPendingIntent(R.id.widgetImage, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
