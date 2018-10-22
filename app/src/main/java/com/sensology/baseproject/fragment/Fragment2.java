package com.sensology.baseproject.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sensology.baseproject.R;
import com.sensology.baseproject.log.LogUtil;
import com.sensology.baseproject.ui.SecondActivity;
import com.sensology.framelib.router.Router;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/10/22.
 */
public class Fragment2 extends BaseFragment {
    private String TAG = Fragment2.class.getSimpleName();
    @BindView(R.id.name)
    public TextView mName;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_2_layout;
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
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view == mName){
            Router.newIntent(context)
                    .to(SecondActivity.class)
                    .launch();
        }
    }
}
