package com.qiao.baserecycleradapter;

import android.content.Context;

import com.qiao.baseadapter.BaseRecyclerAdapter;
import com.qiao.baseadapter.BaseViewHolder;
import com.qiao.baserecycleradapter.databinding.ItemTestLayoutBinding;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

public class TestAdapter extends BaseRecyclerAdapter<TestModel> {
    public TestAdapter(@NonNull Context mContext, @NonNull ObservableArrayList<TestModel> mDatas) {
        super(mContext, mDatas, R.layout.item_test_layout);
    }

    @Override
    public void convert(@NonNull Context mContext, @NonNull BaseViewHolder holder, TestModel testModel, int position) {
        ItemTestLayoutBinding layoutBinding =  (ItemTestLayoutBinding) holder.getBinding();
        layoutBinding.text.setText(testModel.getName());
    }
}
