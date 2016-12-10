package com.hgs.zjcp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgs.zjcp.R;
import com.hgs.zjcp.data.MobMenuCategory;

/**
 * Created by hgs on 2016/10/29.
 */
public class MyGridAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private MobMenuCategory.Child[] mChildren;


    public MyGridAdapter(Context context, MobMenuCategory.Child[] mChildren) {
        mInflater = LayoutInflater.from(context);
        this.mChildren = mChildren;
    }

    @Override
    public int getCount() {
        return mChildren.length;
    }

    @Override
    public Object getItem(int position) {
        return mChildren[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold;
        if (convertView == null) {
            viewHold = new ViewHold();
            convertView = mInflater.inflate(R.layout.menu_category, null);
            viewHold.tvName = (TextView) convertView.findViewById(R.id.menu_cate_name);
            viewHold.ivIcon = (ImageView) convertView.findViewById(R.id.menu_cate_icon);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

        viewHold.tvName.setText(mChildren[position].getName());

        return convertView;
    }

    class ViewHold {
        ImageView ivIcon;
        TextView tvName;
    }


}
