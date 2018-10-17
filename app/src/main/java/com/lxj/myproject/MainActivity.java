package com.lxj.myproject;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.lxj.myproject.adapter.AppFragmentPageAdapter;
import com.lxj.myproject.base.BaseActivity;
import com.lxj.myproject.base.BaseFragment;
import com.lxj.myproject.base.BasePresenter;
import com.lxj.myproject.fragment.AFragment;
import com.lxj.myproject.fragment.BFragment;
import com.lxj.myproject.fragment.CFragment;
import com.lxj.myproject.mvp.presenter.MainPresenter;
import com.lxj.myproject.mvp.view.MianView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MianView {
    @BindView(R.id.a_fragment_btn)
    Button aFragmentBtn;
    @BindView(R.id.b_fragment_btn)
    Button bFragmentBtn;
    @BindView(R.id.c_fragment_btn)
    Button cFragmentBtn;
    @BindView(R.id.vp_container)
    ViewPager vpContainer;

    MainPresenter presenter;

    private static List<BaseFragment> mPages;
    AFragment aFragment;
    BFragment bFragment;
    CFragment cFragment;
    List<Button> mPageIndicates;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initContentView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void initPresenter() {
        presenter = new MainPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 断开View引用
        presenter.detachView();
    }

    @Override
    protected void initView() {
        //初始化Fragment
        initFragment();
    }

    @Override
    protected void initEvent() {
        vpContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageChanged(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        mPageIndicates = new ArrayList<>();
        mPageIndicates.add(aFragmentBtn);
        mPageIndicates.add(bFragmentBtn);
        mPageIndicates.add(cFragmentBtn);

        mPages = new ArrayList<>();
        aFragment = new AFragment();
        bFragment = new BFragment();
        cFragment = new CFragment();

        mPages.add(aFragment);
        mPages.add(bFragment);
        mPages.add(cFragment);

        vpContainer.setOffscreenPageLimit(mPages.size());
        AppFragmentPageAdapter adapter = new AppFragmentPageAdapter(getSupportFragmentManager(), mPages);
        vpContainer.setAdapter(adapter);

        changePage(1);
        bFragmentBtn.setBackgroundResource(R.color.blue);
    }

    /**
     * ViewPage页面滚动切换的回调
     *
     * @param position 滚动后的页面索引
     */
    private void pageChanged(int position) {
        for (int i = 0; i < mPageIndicates.size(); i++) {
            if (position == i) {
                mPageIndicates.get(i).setBackgroundResource(R.color.blue);
                mPages.get(position).onShow();
            } else {
                mPageIndicates.get(i).setBackgroundResource(R.color.green);
                mPages.get(position).onHide();
            }
        }
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO
                //hideInputKeybord();
            }
        }, 200);
    }

    /**
     * 顶部TAB导航切换ViewPage页面主动调用
     *
     * @param position 目标页面索引
     */
    private void changePage(int position) {
        if (vpContainer.getCurrentItem() == position || position < 0 || position >= mPages.size()) {
            return;
        }
        vpContainer.setCurrentItem(position, true);
    }

    @Override
    public void showData(String data) {

    }

    @OnClick({R.id.a_fragment_btn, R.id.b_fragment_btn, R.id.c_fragment_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.a_fragment_btn:
                changePage(0);
                break;
            case R.id.b_fragment_btn:
                changePage(1);
                break;
            case R.id.c_fragment_btn:
                changePage(2);
                break;
        }
    }
}
