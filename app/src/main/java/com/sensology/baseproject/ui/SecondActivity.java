package com.sensology.baseproject.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sensology.baseproject.R;

import butterknife.BindView;

public class SecondActivity extends BaseActivity {
    @BindView(R.id.exit)
    public Button mExit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == mExit){
            exitApp();
        }
    }
}
