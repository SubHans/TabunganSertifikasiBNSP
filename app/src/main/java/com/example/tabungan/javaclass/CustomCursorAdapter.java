package com.example.tabungan.javaclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabungan.R;

import java.util.ArrayList;

public class CustomCursorAdapter extends RecyclerView.Adapter<CustomCursorAdapter.ViewHolder> {
    private ArrayList<UangModel> cashModelArrayList;
    private Context context;

    public CustomCursorAdapter(ArrayList<UangModel> cashModelArrayList, Context context) {
        this.cashModelArrayList = cashModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.datain, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UangModel model = cashModelArrayList.get(position);
        if (model.getStatus() == 0) {
            holder.imageArrow.setImageResource(R.drawable.panah_masuk);
            holder.nominal.setText("[-]Rp. "+model.getNominal());
            holder.keterangan.setText(model.getKeterangan());
            holder.tanggal.setText(model.getTanggal());
        } else {
            holder.imageArrow.setImageResource(R.drawable.panah_keluar);
            holder.nominal.setText("[+]Rp. "+model.getNominal());
            holder.keterangan.setText(model.getKeterangan());
            holder.tanggal.setText(model.getTanggal());
        }
    }

    @Override
    public int getItemCount() {
        return cashModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nominal, keterangan, tanggal;
        private ImageView imageArrow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nominal = itemView.findViewById(R.id.uang);
            keterangan = itemView.findViewById(R.id.keterangan);
            tanggal = itemView.findViewById(R.id.tanggal);
            imageArrow = itemView.findViewById(R.id.gambar);
        }
    }
}
