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

import com.google.android.material.snackbar.Snackbar;
import com.kurt.cryptoapp.R;
import com.kurt.cryptoapp.adapter.CryptoAdapter;
import com.kurt.cryptoapp.db.PrefConfig;
import com.kurt.cryptoapp.model.list.CryptoModel;
import com.kurt.cryptoapp.utils.FavoritesManager;
import com.kurt.cryptoapp.viewmodel.HomeFragmentViewModel;


public class HomeFragment extends Fragment {

    HomeFragmentViewModel homeFragmentViewModel;
    private static final String TAG = "HomeFragment";
    RecyclerView recyclerView;
    CryptoAdapter cryptoAdapter;
    volatile boolean threadWorks = true;
    Context context;


    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        recyclerView = view.findViewById(R.id.home_recycler_view);
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
                homeFragmentViewModel.fetchCryptoModelsToRecycler(cryptoAdapter);
                try {
                    Thread.sleep(2000);
                    Log.d(TAG, "onResume: refreshed");
                } catch (Exception e) {
                    Log.d(TAG, "onResume: Error:" + e.getMessage());
                }
            }
        }).start();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        if (item.getItemId() == 101) {
            CryptoModel favoriteCrypto = cryptoAdapter.getCryptoModelAt(item.getGroupId());

            Log.d(TAG, "onContextItemSelected: will be deleted:" + favoriteCrypto.getDef());

            if (FavoritesManager.addToFavorites(favoriteCrypto.getCod(), context)) {
                Log.d(TAG, "onClick: guncel eklendi:" + favoriteCrypto.toString());
                Snackbar.make(recyclerView, favoriteCrypto.getCod() + " added to favorites", Snackbar.LENGTH_LONG).setAction("Remove", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(context, favoriteCrypto.getCod() + " removed from favorites", Toast.LENGTH_SHORT).show();
                        FavoritesManager.deleteFromFavorites(favoriteCrypto.getCod(), context);
                        Log.d(TAG, "onContextItemSelected: guncel liste" + PrefConfig.getArrayList(context,"favorites"));
                    }
                }).show();
            }else{//cod hali hazırda eklendiyse uyarı göster
                Toast.makeText(context, favoriteCrypto.getCod()+" has already beed added", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return false;
    }





    @Override
    public void onPause() {
        super.onPause();
        threadWorks = false;
       // System.out.println("Ben foregrounde değilim");

    }
}