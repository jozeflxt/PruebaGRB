package com.grability.myapplication.views;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.grability.myapplication.R;
import com.grability.myapplication.adapters.CategoryRecyclerAdapter;
import com.grability.myapplication.entities.Category;
import com.grability.myapplication.presenters.MainPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.ByteArrayOutputStream;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    private RecyclerView.LayoutManager mLayoutManager;
    private MainPresenter presenter;

    @ViewById
    RecyclerView listCategoriesView;


    @AfterViews
    void initPresenter()
    {
        presenter = new MainPresenter(this);
        presenter.GetCategories();
        listCategoriesView.setHasFixedSize(true);
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE)
            mLayoutManager = new GridLayoutManager(this,2);
        else
            mLayoutManager = new LinearLayoutManager(this);
        listCategoriesView.setLayoutManager(mLayoutManager);
        listCategoriesView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        listCategoriesView.setItemAnimator(new DefaultItemAnimator());
    }


    public void setCategories(final List<Category> categories)
    {
        final CategoryRecyclerAdapter mAdapter = new CategoryRecyclerAdapter(categories,this);
        final MainActivity that = this;
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryViewHolder ct = (CategoryViewHolder)listCategoriesView.getChildViewHolder(v);
                Intent intent = new Intent(that, DetailActivty_.class);
                intent.putExtra("id",categories.get(listCategoriesView.getChildPosition(v)).id);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(that, ct.imIcono, "trImage");
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }

        }
    });
        listCategoriesView.setAdapter(mAdapter);
    }
}
