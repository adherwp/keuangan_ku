package com.example.aplikasikeuangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;

public class Beranda extends AppCompatActivity {

    private CashViewModel cashViewModel;

    private String total_pemasukan;
    private String total_pengeluaran;

    private int totalPemasukan;
    private int totalPengeluaran;

    TextView textTotalPemasukan;
    TextView textTotalPengeluaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        // Awal Kode Graph


        AnyChartView anyChartView = findViewById(R.id.gambarGrafik);
        anyChartView.setProgressBar(findViewById(R.id.progressBar));

        Cartesian cartesian = AnyChart.line();

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Grafik Jumlah Pengeluaran dan Pemasukan");

        cartesian.yAxis(0).title("Jumlah Nominal");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("22/10/2021", 500000, 0));
        seriesData.add(new CustomDataEntry("23/10/2021", 300000, 0));
        seriesData.add(new CustomDataEntry("24/10/2021", 0, 100000));
        seriesData.add(new CustomDataEntry("25/10/2021", 500000, 0));
        seriesData.add(new CustomDataEntry("26/10/2021", 400000, 0));
        seriesData.add(new CustomDataEntry("29/10/2021", 0, 500000));


        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name("Pemasukan");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name("Pengeluaran");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);

        // Akhir Kode Graph

        cashViewModel = (CashViewModel) new ViewModelProvider(this).get(CashViewModel.class);

        totalPemasukan = cashViewModel.getJumlahPemasukan();
        totalPengeluaran = cashViewModel.getJumlahPengeluaran();

        textTotalPemasukan = (TextView) findViewById(R.id.labelJumlahPemasukan);
        textTotalPengeluaran = (TextView) findViewById(R.id.labelJumlahPengeluaran);

        total_pemasukan = "Pemasukan: " + totalPemasukan;
        total_pengeluaran = "Pengeluaran: " + totalPengeluaran;

        textTotalPemasukan.setText(total_pemasukan);
        textTotalPengeluaran.setText(total_pengeluaran);

        ImageButton btnPemasukan = findViewById(R.id.labelTambahPemasukan);
        ImageButton btnPengeluaran = findViewById(R.id.labelTambahPengeluaran);
        ImageButton btnDetailCashFlow = findViewById(R.id.labelDetailCashFlow);
        ImageButton btnPengaturan = findViewById(R.id.labelPengaturan);
        ImageButton btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Beranda.this, Login.class));
                Toast.makeText(getBaseContext(), "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
            }
        });

        btnPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Beranda.this, Pemasukan.class));
            }
        });

        btnPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Beranda.this, Pengeluaran.class));
            }
        });

        btnDetailCashFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Beranda.this, CashFlow.class));
            }
        });

        btnPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Beranda.this, Pengaturan.class));
            }
        });
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }

    }
}