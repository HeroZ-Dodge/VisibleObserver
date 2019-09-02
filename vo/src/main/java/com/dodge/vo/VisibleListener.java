package com.dodge.vo;

/**
 * Created by linzheng on 2019/9/2.
 */

public interface VisibleListener<T> {

    void onVisibleChanged(T t, boolean visible);

}
