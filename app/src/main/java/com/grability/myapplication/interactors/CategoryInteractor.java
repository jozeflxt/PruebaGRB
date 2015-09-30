package com.grability.myapplication.interactors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.grability.myapplication.datos.CategoryBLL;
import com.grability.myapplication.entities.Category;
import com.grability.myapplication.presenters.IPresenter;
import com.grability.myapplication.restAPI.EndPointInterface;
import com.grability.myapplication.utilities.DownloadImageTask;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.UiThread;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


/**
 * Created by Carlos  Muñoz on 23/09/2015.
 */
public class CategoryInteractor implements IInteractor {

    private final String TEMP_FILE_NAME = "temp_file_grability.txt";
    File tempFile;
    private CategoryBLL service;
    List<Category> categories;
    IPresenter presenter;
    private Context context;
    public static final String BASE_URL = "https://itunes.apple.com";
    public EndPointInterface apiService;

    public CategoryInteractor(IPresenter p, Context ctx) {
        categories = new ArrayList<Category>();
        presenter = p;
        this.context = ctx;
        this.service = new CategoryBLL(context);
        categories = service.GetCategories();
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String msg) {
                        Log.d("Retrofit", msg);
                    }
                });
        RestAdapter restAdapter = builder.build();
        apiService =
                restAdapter.create(EndPointInterface.class);
    }
    public void GetCategories() {
        apiService.getCategories(20, new retrofit.Callback<Void>() {
            @Override
            public void success(Void v, Response response) {
                String json = new String(((TypedByteArray) response.getBody()).getBytes());
                try {
                    categories = new ArrayList<Category>();
                    JSONObject obj = new JSONObject(json);
                    obj = obj.getJSONObject("feed");
                    JSONArray entries = obj.getJSONArray("entry");
                    for (int i = 0; i < entries.length(); i++) {
                        Category cat = new Category();
                        cat.id = i;
                        cat.name = entries.getJSONObject(i).getJSONObject("im:name").getString("label");
                        cat.description = entries.getJSONObject(i).getJSONObject("summary").getString("label");
                        JSONArray images = entries.getJSONObject(i).getJSONArray("im:image");
                        cat.imageURL = images.getJSONObject(images.length() - 1).getString("label");
                        categories.add(cat);
                    }
                } catch (JSONException e) {

                }
                service.DeleteCategories();
                service.AddCategories(categories);
                for(int i = 0;i< categories.size();i++)
                {
                    new DownloadImageTask(context)
                            .execute(categories.get(i));
                }
                ReadCategories(categories);

            }

            @Override
            public void failure(RetrofitError error) {
                ReadCategories(categories);
            }
        });
    }


    public void ReadCategories(List<Category> categories) {
        presenter.onListLoaded(categories);
    }
    public Category GetCategory(int IdCategory)
    {
        return service.GetCategory(IdCategory);
    }
}
