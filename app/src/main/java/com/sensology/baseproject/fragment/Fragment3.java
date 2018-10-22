package com.sensology.baseproject.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sensology.baseproject.R;
import com.sensology.baseproject.eventBus.UserInfoEvent;
import com.sensology.baseproject.log.LogUtil;
import com.sensology.framelib.event.BusProvider;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/10/22.
 */
public class Fragment3 extends BaseFragment {
    private String TAG = Fragment3.class.getSimpleName();
    @BindView(R.id.name)
    public TextView mName;
    @BindView(R.id.update)
    public Button mUpdate;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_3_layout;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        LogUtil.d(TAG);
        mName.setText(TAG);
        mName.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view == mName){
            BusProvider.getBus().post(new UserInfoEvent("test name",0));
        }
        if (view == mUpdate){
            BusProvider.getBus().post(new UserInfoEvent("update name",1));
        }
    }
}
