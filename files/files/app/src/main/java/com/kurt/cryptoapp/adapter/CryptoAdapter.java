package com.kurt.cryptoapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kurt.cryptoapp.R;
import com.kurt.cryptoapp.db.PrefConfig;
import com.kurt.cryptoapp.model.list.CryptoModel;
import com.kurt.cryptoapp.view.activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.MyViewHolder> {
    private static final String TAG = "CryptoAdapter";
    Context context;
    String fragmentSection;
    private List<CryptoModel> cryptoModelList = new ArrayList<>();
    ArrayList<String> selectedOptionList = new ArrayList<>();

    public CryptoAdapter(Context context) {
        this.context = context;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_clo.setText(cryptoModelList.get(position).getClo());
        holder.tv_cod.setText(cryptoModelList.get(position).getCod());
        holder.tv_def.setText(cryptoModelList.get(position).getDef());
        if (PrefConfig.getArrayList(context, "options") != null) {//element listesi null değilse olanı al
            selectedOptionList = PrefConfig.getArrayList(context, "options");
        } else {//Sp listesi null ise default değerler ata
            selectedOptionList.add(0, "las");
            selectedOptionList.add(1, "hig");
        }
        Log.d(TAG, "onBindViewHolder: 1.index:" + selectedOptionList.get(0) + " 2.index:" + selectedOptionList.get(1));

        switch (selectedOptionList.get(0)) {
            case ""://hiç bir veri yoksa firstoption'da las göster
                holder.tv_firstoption.setText("Las: " + cryptoModelList.get(position).getLas());
                break;
            case "hig":
                holder.tv_firstoption.setText("Hig: " + cryptoModelList.get(position).getHig());
                break;
            case "buy":
                holder.tv_firstoption.setText("Buy: " + cryptoModelList.get(position).getBuy());
                break;
            case "ddi":
                holder.tv_firstoption.setText("Ddi: " + cryptoModelList.get(position).getDdi());
                break;
            case "cl3":
                holder.tv_firstoption.setText("Cl3: " + cryptoModelList.get(position).getCl3());
                break;
            case "pdc":
                holder.tv_firstoption.setText("Pdc: " + cryptoModelList.get(position).getPdc());
                break;
            case "tke":
                holder.tv_firstoption.setText("Tke: " + cryptoModelList.get(position).getTke());
                break;
            case "rtp":
                holder.tv_firstoption.setText("Rtp:" + cryptoModelList.get(position).getRtp());
                break;
            case "pdd":
                holder.tv_firstoption.setText("Pdd: " + cryptoModelList.get(position).getPdd());
                break;
            case "low":
                holder.tv_firstoption.setText("Low: " + cryptoModelList.get(position).getLow());
                break;
            case "sel":
                holder.tv_firstoption.setText("Sel: " + cryptoModelList.get(position).getSel());
                break;
            case "las":
                holder.tv_firstoption.setText("Las:" + cryptoModelList.get(position).getLas());
                break;
            default:
                Log.d(TAG, "onBindViewHolder: 0.indexte hiç veriyok");
        }

        switch (selectedOptionList.get(1)) {
            case ""://hiç bir veri yoksa firstoption'da las göster
                Log.d(TAG, "onBindViewHolder: 1.indexte hiç veri yok");
                holder.tv_secondoption.setText("Las: " + cryptoModelList.get(position).getLas());
                break;
            case "hig":
                holder.tv_secondoption.setText("Hig: " + cryptoModelList.get(position).getHig());
                break;
            case "buy":
                holder.tv_secondoption.setText("Buy: " + cryptoModelList.get(position).getBuy());
                break;
            case "ddi":
                holder.tv_secondoption.setText("Ddi: " + cryptoModelList.get(position).getDdi());
                break;
            case "cl3":
                holder.tv_secondoption.setText("Cl3: " + cryptoModelList.get(position).getCl3());
                break;
            case "pdc":
                holder.tv_secondoption.setText("Pdc:" + cryptoModelList.get(position).getPdc());
                break;
            case "tke":
                holder.tv_secondoption.setText("Tke: " + cryptoModelList.get(position).getTke());
                break;
            case "rtp":
                holder.tv_secondoption.setText("Rtp: " + cryptoModelList.get(position).getRtp());
                break;
            case "pdd":
                holder.tv_secondoption.setText("Pdd: " + cryptoModelList.get(position).getPdd());
                break;
            case "low":
                holder.tv_secondoption.setText("Low: " + cryptoModelList.get(position).getLow());
                break;
            case "sel":
                holder.tv_secondoption.setText("Sel: " + cryptoModelList.get(position).getSel());
                break;
            case "las":
                holder.tv_secondoption.setText("Las: " + cryptoModelList.get(position).getLas());
                break;
            default:
                Log.d(TAG, "onBindViewHolder: 1.indexte hiç veriyok");
                break;
        }



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Cod:" + cryptoModelList.get(position).getCod(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", getCryptoModelAt(position));
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return cryptoModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView tv_cod, tv_firstoption, tv_secondoption, tv_def, tv_clo;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cod = itemView.findViewById(R.id.cod);
            tv_firstoption = itemView.findViewById(R.id.textview_firstoption);
            tv_secondoption = itemView.findViewById(R.id.textview_secondoption);
            tv_def = itemView.findViewById(R.id.def);
            tv_clo = itemView.findViewById(R.id.clo);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            if(fragmentSection.equals("home")){
                contextMenu.setHeaderTitle("Select Operation");
                contextMenu.add(getAdapterPosition(), 101, 0, "Add to favorites");
            }else if(fragmentSection.equals("favorites")){
                contextMenu.setHeaderTitle("Select Operation");
                contextMenu.add(getAdapterPosition(), 102, 0, "Delete from favorites");
            }

        }
    }

    public void setData(List<CryptoModel> cryptoModelList,String fragmentSection) {
        this.cryptoModelList = cryptoModelList;
        this.fragmentSection=fragmentSection;
        notifyDataSetChanged();
    }

    public CryptoModel getCryptoModelAt(int position) {
        return cryptoModelList.get(position);
    }
}
