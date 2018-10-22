package com.sensology.baseproject.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sensology.baseproject.R;
import com.sensology.baseproject.eventBus.UserInfoEvent;
import com.sensology.baseproject.present.MainP;
import com.sensology.framelib.event.BusProvider;
import com.sensology.framelib.router.Router;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainP>{
    @BindView(R.id.bt)
    public Button mTv;
    @BindView(R.id.tvName)
    public TextView mName;
    private Disposable disposable;

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
        if (v == mTv){
            Router.newIntent(context)
                    .to(SecondActivity.class)
                    .launch();
        }
    }

    private void registerBus(){
        disposable = BusProvider.getBus().toFlowable(UserInfoEvent.class)
                .subscribe(new Consumer<UserInfoEvent>() {
                    @Override
                    public void accept(UserInfoEvent userInfoEvent) throws Exception {
                        mName.setText(userInfoEvent.getName());
                    }
                });
    }

}
