package com.training.personalaccountservice;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PASettingsProvider extends ContentProvider {


    public static final String AUTHORITY="com.training.personalaccountservice";

    public static final String PATH_ACTIVE_PROFILE_SETTINGS="ACTIVE_PROFILE_SETTINGS";

    public static final Uri CONTENT_URI= Uri.parse("content://"+AUTHORITY+"/"+PATH_ACTIVE_PROFILE_SETTINGS);


    public static final  int ACTIVE_PROFILE_SETTINGS = 1;

    private static final UriMatcher MATCHER =new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, PATH_ACTIVE_PROFILE_SETTINGS, ACTIVE_PROFILE_SETTINGS);
    }

    public static final String MIME_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE+"/"+"vnd.com.training.settings";

    PAServiceManager manager;

    @Override
    public boolean onCreate() {
        Log.i("Content provider","onCreate called");
        manager=PAServiceManager.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        Log.i("Content provider","Query called");
        Cursor cursor = null;
        if (MATCHER.match(uri) == ACTIVE_PROFILE_SETTINGS)
            cursor = manager.readActiveProfileSettings();

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri)
    {
        if(MATCHER.match(uri)==ACTIVE_PROFILE_SETTINGS){
            return MIME_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        Log.i("Content provider","update called");
        int updateCount =-1;
        if (MATCHER.match(uri) == ACTIVE_PROFILE_SETTINGS) {
            updateCount=manager.updateActiveProfileSettings(contentValues);
        }

        return updateCount;
    }
}
