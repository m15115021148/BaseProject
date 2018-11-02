package com.sensology.baseproject.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sensology.baseproject.R;
import com.sensology.baseproject.adapter.FragmentAdapter;
import com.sensology.baseproject.bean.TypeBean;
import com.sensology.baseproject.eventBus.UserInfoEvent;
import com.sensology.baseproject.log.LogUtil;
import com.sensology.baseproject.present.Fragment1P;
import com.sensology.framelib.event.BusProvider;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by ${chenM} on 2018/10/22.
 */
public class Fragment1 extends BaseFragment<Fragment1P> implements FragmentAdapter.OnFragmentCallBack {
    private String TAG = Fragment1.class.getSimpleName();
    @BindView(R.id.recyclerView)
    public RecyclerView mRecyclerView;
    private FragmentAdapter mAdapter;
    private Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_1_layout;
    }

    @Override
    public Fragment1P newP() {
        return new Fragment1P();
    }

    @Override
    protected void onDestoryLazy() {
        super.onDestoryLazy();
        unSubscribeRxBus(disposable);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        registerBus();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new FragmentAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setData(getP().getData());
    }

    @Override
    protected void onStartLazy() {
        super.onStartLazy();
        LogUtil.d(TAG + " -->onStartLazy");
    }

    @Override
    protected void onStopLazy() {
        super.onStopLazy();
        LogUtil.d(TAG + " -->onStopLazy");
    }

    private void registerBus(){
        disposable = BusProvider.getBus().toFlowable(UserInfoEvent.class)
                .subscribe(new Consumer<UserInfoEvent>() {
                    @Override
                    public void accept(UserInfoEvent userInfoEvent) throws Exception {
                        if (userInfoEvent.getType() == 0){
                            mAdapter.addElement(new TypeBean(userInfoEvent.getName(),"add detail"));
                        }else {
                            mAdapter.updateElement(new TypeBean(userInfoEvent.getName(),"update detail"),2);
                        }

                    }
                });
    }

    @Override
    public void onItemClickListener(int position) {
        getP().login("13564719491","123456");
    }
}
