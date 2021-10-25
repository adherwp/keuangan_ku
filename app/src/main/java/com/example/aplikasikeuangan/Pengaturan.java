package com.example.aplikasikeuangan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pengaturan extends AppCompatActivity {

    private UserViewModel userViewModel;

    private EditText editPasswordLama;
    private EditText editPasswordBaru;

    private String passwordLama;
    private String usernameLama;

    private String insertPasswordLama;
    private String insertPasswordBaru;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        userViewModel = (UserViewModel) new ViewModelProvider(this).get(UserViewModel.class);

        editPasswordLama = findViewById(R.id.password_lama);
        editPasswordBaru = findViewById(R.id.password_baru);

        Button btnSimpanPengaturan = findViewById(R.id.btnSimpanPengaturan);
        Button btnKembali = findViewById(R.id.btnKembaliPengaturan);

        passwordLama = userViewModel.getPassword();
        usernameLama = userViewModel.getUsername();
        idUser = userViewModel.getId();

        Log.d("PASSWORD LAMA", passwordLama);
        Log.d("USERNAME LAMA", usernameLama);
        Log.d("ID USER", Integer.toString(idUser));

        btnSimpanPengaturan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                insertPasswordLama = editPasswordLama.getText().toString();
                insertPasswordBaru = editPasswordBaru.getText().toString();

                if (insertPasswordLama.isEmpty() || insertPasswordBaru.isEmpty()) {

                    Toast.makeText(getBaseContext(), "Masukkan Password Baru dan Password Lama", Toast.LENGTH_SHORT).show();

                } else if (insertPasswordLama.equals(passwordLama)) {
                    UserItem userItem = new UserItem(usernameLama, insertPasswordBaru);
                    userItem.setId(idUser);
                    userViewModel.update(userItem);

                    Toast.makeText(getBaseContext(), "Password Berhasil Diganti!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Password Lama Tidak Sesuai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pengaturan.this, Beranda.class));
            }
        });

    }
}