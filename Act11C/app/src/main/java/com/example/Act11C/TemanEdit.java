package com.example.Act11C;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Act11C.database.Teman;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TemanEdit extends AppCompatActivity {
    private TextView tv_Key;
    private EditText ed_Nama, ed_Telpon;
    private Button btnEdit;
    private DatabaseReference database;
    String kode, nama, telpon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_edit);

        ed_Nama = findViewById(R.id.edBarang);
        ed_Telpon = findViewById(R.id.edKode);
        tv_Key = findViewById(R.id.tv_key);
        btnEdit = findViewById(R.id.btEdit);

        database = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = this.getIntent().getExtras();
        kode = bundle.getString("kunci1");
        nama = bundle.getString("kunci2");
        telpon = bundle.getString("kunci3");



        tv_Key.setText("Key : "+kode);
        ed_Nama.setText(nama);
        ed_Telpon.setText(telpon);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTeman(new Teman(ed_Nama.getText().toString(), ed_Telpon.getText().toString()));
            }
        });
    }

    public void editTeman(Teman nama){
        database.child("Teman")
                .child(kode)
                .setValue(nama)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(TemanEdit.this, "Data Berhasil Diupdate", Toast.LENGTH_LONG).show();
                        finish();

                    }
                });
    }
}