package com.example.rituka.uploadrecipe.models;

import android.content.Context;

/**
 * Created by rituka on 27/10/17.
 */

public class Shared_Preferences {

    String user_name;

    static public String getusername(Context context)
    {
        final String PREF_NAME = "com.data.wfi.userdetails";
        android.content.SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString("NAME","") ;
    }




}
