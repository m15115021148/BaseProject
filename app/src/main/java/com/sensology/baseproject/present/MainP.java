package com.sensology.baseproject.present;

import com.sensology.baseproject.bean.BaseResult;
import com.sensology.baseproject.http.CustomApiSubscriber;
import com.sensology.baseproject.http.HttpManager;
import com.sensology.baseproject.ui.MainActivity;
import com.sensology.framelib.http.NetError;
import com.sensology.framelib.http.XApi;
import com.sensology.framelib.mvp.present.XPresent;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ${chenM} on 2018/10/19.
 */
public class MainP extends XPresent<MainActivity> {

    public void addComment(List<File> list) {

        Map<String, RequestBody> map = new HashMap<>();

        map.put("os",HttpManager.getParameterText("os"));
        map.put("token", HttpManager.getParameterText("os"));


        MultipartBody.Part[] parts = new MultipartBody.Part[list.size()];

        for (int i=0;i<list.size();i++){
            File file = list.get(i);
            if (file != null){
                parts[i] = HttpManager.getMultipartBody("commentPics",file);
            }
        }

        HttpManager.getApiService().addComment(map,parts)
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
