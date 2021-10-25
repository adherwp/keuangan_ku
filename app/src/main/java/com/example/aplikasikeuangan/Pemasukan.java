package com.example.aplikasikeuangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Pemasukan extends AppCompatActivity {

    private CashViewModel cashViewModel;

    private EditText etDate;
    private EditText editTextNominal;
    private EditText editTextKeterangan;

    private String date;

    private int nominal_pemasukan = 0;

    private String pemasukan;
    private String keterangan_pemasukan;
    private String tanggal_pemasukan;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemasukan);

        editTextNominal = findViewById(R.id.nominal_pemasukan);
        editTextKeterangan = findViewById(R.id.keterangan_pemasukan);

        Button btnKembali = findViewById(R.id.btnKembaliPemasukan);

        Button btnSimpan = findViewById(R.id.btnSimpanPemasukan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                keterangan_pemasukan = editTextKeterangan.getText().toString();
                tanggal_pemasukan = date;
                pemasukan = editTextNominal.getText().toString();

                try{
                    if (keterangan_pemasukan.isEmpty() || tanggal_pemasukan.isEmpty() || pemasukan.isEmpty()) {
                        Log.d("DATA KOSONG", "DATA TIDAK DIMASUKKAN");
                        Toast.makeText(getBaseContext(), "Nominal Pemasukan & Tanggal Pemasukan & Keterangan Pemasukan Harus Diisi", Toast.LENGTH_SHORT).show();
                    } else {
                        nominal_pemasukan = Integer.parseInt(pemasukan);

                        Log.d("NOMINAL PEMASUKAN_1", Integer.toString(nominal_pemasukan) );
                        Log.d("KETERANGAN PEMASUKAN_1", keterangan_pemasukan );
                        Log.d("TANGGAL PEMASUKAN_1", tanggal_pemasukan );

                        Intent intent = new Intent(getBaseContext(), CashFlow.class);
                        intent.putExtra("NOMINAL", nominal_pemasukan);
                        intent.putExtra("KETERANGAN", keterangan_pemasukan);
                        intent.putExtra("TANGGAL", tanggal_pemasukan);
                        intent.putExtra("STATUS", 1);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), "Data Berhasil Dimasukkan!", Toast.LENGTH_SHORT).show();

                    }
                }

                catch(Exception e)  {
                    Toast.makeText(getBaseContext(), "Nominal Pemasukan & Tanggal Pemasukan & Keterangan Pemasukan Harus Diisi", Toast.LENGTH_SHORT).show();
                }

            }
        });

        etDate = findViewById(R.id.et_date_pemasukan);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        btnKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pemasukan.this, Beranda.class));
            }
        });


        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Pemasukan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        date = day + "/" + month + "/" + year;
                        etDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}