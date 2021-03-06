package com.sensology.baseproject.http;

import com.sensology.baseproject.bean.BaseResult;
import com.sensology.baseproject.eventBus.ExitAppEvent;
import com.sensology.framelib.event.BusProvider;
import com.sensology.framelib.http.ApiSubscriber;
import com.sensology.framelib.http.NetError;

/**
 * Created by ${chenM} on 2018/10/25.
 */
public abstract class CustomApiSubscriber<T extends BaseResult> extends ApiSubscriber<T> {

    @Override
    protected void onFail(NetError error) {

    }

    @Override
    public void onNext(T t) {
        if (t.getCode() == 401){
            BusProvider.getBus().post(new ExitAppEvent(true,true));
        }
    }
}
