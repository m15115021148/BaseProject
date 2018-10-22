package com.sensology.baseproject.present;

import com.sensology.baseproject.bean.TypeBean;
import com.sensology.baseproject.fragment.Fragment1;
import com.sensology.framelib.mvp.present.XPresent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chenM} on 2018/10/22.
 */
public class Fragment1P extends XPresent<Fragment1> {

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
