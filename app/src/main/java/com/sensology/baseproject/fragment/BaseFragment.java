package com.sensology.baseproject.fragment;

import android.view.View;

import com.sensology.baseproject.ui.BaseActivity;
import com.sensology.framelib.mvp.fragment.XFragment;
import com.sensology.framelib.mvp.present.IPresent;

import io.reactivex.disposables.Disposable;

/**
 * Created by ${chenM} on 2018/10/22.
 */
public abstract class BaseFragment<P extends IPresent> extends XFragment<P> implements View.OnClickListener {


    @Override
    public void onClick(View view) {
        if (getActivity() !=null && getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).clickVibrator();
        }
    }

    @Override
    public boolean useEventBus() {
        return true;
    }


    public void showNetError(){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                ToastUtil.showShort(context,getString(R.string.net_error));
            }
        });
    }

    protected void unSubscribeRxBus(Disposable disposable){
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
