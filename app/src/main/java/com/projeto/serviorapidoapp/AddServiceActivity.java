package com.projeto.serviorapidoapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddServiceActivity extends AppCompatActivity {

    //  tela
    EditText edtTitle, edtDesc, edtPhone;
    Button btnSave;

    // Referência para o Firebase Realtime Database
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        // Associando os componentes da tela
        edtTitle = findViewById(R.id.edtTitle);
        edtDesc = findViewById(R.id.edtDesc);
        edtPhone = findViewById(R.id.edtPhone);
        btnSave = findViewById(R.id.btnSave);


        // Inicializando referência do Firebase
        dbRef = FirebaseDatabase.getInstance().getReference("services");


        // Ação do botão salvar
        btnSave.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String desc = edtDesc.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();



            // Validação simples
            if (title.isEmpty() || desc.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }



            // Gera um ID automático e salvar no Firebase
            String id = dbRef.push().getKey();
            Service service = new Service(id, title, desc, phone);
            dbRef.child(id).setValue(service).addOnSuccessListener(aVoid -> {
                Toast.makeText(this, "Serviço salvo com sucesso", Toast.LENGTH_SHORT).show();
                finish();
                // Fecha a tela e volta para a principal
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
            });
        });
    }
}
