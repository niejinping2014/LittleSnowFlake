package com.kkkeyboard.emoji.keyboard.theme.LittleSnowFlake;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.kkkeyboard.emoji.keyboard.theme.view.CirclePageIndicator;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.kkkeyboard.emoji.keyboard.theme.view.JazzyViewPager;

/**
 * Created by Bin Hsueh on 14-6-18.
 */
public class MainActivity extends Activity{
    private static final String KEYBOARD_PACKAGE_NAME = "com.kitkatandroid.keyboard";
    private static final String THEME_MANAGER_ACTIVITY_NAME = "com.kitkatandroid.keyboard.kbd.ThemeManagerNew";
    private static final String THEME_PKG_SEARCH = "market://search?q=pub:Emoji Keyboard Theme";
    private static final String KEYBOARD_PKG_SEARCH = "market://details?id=com.kitkatandroid.keyboard&referrer=utm_source%3DLittleSnowFlake%26utm_medium%3Dcpc";
    private static final String THEME_APPLY_INTENT_EXTRA_NAME = "com.kitkatandroid.keyboard.ApplyTheme";
    private static final String SOLO_LAUNCHER_PROMOTION="market://details?id=home.solo.launcher.free&referrer=utm_source%3Demoji_keyboard_theme%26utm_medium%3Dcpc";
    private static final String SOLO_LAUNCHER_PKG_NAME = "home.solo.launcher.free";
    private static final int CORRECT_KEYBOARD_VERSION_CODE = 206;
    private Button mApplyButton;
    private Button mMoreButton;
    private StartAppAd startAppAd = new StartAppAd(this);
    private JazzyViewPager mJazzViewPager;
    private CirclePageIndicator mPageIndicator;

    private int[] mPreviewIDs = {
            R.drawable.preview_1,
            R.drawable.preview_2,
            R.drawable.preview_3,
            R.drawable.preview_4,
            R.drawable.preview_5,
            R.drawable.preview_6,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "109617482", "212056726", true);
        StartAppAd.showSplash(this, savedInstanceState);
        setContentView(R.layout.main);
        StartAppAd.showSlider(this);
        mApplyButton = (Button)findViewById(R.id.apply_button);
        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isKeyboardInstalled() > 0) {
                    Intent intent = new Intent();
                    intent.setClassName(KEYBOARD_PACKAGE_NAME, THEME_MANAGER_ACTIVITY_NAME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(THEME_APPLY_INTENT_EXTRA_NAME, getPackageName());
                    startActivity(intent);
                } else {
                    GooglePlayWrapper.findApplicationFromGooglePlay(MainActivity.this,
                            MainActivity.KEYBOARD_PKG_SEARCH);
                }
            }
        });
        mMoreButton = (Button)findViewById(R.id.get_more_button);
        mMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSoloLauncherInstalled() > 0) {
                    GooglePlayWrapper.findApplicationFromGooglePlay(MainActivity.this,
                            THEME_PKG_SEARCH);
                } else {
                    GooglePlayWrapper.findApplicationFromGooglePlay(MainActivity.this,
                            SOLO_LAUNCHER_PROMOTION);
                }
            }
        });

        mJazzViewPager = (JazzyViewPager)findViewById(R.id.preview_pager);
        mJazzViewPager.setAdapter(new ImageViewAdapter());
        mJazzViewPager.setTransitionEffect(JazzyViewPager.TransitionEffect.Tablet);

        mPageIndicator = (CirclePageIndicator)findViewById(R.id.circle_page_indicator);
        mPageIndicator.setViewPager(mJazzViewPager);
    }

class ImageViewAdapter extends PagerAdapter {
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public int getCount() {
        return mPreviewIDs.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position >= getCount())
            return null;
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(mPreviewIDs[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        container.addView(imageView);
        mJazzViewPager.setObjectForPosition(imageView, position);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mJazzViewPager.findViewFromObject(position));
    }
}

    @Override
    protected void onResume(){
        super.onResume();
        startAppAd.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        startAppAd.onPause();
        //finish();
    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }

    // If installed, return version code; otherwise, return -1.
    private int isKeyboardInstalled() {
        final PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(KEYBOARD_PACKAGE_NAME, 0);
            if(packageInfo != null)
                return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
        return -1;
    }

    private int isSoloLauncherInstalled() {
        final PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(SOLO_LAUNCHER_PKG_NAME, 0);
            if(packageInfo != null)
                return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
        return -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,
                        getResources().getString(R.string.action_share_text, getPackageName()));
                startActivity(Intent.createChooser(intent,
                        getResources().getString(R.string.action_share_title)));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


