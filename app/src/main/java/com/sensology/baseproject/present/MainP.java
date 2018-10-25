package com.sensology.baseproject.present;

import com.sensology.baseproject.bean.BaseResult;
import com.sensology.baseproject.http.CustomApiSubscriber;
import com.sensology.baseproject.http.HttpManager;
import com.sensology.baseproject.http.SignalUtils;
import com.sensology.baseproject.ui.MainActivity;
import com.sensology.framelib.http.NetError;
import com.sensology.framelib.http.XApi;
import com.sensology.framelib.kit.Codec;
import com.sensology.framelib.kit.Kits;
import com.sensology.framelib.mvp.present.XPresent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${chenM} on 2018/10/19.
 */
public class MainP extends XPresent<MainActivity> {

    public void login(String userName, String pwd) {

        Map<String, Object> params = SignalUtils.getSignal();
        params.put("account", userName);
        params.put("pwd", Kits.MD5.getMD5(pwd));

        HttpManager.getApiService().login(params)
                .compose(XApi.<BaseResult>getApiTransformer())
                .compose(XApi.<BaseResult>getScheduler())
                .compose(getV().<BaseResult>bindToLifecycle())
                .subscribe(new CustomApiSubscriber<BaseResult>() {
                    @Override
                    protected void onFail(NetError error) {
                        getV().showTs(error.getMessage());
                    }

                    @Override
                    public void onNext(BaseResult result) {
                        super.onNext(result);
                    }
                });
    }
}
