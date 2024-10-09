package com.jagdon.todolistapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jagdon.todolistapp.ToDoAdapter.ToDoAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView itemsRecyclerView;
    private EditText itemEditText;
    private Button addButton;
    private ArrayList<String> items;
    private ToDoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);
        itemEditText = findViewById(R.id.itemEditText);
        addButton = findViewById(R.id.addButton);

        // Initialize the items list before creating the adapter
        items = new ArrayList<>();
        // Now create the adapter with the newly initialized list and context
        adapter = new ToDoAdapter(items, this);

        itemsRecyclerView.setAdapter(adapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton.setOnClickListener(v -> {
            String itemText = itemEditText.getText().toString();
            if (!itemText.isEmpty()) {
                items.add(itemText);
                adapter.notifyItemInserted(items.size() - 1);
                itemEditText.setText("");
            }
        });
    }
}
