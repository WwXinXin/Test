package com.efamily.testapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.efamily.testapp.DividerItemDecoration;
import com.efamily.testapp.R;

import java.util.ArrayList;
import java.util.List;


public class TestOneFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<String> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_have_replay, container, false);

        initView(view);
        initData();
        initAdapter();

        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyvlerView);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            mData.add("美食" + i);
    }

    private void initAdapter() {
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(new RvAdapter(mData));//设置adapter
    }

    class RvAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public RvAdapter(@Nullable List<String> data) {
            super(R.layout.rv_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tvTitle, item);
        }
    }
}
