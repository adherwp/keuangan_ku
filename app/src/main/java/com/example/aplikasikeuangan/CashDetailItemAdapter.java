package com.example.aplikasikeuangan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CashDetailItemAdapter extends RecyclerView.Adapter<CashDetailItemAdapter.ExampleViewHolder> {
    private List<CashItem> cashItems = new ArrayList<>();

    int Pemasukan = 0;

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cash_detail, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        CashItem currentItem = cashItems.get(position);

        Pemasukan = currentItem.getIncome();

        if(Pemasukan == 1) {
            holder.gambarPanah.setImageResource(R.drawable.arrow_income);
            holder.ketNominal.setText(Integer.toString(currentItem.getNominal()));
            holder.ketKeterangan.setText(currentItem.getKeterangan());
            holder.ketTanggal.setText(currentItem.getTanggal());
        }

        else if(Pemasukan == 0){
            holder.gambarPanah.setImageResource(R.drawable.arrow_outcome);
            holder.ketNominal.setText(Integer.toString(currentItem.getNominal()));
            holder.ketKeterangan.setText(currentItem.getKeterangan());
            holder.ketTanggal.setText(currentItem.getTanggal());
        }

    }

    public void setCashItems(List<CashItem> cashItems) {
        this.cashItems = cashItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cashItems.size();
    }

    class ExampleViewHolder extends RecyclerView.ViewHolder {

        private ImageView gambarPanah;
        private TextView ketNominal;
        private TextView ketKeterangan;
        private TextView ketTanggal;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            gambarPanah = itemView.findViewById(R.id.gambarPanah);
            ketNominal = itemView.findViewById(R.id.ketNominal);
            ketKeterangan = itemView.findViewById(R.id.ketKeterangan);
            ketTanggal = itemView.findViewById(R.id.ketTanggal);
        }
    }


}
