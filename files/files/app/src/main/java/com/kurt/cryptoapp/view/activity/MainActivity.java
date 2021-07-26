package com.kurt.cryptoapp.view.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kurt.cryptoapp.R;
import com.kurt.cryptoapp.db.PrefConfig;
import com.kurt.cryptoapp.view.fragment.FavoritesFragment;
import com.kurt.cryptoapp.view.fragment.HomeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    String[] list = {"hig", "buy", "ddi", "cl3", "pdc", "tke", "rtp", "pdd", "low", "sel", "las"};
    boolean[] selectedOption;
    ArrayList<String> selectedOptionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


    }

    public void showOptions() {//seçim ekranı açıldığında güncel listeyi temizle

        AlertDialog.Builder sayWindows = new AlertDialog.Builder(
                this);
        sayWindows.setPositiveButton("ok", null);
        sayWindows.setTitle("Please select 2 option");
        sayWindows.setCancelable(false);

        selectedOptionList.clear();
        selectedOption = new boolean[list.length];
        sayWindows.setMultiChoiceItems(list, selectedOption, new DialogInterface.OnMultiChoiceClickListener() {
            int count = 0;
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                count += b ? 1 : -1;
                if (count > 2) {//2 den fazla item seçildiğinde her seçilen yeni itemi false yap
                    Toast.makeText(getApplicationContext(), "Please select only 2 items", Toast.LENGTH_SHORT).show();
                    selectedOption[i] = false;
                    count--;
                    ((AlertDialog) dialogInterface).getListView().setItemChecked(i, false);
                } else {//2 den fazla item seçilmediyse listeye ekle
                    if (b) {
                        selectedOptionList.add(list[i]);
                    } else {
                        selectedOptionList.remove(list[i]);
                    }
                }
            }
        });


        final AlertDialog mAlertDialog = sayWindows.create();
        mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {

                Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if(selectedOptionList.size()==2){
                            PrefConfig.saveArrayList(getApplicationContext(), selectedOptionList, "options");
                            dialog.dismiss();
                        }else{
                            Toast.makeText(MainActivity.this, "Please select 2 items", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        mAlertDialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.change_options:
                //popup aç
                Log.d(TAG, "onOptionsItemSelected: menu clicked");
                showOptions();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_favorites:
                    selectedFragment = new FavoritesFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

}