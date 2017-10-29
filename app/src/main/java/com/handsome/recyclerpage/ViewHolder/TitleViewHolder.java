package com.handsome.recyclerpage.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handsome.recyclerpage.R;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/10/29.
 */

public class TitleViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_content;

    public TitleViewHolder(View itemView) {
        super(itemView);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
    }
}
