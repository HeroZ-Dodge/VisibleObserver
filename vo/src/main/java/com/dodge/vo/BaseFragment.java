package com.dodge.vo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by linzheng on 2019/9/2.
 */

public class BaseFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentVisibleObserver.onCreate(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        FragmentVisibleObserver.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        FragmentVisibleObserver.onPause(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        FragmentVisibleObserver.setUserVisibleHint(this, isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        FragmentVisibleObserver.onHiddenChanged(this, hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FragmentVisibleObserver.onDestroy(this);
    }


}
