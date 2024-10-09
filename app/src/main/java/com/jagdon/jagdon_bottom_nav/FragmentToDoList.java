package com.jagdon.jagdon_bottom_nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentToDoList extends Fragment {
    private RecyclerView itemsRecyclerView;
    private EditText itemEditText;
    private Button addButton;
    private ArrayList<String> items;
    private ToDoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todolist, container, false);

        itemsRecyclerView = view.findViewById(R.id.itemsRecyclerView);
        itemEditText = view.findViewById(R.id.itemEditText);
        addButton = view.findViewById(R.id.addButton);

        // Initialize the items list before creating the adapter
        items = new ArrayList<>();
        // Create the adapter with the newly initialized list and context
        adapter = new ToDoAdapter(items, getActivity());

        itemsRecyclerView.setAdapter(adapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addButton.setOnClickListener(v -> {
            String itemText = itemEditText.getText().toString();
            if (!itemText.isEmpty()) {
                items.add(itemText);
                adapter.notifyItemInserted(items.size() - 1);
                itemEditText.setText("");
            }
        });

        return view;
    }
}
