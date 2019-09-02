package com.dodge.vo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by linzheng on 2019/8/30.
 */

public class ActivityVisibleManager implements Application.ActivityLifecycleCallbacks {

    private VisibleRecorder mVisibleRecorder = VisibleRecorder.getInstance();


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mVisibleRecorder.put(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        updateActivityVisible(activity, true);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        updateActivityVisible(activity, false);
    }

    private void updateActivityVisible(Activity activity, boolean expect) {
        String key = mVisibleRecorder.getKey(activity);
        if (mVisibleRecorder.isDifferent(key, expect)) {
            mVisibleRecorder.updateVisible(key, expect);
            // listener
            mVisibleRecorder.performVisibleListener(activity, expect);
            // update fragment
            if (activity instanceof FragmentActivity) {
                FragmentManager fm = ((FragmentActivity) activity).getSupportFragmentManager();
                List<Fragment> list = fm.getFragments();
                updateFragmentVisible(list, expect);
            }
        }
    }

    private void updateFragmentVisible(List<Fragment> list, boolean expect) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Fragment fragment : list) {
            String key = mVisibleRecorder.getKey(fragment);
            if (mVisibleRecorder.isDifferent(key, expect)) {
                boolean visible = mVisibleRecorder.isFragmentVisible(fragment);
                if (visible == expect) {
                    mVisibleRecorder.updateVisible(key, expect);
                    mVisibleRecorder.performVisibleListener(fragment, expect);
                }
            }
            FragmentManager fm = fragment.getChildFragmentManager();
            List<Fragment> childList = fm.getFragments();
            updateFragmentVisible(childList, expect);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mVisibleRecorder.remove(activity);
    }

}
