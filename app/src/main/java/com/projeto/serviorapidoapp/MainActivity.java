package com.projeto.serviorapidoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerServices;
    List<Service> services = new ArrayList<>();
    ServiceAdapter adapter;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // organizando os itens verticalmente.
        recyclerServices = findViewById(R.id.recyclerServices);
        recyclerServices.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ServiceAdapter(services);
        recyclerServices.setAdapter(adapter);

        // Define um lista aonde novos serviços podem ser adicionados.
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddServiceActivity.class));
        });

        dbRef = FirebaseDatabase.getInstance().getReference("services");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { // quando há mudanças no banco de dados , converte em objetos
                services.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Service s = data.getValue(Service.class);
                    services.add(s);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Erro ao carregar dados", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
