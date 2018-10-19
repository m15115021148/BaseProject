package com.sensology.baseproject.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sensology.baseproject.R;
import com.sensology.baseproject.present.MainP;
import com.sensology.framelib.router.Router;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainP>{
    @BindView(R.id.bt)
    public Button mTv;

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
        mTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == mTv){
            Router.newIntent(context)
                    .to(SecondActivity.class)
                    .launch();
        }
    }

    @Override
    public void registerEventBusBase() {
        super.registerEventBusBase();
    }
}
