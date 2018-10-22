package com.sensology.baseproject.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sensology.baseproject.R;
import com.sensology.baseproject.fragment.BaseFragment;

import java.util.List;

/**
 * Created by ${chenM} on 2018/10/22.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<BaseFragment> mFragment;
    private String[] mName = {"One","Two","Three"};

    public MyFragmentAdapter(FragmentManager fm, List<BaseFragment> fragment, Context context) {
        super(fm);
        this.mFragment = fragment;
        this.mContext = context;
    }

    public View getView(int pos){
        View view = LayoutInflater.from(mContext).inflate(R.layout.tablayout_layout_item,null);
        TextView name = view.findViewById(R.id.name);
        name.setText(mName[pos]);
        return view;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mName.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mName[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
