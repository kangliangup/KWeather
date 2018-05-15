package com.example.weather.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView adapter基类
 *
 * @param <D> 数据类型
 * @param <T> ViewHolder类型
 */
public abstract class BaseRecyclerAdapter<D, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter implements View.OnClickListener, View.OnLongClickListener {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener OnItemLongClickListener;
    protected LayoutInflater mLayoutInflater;


    protected abstract RecyclerView.ViewHolder getViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindViewHolderConvert(T holder, int position);

    protected List<D> list = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(View view, List list, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, List list, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mLayoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = getViewHolder(parent, viewType);
//        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setOnLongClickListener(this);
        return viewHolder;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderConvert((T) holder, position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.OnItemLongClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, list, (int) v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (OnItemLongClickListener != null) {
            OnItemLongClickListener.onItemLongClick(v, list, (int) v.getTag());
        }
        return true;
    }


    /**
     * 判断数据是否为空
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * 在原有的数据起始处添加新数据
     */
    public void addItemsFromFirst(List<D> itemList) {
        this.list.addAll(0, itemList);
        notifyDataSetChanged();
    }

    /**
     * 在原有的数据上添加新数据
     */
    public void addItems(List<D> itemList) {
        this.list.addAll(itemList);
        notifyDataSetChanged();
    }

    /**
     * 在原有的数据上添加新数据
     */
    public void addItem(D data) {
        this.list.add(data);
        notifyDataSetChanged();
    }

    /**
     * 在原有的数据上添加新数据
     */
    public void addItem(int index, D data) {
        this.list.add(index, data);
        notifyDataSetChanged();
    }

    /**
     * 在原有的数据上添加新数据
     */
    public void removeItem(D data) {
        this.list.remove(data);
        notifyDataSetChanged();
    }

    /**
     * 设置为新的数据，旧数据会被清空
     */
    public void setItems(List<D> itemList) {
        if (itemList != null) {
            list.clear();
            list.addAll(itemList);
        }
        notifyDataSetChanged();
    }

    /**
     * 获取列表数据
     */
    public List<D> getItems() {
        return list;
    }

    /**
     * 清空数据
     */
    public void clearItems() {
        list.clear();
        notifyDataSetChanged();
    }


    public void moveToPosition(RecyclerView recyclerView, int n) {
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

//        int firstItem = manager.findFirstVisibleItemPosition();
//        int lastItem = manager.findLastVisibleItemPosition();
//        System.out.println(firstItem + "  " + lastItem);
//        if (n <= firstItem) {
//            recyclerView.scrollToPosition(n);
//        } else if (n <= lastItem) {
//            int top = recyclerView.getChildAt(n - firstItem).getTop();
//            recyclerView.scrollBy(0, top);
//        } else {
//            recyclerView.scrollToPosition(n);
//        }


        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);

    }
}
