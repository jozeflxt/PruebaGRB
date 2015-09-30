package com.grability.myapplication.presenters;

import com.grability.myapplication.entities.Category;
import com.grability.myapplication.interactors.CategoryInteractor;
import com.grability.myapplication.views.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos  Muñoz on 23/09/2015.
 */
public class MainPresenter implements  IPresenter {
    private CategoryInteractor mainInteractor;
    private MainActivity mainActivity;
    public MainPresenter(MainActivity m)
    {
        this.mainActivity = m;
        this.mainInteractor = new CategoryInteractor(this, m);
    }
    public void GetCategories() {
        mainInteractor.GetCategories();
    }
    public void onListLoaded(List<Category> items)
    {
        mainActivity.setCategories(items);
    }
}
