package com.grability.myapplication.entities;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos  Muñoz on 23/09/2015.
 */
public class TopFree  {
    @SerializedName("feed")
    Feed feed;
}
class Feed{
    @SerializedName("author")
    JsonObject  author;
    @SerializedName("entry")
    JsonObject entry;
}