package com.jagdon.jagdon_bottom_nav;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {
    private List<String> items;
    private Context context;

    public ToDoAdapter(List<String> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ToDoViewHolder holder, final int position) {
        final String item = items.get(position);
        holder.checkBox.setText(item);

        holder.checkBox.setOnClickListener(v -> {
            if(holder.checkBox.isChecked()){
                showDialogEdit(item, position);
            }
            else{
                holder.checkBox.setChecked(false);
            }
        });

        // Set up the delete button click listener
        holder.deleteButton.setOnClickListener(v -> {
            items.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, items.size());
        });
    }

    private void showDialogEdit(String item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Task");

        // Set up the input
        final EditText input = new EditText(context);
        input.setText(item);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newItem = input.getText().toString();
                items.set(position, newItem);
                notifyItemChanged(position);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView deleteButton;

        public ToDoViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}

