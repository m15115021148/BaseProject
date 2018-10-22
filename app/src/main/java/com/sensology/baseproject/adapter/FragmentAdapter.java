package com.sensology.baseproject.adapter;

import android.view.View;
import android.widget.TextView;

import com.sensology.baseproject.R;
import com.sensology.baseproject.bean.TypeBean;
import com.sensology.framelib.adapter.BaseViewHolder;
import com.sensology.framelib.adapter.XRecyclerViewAdapter;

import butterknife.BindView;

/**
 * Created by ${chenM} on 2018/10/22.
 */
public class FragmentAdapter extends XRecyclerViewAdapter<TypeBean> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_item_layout;
    }

    @Override
    public BaseViewHolder onBindViewHolder(View view) {
        return new Holder(view);
    }

    public class Holder extends BaseViewHolder{
        @BindView(R.id.name)
        public TextView mName;
        @BindView(R.id.content)
        public TextView mDetail;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void initData(int position) {
            TypeBean bean = getData().get(position);
            mName.setText(bean.getName());
            mDetail.setText(bean.getDetail());
        }
    }

}
