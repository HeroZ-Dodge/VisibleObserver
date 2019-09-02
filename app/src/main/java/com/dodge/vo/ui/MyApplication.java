package com.dodge.vo.ui;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.dodge.vo.ActivityVisibleManager;
import com.dodge.vo.VisibleListener;
import com.dodge.vo.VisibleRecorder;

/**
 * Created by linzheng on 2019/9/2.
 */

public class MyApplication extends Application {


    public static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        initVisibleManager();

    }

    private void initVisibleManager() {
        VisibleRecorder recorder = VisibleRecorder.getInstance();
        recorder.setActivityVisibleListener(new VisibleListener<Activity>() {
            @Override
            public void onVisibleChanged(Activity activity, boolean visible) {
                Log.d(TAG, "onVisibleChanged: activity = " + activity.toString() + ", visible = " + visible);

            }
        });

        recorder.setFragmentVisibleListener(new VisibleListener<Fragment>() {
            @Override
            public void onVisibleChanged(Fragment fragment, boolean visible) {
                Log.d(TAG, "onVisibleChanged: fragment = " + fragment.toString() + ", visible = " + visible);

            }
        });
        registerActivityLifecycleCallbacks(new ActivityVisibleManager());
    }
}
