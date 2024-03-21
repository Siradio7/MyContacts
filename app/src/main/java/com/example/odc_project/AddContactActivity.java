package com.example.odc_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.odc_project.database.Database;

import java.util.Timer;
import java.util.TimerTask;

public class AddContactActivity extends AppCompatActivity {
    EditText editTextName, editTextPhone, editTextEmail, editTextAddress;
    CheckBox checkBoxIsFavorite;
    Button buttonAdd;
    Database database = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        checkBoxIsFavorite = findViewById(R.id.checkBoxIsFavorite);
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(v -> {
            String name, phone, address, email;
            boolean isFavorite;

            name = editTextName.getText().toString();
            phone = editTextPhone.getText().toString();
            address = editTextAddress.getText().toString();
            email = editTextEmail.getText().toString();
            isFavorite = checkBoxIsFavorite.isChecked();

            if (!name.isEmpty() && !phone.isEmpty() && !address.isEmpty()) {
                if (database.addContact(new Contact(name, phone, address, email, isFavorite)) > 0) {
                    Toast.makeText(this, "Contact ajouté avec succès", Toast.LENGTH_SHORT).show();

                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(String.valueOf(HomeActivity.class));
                            startActivity(intent);
                        }
                    };

                    timer.schedule(task, 1000);
                } else {
                    Toast.makeText(this, "Erreur, veuillez reprendre", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
        });
    }
}