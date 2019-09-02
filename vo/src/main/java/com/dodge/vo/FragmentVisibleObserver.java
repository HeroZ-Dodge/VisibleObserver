package com.dodge.vo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by linzheng on 2019/9/2.
 */

public class FragmentVisibleObserver {


    public static void onCreate(Fragment fragment) {
        VisibleRecorder.getInstance().put(fragment);
    }

    public static void onResume(Fragment fragment) {
        updateFragmentVisible(fragment, true);
    }

    public static void onPause(Fragment fragment) {
        updateFragmentVisible(fragment, false);
    }

    public static void setUserVisibleHint(Fragment fragment, boolean isVisibleToUser) {
        if (fragment.isAdded()) {
            updateFragmentVisible(fragment, isVisibleToUser);
        }
    }

    public static void onHiddenChanged(Fragment fragment, boolean hidden) {
        updateFragmentVisible(fragment, !hidden);
    }

    public static void onDestroy(Fragment fragment) {
        VisibleRecorder.getInstance().remove(fragment);
    }


    private static void updateFragmentVisible(Fragment fragment, boolean expect) {
        if (fragment == null) {
            return;
        }
        VisibleRecorder recorder = VisibleRecorder.getInstance();
        String key = recorder.getKey(fragment);
        if (recorder.isDifferent(key, expect)) {
            boolean visible = recorder.isFragmentVisible(fragment);
            if (visible == expect) {
                recorder.updateVisible(key, expect);
                recorder.performVisibleListener(fragment, expect);
            }
        }
        FragmentManager fm = fragment.getChildFragmentManager();
        List<Fragment> childList = fm.getFragments();
        if (childList.isEmpty()) {
            return;
        }
        for (Fragment child : childList) {
            updateFragmentVisible(child, expect);
        }
    }


}
