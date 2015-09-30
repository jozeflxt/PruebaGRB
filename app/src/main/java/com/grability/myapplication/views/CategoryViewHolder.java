package com.grability.myapplication.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.grability.myapplication.R;

/**
 * Created by Carlos  Muñoz on 24/09/2015.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public ImageView imIcono;

    public CategoryViewHolder(View view) {
        super(view);
        this.tvName = (TextView) view.findViewById(R.id.categoryName);
        this.imIcono = (ImageView) view.findViewById(R.id.categoryImage);
    }
}