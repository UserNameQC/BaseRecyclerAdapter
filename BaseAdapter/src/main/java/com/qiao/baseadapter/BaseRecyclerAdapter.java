package com.qiao.baseadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Administrator on 2018/7/31 0031.
 */

public abstract class UniversalRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener {

    public Context mContext;
    public ObservableArrayList<T> mDatas;
    private int mLayoutId;
    public R viewBinding;
    private OnItemClickListener mItemClickListener;
    private onLongItemClickListener mLongItemClickListener;

    public UniversalRecyclerAdapter(Context mContext, ObservableArrayList<T> mDatas, int mLayoutId) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mLayoutId = mLayoutId;
    }


    public  UniversalRecyclerAdapter(Context mContext, ObservableArrayList<T> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    public UniversalRecyclerAdapter(Context context, ObservableArrayList<T> mDatas, int mLayoutId, int type){
        this.mContext = context;
        this.mDatas = mDatas;
        this.mLayoutId = mLayoutId;
    }

    public void updateData(List<T> data) {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public List<T> getList(){
        return  mDatas;
    }

    public void addAll(List<T> data) {
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void AddHeaderItem(List<T> items){
        mDatas.addAll(0,items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<T> items){
        mDatas.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(T t, int position){
        mDatas.add(position, t);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        view.setOnClickListener(this);
        return new BaseViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.itemView.setTag(position);
        convert(mContext, holder, mDatas.get(position), position);
        if (mItemClickListener != null) {
            holder.mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v, position);
                }
            });
        }
        if (mLongItemClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongItemClickListener.onLongItemClick(v, position);
                    return true;
                }
            });
        }
    }


    public abstract void convert(Context mContext, BaseViewHolder holder, T t, int position);

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null){
            mItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface onLongItemClickListener {
        void onLongItemClick(View view, int postion);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setonLongItemClickListener(onLongItemClickListener listener) {
        this.mLongItemClickListener = listener;
    }

    public final void clear(){
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void notifyList(List<T> list){
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }
}
