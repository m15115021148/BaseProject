package com.sensology.baseproject.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sensology.baseproject.R;
import com.sensology.baseproject.adapter.MyFragmentAdapter;
import com.sensology.baseproject.eventBus.UserInfoEvent;
import com.sensology.baseproject.fragment.BaseFragment;
import com.sensology.baseproject.fragment.Fragment1;
import com.sensology.baseproject.fragment.Fragment2;
import com.sensology.baseproject.fragment.Fragment3;
import com.sensology.baseproject.present.MainP;
import com.sensology.framelib.event.BusProvider;
import com.sensology.framelib.router.Router;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainP>{
    private Disposable disposable;
    @BindView(R.id.viewPager)
    public ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    public TabLayout mTabLayout;
    private List<BaseFragment> mFragment = new ArrayList<>();
    private MyFragmentAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainP newP() {
        return new MainP();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initTabLayout();
        registerBus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSubscribeRxBus(disposable);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onBackPressed() {
        exitApp();
    }

    private void initTabLayout(){
        mFragment.clear();
        mTabLayout.removeAllTabs();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式

        mFragment.add(new Fragment1());
        mFragment.add(new Fragment2());
        mFragment.add(new Fragment3());

        mAdapter = new MyFragmentAdapter(getSupportFragmentManager(),mFragment,context);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);

        for (int i=0;i<mTabLayout.getTabCount();i++){
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab!=null){
                View v = mAdapter.getView(i);
                if (i==0){//默认第一个选中
                    v.setSelected(true);
                }
                tab.setCustomView(v);
            }
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void registerBus(){
        disposable = BusProvider.getBus().toFlowable(UserInfoEvent.class)
                .subscribe(new Consumer<UserInfoEvent>() {
                    @Override
                    public void accept(UserInfoEvent userInfoEvent) throws Exception {

                    }
                });
    }

}
