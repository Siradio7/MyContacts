package com.example.odc_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odc_project.database.Database;
import com.example.odc_project.interfaces.ItemClickListener;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements ItemClickListener {
    private Database database;
    private RecyclerView recyclerViewContacts;
    private ContactAdapter contactAdapter;
    private List<Contact> contacts;
    private EditText editTextSearch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editTextSearch = findViewById(R.id.edit_text_search);
        recyclerViewContacts = findViewById(R.id.recyclerView);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        database = new Database(this);
        contacts = database.getAll();

        contactAdapter = new ContactAdapter(contacts, this);
        recyclerViewContacts.setAdapter(contactAdapter);
    }

    @Override
    public void onItemClickListener(int position) {
        Contact contact = contacts.get(position);
        Intent intent = new Intent(this, ContactDetailsActivity.class);

        intent.putExtra("name", contact.getName());
        intent.putExtra("phone", contact.getPhone());
        intent.putExtra("address", contact.getAddress());
        intent.putExtra("email", contact.getEmail());
        intent.putExtra("isFavorite", contact.isFavorite());
        intent.putExtra("addedDate", contact.getAddedDate());
        startActivity(intent);
    }

    public void startAddContactActivity(View view) {
        Intent intent = new Intent(this, AddContactActivity.class);
        startActivity(intent);
    }

    public void activeSearchEditText(View view) {
        if (editTextSearch.getVisibility() == View.GONE) {
            editTextSearch.setVisibility(View.VISIBLE);
        } else {
            editTextSearch.setVisibility(View.GONE);
            editTextSearch.setText("");
        }

    }
}