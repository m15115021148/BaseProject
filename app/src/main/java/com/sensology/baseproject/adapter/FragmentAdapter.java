package com.sensology.baseproject.adapter;

import android.view.View;
import android.widget.LinearLayout;
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
    private OnFragmentCallBack mCallBack;

    public FragmentAdapter(OnFragmentCallBack callBack) {
        this.mCallBack = callBack;
    }

    public interface OnFragmentCallBack {
        void onItemClickListener(int position);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_item_layout;
    }

    @Override
    public BaseViewHolder onBindViewHolder(View view) {
        return new Holder(view);
    }

    public class Holder extends BaseViewHolder {
        @BindView(R.id.name)
        public TextView mName;
        @BindView(R.id.content)
        public TextView mDetail;
        @BindView(R.id.layout)
        public LinearLayout mLayout;

        public Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void initData(final int position) {
            TypeBean bean = getData().get(position);
            mName.setText(bean.getName());
            mDetail.setText(bean.getDetail());
            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallBack != null) mCallBack.onItemClickListener(position);
                }
            });
        }
    }

}
