package com.qiao.baseadapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Administrator on 2018/7/31 0031.
 */

abstract class BaseRecyclerAdapter<T>(val mContext: Context, var mDatas: ObservableArrayList<T>, private val mLayoutId: Int=0) : RecyclerView.Adapter<BaseViewHolder>(), View.OnClickListener {

    private var mItemClickListener: OnItemClickListener? = null
    private var mLongItemClickListener: onLongItemClickListener? = null

    fun updateData(data: List<T>) {
        mDatas.clear()
        mDatas.addAll(data)
        notifyDataSetChanged()
    }

    val list: List<T>
        get() = mDatas

    fun addAll(data: List<T>) {
        mDatas.addAll(data)
        notifyDataSetChanged()
    }

    fun AddHeaderItem(items: List<T>) {
        mDatas.addAll(0, items)
        notifyDataSetChanged()
    }

    fun AddFooterItem(items: List<T>) {
        mDatas.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(t: T, position: Int) {
        mDatas.add(position, t)
        notifyItemInserted(position)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        mDatas.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(mContext), mLayoutId, parent, false)
        val holder = BaseViewHolder(binding)
        holder.itemView.setOnClickListener(this)
        return holder
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.tag = position
        convert(mContext, holder, mDatas[position], position)
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener { v -> mItemClickListener!!.onItemClick(v, position) }
        }
        if (mLongItemClickListener != null) {
            holder.itemView.setOnLongClickListener { v ->
                mLongItemClickListener!!.onLongItemClick(v, position)
            }
        }
    }


    abstract fun convert(mContext: Context, holder: BaseViewHolder, t: T, position: Int)

    override fun onClick(v: View) {
        if (mItemClickListener != null) {
            mItemClickListener!!.onItemClick(v, v.tag as Int)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface onLongItemClickListener {
        fun onLongItemClick(view: View, postion: Int): Boolean
    }


    fun setOnItemClickListener(listener: (View, Int) -> Unit) {
        this.mItemClickListener = object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                listener(view, position)
            }
        }
    }

    fun setonLongItemClickListener(listener: (View, Int) -> Boolean) {
        this.mLongItemClickListener = object : onLongItemClickListener {
            override fun onLongItemClick(view: View, postion: Int): Boolean {
                return listener(view, postion)
            }
        }
    }

    fun clear() {
        mDatas.clear()
        notifyDataSetChanged()
    }

    fun notifyList(list: List<T>) {
        mDatas.clear()
        mDatas.addAll(list)
        notifyDataSetChanged()
    }
}
