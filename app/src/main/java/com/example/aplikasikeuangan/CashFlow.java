package com.example.aplikasikeuangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class CashFlow extends AppCompatActivity {
    private CashViewModel cashViewModel;

    int nominal;
    String keterangan;
    String tanggal_pemasukan;
    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_flow);

        Button btnKembali = findViewById(R.id.btnKembaliCashFlow);

        btnKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(CashFlow.this, Beranda.class));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CashDetailItemAdapter cashAdapter = new CashDetailItemAdapter();
        recyclerView.setAdapter(cashAdapter);

        cashViewModel = (CashViewModel) new ViewModelProvider(this).get(CashViewModel.class);
        cashViewModel.getAllCashItem().observe(this, new Observer<List<CashItem>>() {
            @Override
            public void onChanged(List<CashItem> cashItems) {
                cashAdapter.setCashItems(cashItems);
            }
        });

        Bundle pathBundle = getIntent().getExtras();

        if (pathBundle != null) {
            nominal = getIntent().getIntExtra("NOMINAL", 1);
            keterangan = getIntent().getStringExtra("KETERANGAN");
            tanggal_pemasukan = getIntent().getStringExtra("TANGGAL");
            status = getIntent().getIntExtra("STATUS", 0);

            CashItem cashItem = new CashItem(nominal, keterangan, tanggal_pemasukan, status);
            cashViewModel.insert(cashItem);
        }
    }
}