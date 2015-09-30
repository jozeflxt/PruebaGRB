package com.grability.myapplication.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by Carlos  Muñoz on 23/09/2015.
 */


@Table(name = "Cateogry")
public class Category extends Model implements Serializable {


    @Column(name = "IdCategory")
    public Integer id;
    @Column(name = "Name")
    public String name;
    @Column(name = "Description")
    public String description;
    public String imageURL;
    @Column(name = "Image")
    public byte[] icono;
    public Category()
    {

    }
    public Category(String n,String d,String p)
    {
        this.name = n;
        this.description = d;
        this.imageURL = p;
    }
}
