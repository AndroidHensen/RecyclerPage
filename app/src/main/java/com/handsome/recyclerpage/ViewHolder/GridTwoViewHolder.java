package com.handsome.recyclerpage.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.handsome.recyclerpage.R;
import com.handsome.recyclerpage.View.RectImageView;
import com.handsome.recyclerpage.View.SquareImageView;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/10/29.
 */

public class GridTwoViewHolder extends RecyclerView.ViewHolder {

    public RectImageView iv_icon;
    public TextView tv_content;

    public GridTwoViewHolder(View itemView) {
        super(itemView);
        iv_icon = (RectImageView) itemView.findViewById(R.id.iv_icon);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
    }
}
