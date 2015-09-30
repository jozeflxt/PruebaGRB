package com.grability.myapplication.presenters;

import android.content.Context;

import com.grability.myapplication.entities.Category;
import com.grability.myapplication.interactors.CategoryInteractor;
import com.grability.myapplication.views.DetailActivty;
import com.grability.myapplication.views.MainActivity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Carlos  Muñoz on 25/09/2015.
 */
public class DetailPresenter implements IPresenter  {
    private CategoryInteractor interactor;
    private Context mainActivity;
    public DetailPresenter(Context m)
    {
        this.mainActivity = m;
        this.interactor = new CategoryInteractor(this, m);
    }
    public Category GetCategory(int categoryID)
    {
        return interactor.GetCategory(categoryID);
    }
    public void onListLoaded(List<Category> items)
    {

    }
}
