package com.handsome.recyclerpage.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handsome.recyclerpage.R;
import com.handsome.recyclerpage.View.SquareImageView;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/10/29.
 */

public class GridThreeViewHolder extends RecyclerView.ViewHolder {

    public SquareImageView iv_icon;
    public TextView tv_content;

    public GridThreeViewHolder(View itemView) {
        super(itemView);
        iv_icon = (SquareImageView) itemView.findViewById(R.id.iv_icon);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
    }
}
