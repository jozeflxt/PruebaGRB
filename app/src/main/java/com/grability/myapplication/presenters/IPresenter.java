package com.grability.myapplication.presenters;

import com.grability.myapplication.entities.Category;

import java.util.List;

/**
 * Created by Carlos  Muñoz on 25/09/2015.
 */
public interface IPresenter {
    void onListLoaded(List<Category> items);
}
