package com.kurt.cryptoapp.utils;

import android.content.Context;
import android.util.Log;

import com.kurt.cryptoapp.db.PrefConfig;

import java.util.ArrayList;

public class FavoritesManager {
    private static final String TAG = "FavoritesManager";

    public static boolean addToFavorites(String cod, Context context) {
        ArrayList<String> favoritesList;
        Boolean found = false;
        if (PrefConfig.getArrayList(context, "favorites") != null) {//Sp listesi null değilse
            favoritesList = PrefConfig.getArrayList(context, "favorites");
        } else {//Sp listesi null ise
            favoritesList = new ArrayList<>();
        }
        if (favoritesList.contains(cod)) {
            found = true;
        }
        if (!found) {
            favoritesList.add(cod);
            Log.d(TAG, "addToFavorites: eklendi:" + cod);
        }

        PrefConfig.saveArrayList(context, favoritesList, "favorites");
        return !found;//eklendiyse true döndürür
    }

    public static void deleteFromFavorites(String cod, Context context) {
        ArrayList<String> favoritesList;
        if (PrefConfig.getArrayList(context, "favorites") != null) {//Sp listesi null değilse
            favoritesList = PrefConfig.getArrayList(context, "favorites");
        } else {//Sp listesi null ise
            favoritesList = new ArrayList<>();
        }
        Log.d(TAG, "deleteFromFavorites: silmeden önce:" + favoritesList.toString());

        Boolean found = false;

        if (favoritesList.contains(cod)) {
            found = true;
        }

        if (found) {
            favoritesList.remove(cod);

            PrefConfig.saveArrayList(context, favoritesList, "favorites");
            Log.d(TAG, "deleteFromFavorites: sildikten sonra:" + favoritesList.toString());
        }


    }

    public static boolean found(String cod, Context context) {
        ArrayList<String> favoritesList;
        Boolean found = false;
        if (PrefConfig.getArrayList(context, "favorites") != null) {//Sp listesi null değilse
            favoritesList = PrefConfig.getArrayList(context, "favorites");
        } else {//Sp listesi null ise
            favoritesList = new ArrayList<>();
        }

        if (favoritesList.contains(cod)) {//hali hazırda favori listesine bu sembol eklenmişse uyar
            found = true;
        }


        return found;
    }
}
