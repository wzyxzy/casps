package com.hj.casps.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * fragment 懒加载
 * http://www.jianshu.com/p/104be7cd72b6
 */

public class FragmentBase extends Fragment {
    protected void log(String s) {
        Log.d(this.getClass().getSimpleName(), s);
    }

    protected void toast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    //是否可见
    protected boolean isVisble;
    // 标志位，标志Fragment已经初始化完成。
    public boolean isPrepared = false;
    private boolean hasLoadData = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 实现Fragment数据的缓加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisble = true;
            onVisible();
        } else {
            isVisble = false;
            onInVisible();
        }
    }

 /*   @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisble = true;
            onVisible();
        } else {
            isVisble = false;
            onInVisible();
        }
    }*/

    protected void onInVisible() {
    }

    protected void onVisible() {
        if (isPrepared && isVisble && !hasLoadData) {
            loadData();
        }
    }

    protected void loadData() {
        hasLoadData = true;
    }
}
