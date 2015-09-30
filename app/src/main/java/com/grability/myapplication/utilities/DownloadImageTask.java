package com.grability.myapplication.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.grability.myapplication.datos.CategoryBLL;
import com.grability.myapplication.entities.Category;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Carlos  Muñoz on 24/09/2015.
 */
public class DownloadImageTask extends AsyncTask<Category, Void, Bitmap> {
    Context context;
    Category Category;
    public DownloadImageTask(Context ctx) {
        this.context = ctx;
    }

    protected Bitmap doInBackground(Category... cat) {
        this.Category = cat[0];
        String urldisplay = this.Category.imageURL;
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {

        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        new CategoryBLL(context).UpdateCategoryImage(Category.id,byteArray);
        Category.icono = byteArray;
        new CategoryBLL(this.context).UpdateCategoryImage(Category.id,Category.icono);
    }
}