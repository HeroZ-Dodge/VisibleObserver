package com.dodge.vo;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linzheng on 2019/8/30.
 */

public class VisibleRecorder {

    private Map<String, Boolean> mViewVisibleMap = new HashMap<>();
    private Map<String, SoftReference<Activity>> mActivityMap = new HashMap<>();
    private Map<String, SoftReference<Fragment>> mFragmentMap = new HashMap<>();

    private VisibleListener<Activity> mActivityVisibleListener;
    private VisibleListener<Fragment> mFragmentVisibleListener;

    private VisibleRecorder() {
    }

    public void setActivityVisibleListener(VisibleListener<Activity> activityVisibleListener) {
        mActivityVisibleListener = activityVisibleListener;
    }


    public void setFragmentVisibleListener(VisibleListener<Fragment> fragmentVisibleListener) {
        mFragmentVisibleListener = fragmentVisibleListener;
    }

    public void performVisibleListener(Activity activity, boolean visible) {
        if (mActivityVisibleListener != null) {
            mActivityVisibleListener.onVisibleChanged(activity, visible);
        }
    }


    public void performVisibleListener(Fragment fragment, boolean visible) {
        if (mFragmentVisibleListener != null) {
            mFragmentVisibleListener.onVisibleChanged(fragment, visible);
        }
    }


    public void put(Activity activity) {
        if (activity == null) {
            return;
        }
        String key = getKey(activity);
        mActivityMap.put(key, new SoftReference<>(activity));
    }

    public void remove(Activity activity) {
        if (activity == null) {
            return;
        }
        String key = getKey(activity);
        mActivityMap.remove(key);
        mViewVisibleMap.remove(key);
    }


    public final void put(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        String key = getKey(fragment);
        mFragmentMap.put(key, new SoftReference<>(fragment));
    }

    public final void remove(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        String key = getKey(fragment);
        mFragmentMap.remove(key);
        mViewVisibleMap.remove(key);
    }

    public final boolean isDifferent(String key, boolean expect) {
        Boolean visible = mViewVisibleMap.get(key);
        if (visible == null) {
            return true;
        } else {
            return visible ^ expect;
        }
    }

    public final boolean isVisible(String key) {
        if (TextUtils.isEmpty(key)) {
            return false;
        } else {
            Boolean visible = mViewVisibleMap.get(key);
            return visible != null ? visible : false;
        }
    }

    public final void updateVisible(String key, boolean visible) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        mViewVisibleMap.put(key, visible);
    }

    public final String getKey(Object obj) {
        if (obj == null) {
            return null;
        }
        int hashCode = obj.hashCode();
        return obj.getClass().getSimpleName() + hashCode;
    }

    public final boolean isFragmentVisible(Fragment fragment) {
        final boolean userVisible = fragment.getUserVisibleHint();
        final boolean isHidden = fragment.isHidden();
        final boolean resumed = fragment.isResumed();
        if (!userVisible || isHidden || !resumed) {
            return false;
        } else {
            final boolean parentVisible;
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment != null) {
                parentVisible = isFragmentVisible(parentFragment);
            } else {
                Activity parentActivity = fragment.getActivity();
                String parentKey = getKey(parentActivity);
                parentVisible = isVisible(parentKey);
            }
            return parentVisible;
        }
    }

    public static VisibleRecorder getInstance() {
        return InstanceHolder.mInstance;
    }

    private static class InstanceHolder {

        private static VisibleRecorder mInstance = new VisibleRecorder();

    }


}
