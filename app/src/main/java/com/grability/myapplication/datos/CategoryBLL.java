package com.grability.myapplication.datos;

import android.content.Context;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.grability.myapplication.entities.Category;

import java.util.List;

/**
 * Created by Carlos  Muñoz on 25/09/2015.
 */
public class CategoryBLL {
    public CategoryBLL(Context context)
    {
        ActiveAndroid.initialize(context);
    }
    public List<Category> GetCategories()
    {
        return new Select().from(Category.class).execute();
    }
    public Category GetCategory(int IdCategory)
    {
        return new Select()
                .from(Category.class)
                .where("IdCategory = ?", IdCategory).executeSingle();
    }
    public void DeleteCategories(){
        new Delete().from(Category.class).execute();
    }
    public  void AddCategories(List<Category> categories)
    {
        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < categories.size(); i++) {
                Category item = categories.get(i);
                item.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        catch (Exception e)
        {
            Log.v("LXT",e.toString());
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }
    public void UpdateCategoryImage(Integer categoryID,byte[] image)
    {
        Category oldCategory = GetCategory(categoryID);
        oldCategory.icono = image;
        oldCategory.save();
    }
}
