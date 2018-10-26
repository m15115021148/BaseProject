package com.sensology.baseproject.present;

import com.sensology.baseproject.bean.BaseResult;
import com.sensology.baseproject.bean.TypeBean;
import com.sensology.baseproject.fragment.Fragment1;
import com.sensology.baseproject.http.CustomApiSubscriber;
import com.sensology.baseproject.http.HttpManager;
import com.sensology.baseproject.http.SignalUtils;
import com.sensology.baseproject.util.ToastUtil;
import com.sensology.framelib.http.NetError;
import com.sensology.framelib.http.XApi;
import com.sensology.framelib.kit.Kits;
import com.sensology.framelib.mvp.present.XPresent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ${chenM} on 2018/10/22.
 */
public class Fragment1P extends XPresent<Fragment1> {

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

                    }

                    @Override
                    public void onNext(BaseResult result) {
                        super.onNext(result);
                        if (result.getCode() == 200){
                            ToastUtil.showBottomShort(result.getMessage());
                        }
                    }
                });
    }

    public List<TypeBean> getData() {
        List<TypeBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TypeBean bean = new TypeBean();
            bean.setName("DATA " + (i + 1));
            bean.setDetail("SS ->" + i);
            list.add(bean);
        }
        return list;
    }

}
