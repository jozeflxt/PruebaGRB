package com.grability.myapplication.views;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import com.grability.myapplication.R;
import com.grability.myapplication.entities.Category;
import com.grability.myapplication.presenters.DetailPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_detail_activty)
public class DetailActivty extends Activity {

    private DetailPresenter presenter;

    @AfterViews
    void initPresenter()
    {
        presenter = new DetailPresenter(this);
        int id = getIntent().getIntExtra("id",0);
        Category cat = presenter.GetCategory(id);
        if(cat.icono != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(cat.icono, 0, cat.icono.length);
            categoryImage.setImageBitmap(bmp);
        }
        categoryName.setText(cat.name);
        categoryDescription.setText(cat.description);
    }

    @ViewById
    TextView categoryName;

    @ViewById
    TextView categoryDescription;

    @ViewById
    ImageView categoryImage;

}
