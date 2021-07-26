package com.kurt.cryptoapp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kurt.cryptoapp.R;
import com.kurt.cryptoapp.adapter.CryptoAdapter;
import com.kurt.cryptoapp.db.PrefConfig;
import com.kurt.cryptoapp.model.list.CryptoModel;
import com.kurt.cryptoapp.utils.FavoritesManager;
import com.kurt.cryptoapp.viewmodel.FavoritesFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    FavoritesFragmentViewModel favoritesFragmentViewModel;
    private static final String TAG = "FavoritesFragment";
    RecyclerView recyclerView;
    CryptoAdapter cryptoAdapter;
    volatile boolean threadWorks = true;
    Context context;
    List<String> favoriteSymbolList = new ArrayList<>();

    public FavoritesFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        context = view.getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoritesFragmentViewModel = ViewModelProviders.of(this).get(FavoritesFragmentViewModel.class);
        recyclerView = view.findViewById(R.id.favorites_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        cryptoAdapter = new CryptoAdapter(getContext());
        recyclerView.setAdapter(cryptoAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        threadWorks = true;
        new Thread(() -> {
            while (threadWorks) {
                updateFavoriteSymbolList();//Her defasında güncel favori listesini sp'den çeker ve aramaları buna göre yaparız
                if (favoriteSymbolList != null) {//favori listesi boş değilse dataları çekmeye başla
                    favoritesFragmentViewModel.fetchCryptoModelsToRecycler(cryptoAdapter, favoriteSymbolList);
                }
                try {
                    Thread.sleep(2000);
                    //Log.d(TAG, "onResume: refreshed");
                } catch (Exception e) {
                    Log.d(TAG, "onResume: Error:" + e.getMessage());
                }
            }
        }).start();
    }

    private void updateFavoriteSymbolList() {//sp'deki listeyi alıp classtaki listeye atar
        favoriteSymbolList=PrefConfig.getArrayList(context,"favorites");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        if (item.getItemId() == 102) {
            CryptoModel selectedCrypto = cryptoAdapter.getCryptoModelAt(item.getGroupId());

            Log.d(TAG, "onContextItemSelected: will be deleted:" + selectedCrypto.getDef());

            FavoritesManager.deleteFromFavorites(selectedCrypto.getCod(), context);
            Toast.makeText(context, selectedCrypto.getCod() + " deleted from favorites", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        threadWorks = false;
    }
}