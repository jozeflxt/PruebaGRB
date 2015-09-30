package com.grability.myapplication.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grability.myapplication.R;
import com.grability.myapplication.datos.CategoryBLL;
import com.grability.myapplication.entities.Category;
import com.grability.myapplication.utilities.DownloadImageTask;
import com.grability.myapplication.views.CategoryViewHolder;

import java.util.List;

/**
 * Created by Carlos  Muñoz on 23/09/2015.
 */
public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryViewHolder>
        implements View.OnClickListener {
    private List<Category> mDataset;
    private Context context;
    private View.OnClickListener listener;

    public CategoryRecyclerAdapter(List<Category> cats,Context ctx) {
        mDataset = cats;
        context = ctx;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item, parent, false);
        v.setOnClickListener(this);
        CategoryViewHolder vh = new CategoryViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Category cat = mDataset.get(position);
        holder.tvName.setText(mDataset.get(position).name);
        if(cat.icono == null) {
            cat = new CategoryBLL(context).GetCategory(cat.id);
            if(cat.icono != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(cat.icono, 0, cat.icono.length);
                holder.imIcono.setImageBitmap(bmp);
            }
        }
        else {
            Bitmap bmp = BitmapFactory.decodeByteArray(cat.icono, 0, cat.icono.length);
            holder.imIcono.setImageBitmap(bmp);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }
}
