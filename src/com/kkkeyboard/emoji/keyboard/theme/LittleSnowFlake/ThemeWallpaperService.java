package com.kkkeyboard.emoji.keyboard.theme.LittleSnowFlake;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.kkkeyboard.emoji.keyboard.theme.LittleSnowFlake.R;

/**
 * Created by Bin Hsueh on 14-4-18.
 */
public class ThemeWallpaperService extends WallpaperService {


    class ThemeWallpaperEngine extends Engine {

        private int mSurfaceWidth;
        private int mSurfaceHeight;

        void drawImage() {

            SurfaceHolder holder = getSurfaceHolder();

            Canvas canvas = holder.lockCanvas();

            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper);

            canvas.drawBitmap(image, null, new Rect(0, 0, mSurfaceWidth, mSurfaceHeight), new Paint());

            holder.unlockCanvasAndPost(canvas);
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            setTouchEventsEnabled(false);
        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            super.onSurfaceChanged(holder, format, width, height);

            mSurfaceWidth = width;
            mSurfaceHeight = height;
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            super.onVisibilityChanged(visible);

            if (visible == true)
            {
                drawImage();
            }
        }


    }

    @Override
    public Engine onCreateEngine() {
        return new ThemeWallpaperEngine();
    }
}
