package com.example.movieapp.util;

import androidx.room.TypeConverter;

import com.example.movieapp.model.CastItem;
import com.example.movieapp.model.DirectorsItem;
import com.example.movieapp.model.ProductionCompaniesItem;
import com.example.movieapp.model.WritersItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromStringList(List<String> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<String> toStringList(String json) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    @TypeConverter
    public static String fromDirectorsList(List<DirectorsItem> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<DirectorsItem> toDirectorsList(String json) {
        Type listType = new TypeToken<List<DirectorsItem>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    @TypeConverter
    public static String fromCastList(List<CastItem> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<CastItem> toCastList(String json) {
        Type listType = new TypeToken<List<CastItem>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    @TypeConverter
    public static String fromWritersList(List<WritersItem> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<WritersItem> toWritersList(String json) {
        Type listType = new TypeToken<List<WritersItem>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    @TypeConverter
    public static String fromProductionCompaniesList(List<ProductionCompaniesItem> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<ProductionCompaniesItem> toProductionCompaniesList(String json) {
        Type listType = new TypeToken<List<ProductionCompaniesItem>>() {}.getType();
        return gson.fromJson(json, listType);
    }
}
