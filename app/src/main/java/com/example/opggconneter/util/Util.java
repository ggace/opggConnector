package com.example.opggconneter.util;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
    private static Application application;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor spEditor;

    public static void init(Application application) {
        Util.application = application;

        sp = PreferenceManager.getDefaultSharedPreferences(application);
        spEditor = sp.edit();
    }

    public static void spRemove(String key){
        spEditor.remove(key);
    }

    public static void spPut(String key, Object obj) {
        spPut(key, objToJsonString(obj));
    }

    public static void log(String msg) {
        Log.d("AA1", msg);
    }

    public static String objToJsonString(Object obj) {
        ObjectMapper om = new ObjectMapper();

        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static <T> T spGetObj(String key, Class<T> cls) {
        String jsonString = spGetString(key, "");

        ObjectMapper om = new ObjectMapper();

        try {
            return (T)om.readValue(jsonString, cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static void toast(String msg) {
        Toast.makeText(application, msg, Toast.LENGTH_SHORT).show();
    }

    public static void spPut(String key, int value) {
        spEditor.putInt(key, value);
    }

    public static void spPut(String key, boolean value) {
        spEditor.putBoolean(key, value);
    }

    public static void spPut(String key, String value) {
        spEditor.putString(key, value);
    }

    public static void spCommit() {
        spEditor.commit();
    }

    public static int spGetInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public static boolean spGetBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public static String spGetString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public static <T> T spGetObj(String key, TypeReference<T> cls) {
        String jsonString = spGetString(key, "");

        ObjectMapper om = new ObjectMapper();

        try {
            return (T)om.readValue(jsonString, cls);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
