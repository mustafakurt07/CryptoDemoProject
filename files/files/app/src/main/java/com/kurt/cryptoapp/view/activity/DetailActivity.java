package com.kurt.cryptoapp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.kurt.cryptoapp.R;
import com.kurt.cryptoapp.model.list.CryptoModel;
import com.kurt.cryptoapp.utils.FavoritesManager;
import com.kurt.cryptoapp.viewmodel.DetailActivityViewModel;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    volatile boolean threadWorks = true;
    String cod;
    List<CryptoModel> favoriteList;
    CryptoModel detailObject;
    DetailActivityViewModel detailActivityViewModel;
    TextView textViewCod, textViewDetails;
    Switch switchFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewCod = findViewById(R.id.textview_da_cod);
        textViewDetails = findViewById(R.id.textview_da_details);
        switchFavorite = findViewById(R.id.switch_favorite);
        detailObject = (CryptoModel) getIntent().getSerializableExtra("object");
        textViewCod.setText("Cod: " + detailObject.getCod());
        detailActivityViewModel = ViewModelProviders.of(this).get(DetailActivityViewModel.class);

        if (FavoritesManager.found(detailObject.getCod(), getApplicationContext())) {//bu cod favorilere alındıysa switchin değerini değiştir
            switchFavorite.setChecked(true);
            Log.d(TAG, "onCreate: foundtest:" + detailObject.getCod() + " true");
        } else {
            switchFavorite.setChecked(false);
            Log.d(TAG, "onCreate: foundtest:" + detailObject.getCod() + " false");
        }


        switchFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {//switch aktif hale getirildiyse sembolü favorilere ekle
                    Log.d(TAG, "onCheckedChanged: true" + detailObject.toString() + " eklenecek");
                    FavoritesManager.addToFavorites(detailObject.getCod(), getApplicationContext());
                } else {
                    Log.d(TAG, "onCheckedChanged: false" + detailObject.toString() + " eklenecek");
                    FavoritesManager.deleteFromFavorites(detailObject.getCod(), getApplicationContext());
                }
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        threadWorks = true;
        new Thread(() -> {
            while (threadWorks) {
                detailActivityViewModel.fetchDetails(detailObject.getCod(), textViewDetails);
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
    protected void onPause() {
        super.onPause();
        threadWorks=false;
    }
}