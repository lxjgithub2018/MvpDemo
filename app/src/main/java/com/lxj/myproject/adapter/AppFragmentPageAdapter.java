package com.lxj.myproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lxj.myproject.base.BaseFragment;

import java.util.List;

/**
 * Created by li on 2018/8/9.
 */

public class AppFragmentPageAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mList = null;

    public AppFragmentPageAdapter(FragmentManager fragmentManager, List<BaseFragment> list) {
        super(fragmentManager);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        if (null == mList || position < 0 || position >= mList.size()) {
            return null;
        }
        return mList.get(position);
    }

    @Override
    public int getCount() {
        if (null == mList) {
            return 0;
        }
        return mList.size();
    }
}
