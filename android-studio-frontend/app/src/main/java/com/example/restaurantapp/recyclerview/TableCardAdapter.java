package com.example.restaurantapp.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantapp.CurrentOrderViewModel;
import com.example.restaurantapp.R;
import com.example.restaurantapp.activities.DashboardWaiterActivity;
import com.example.restaurantapp.activities.MenuActivity;
import com.example.restaurantapp.activities.MenuListFragment;
import com.example.restaurantapp.entities.FoodItem;
import com.example.restaurantapp.entities.TableEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TableCardAdapter extends RecyclerView.Adapter<TableCardHolder> {

    Context context;
    ArrayList<TableEntity> tableList;
    CurrentOrderViewModel currentOrderViewModel;

    public TableCardAdapter(Context context, ArrayList<TableEntity> tableList, CurrentOrderViewModel currentOrderViewModel) {
        this.context = context;
        this.tableList = tableList;
        this.currentOrderViewModel = currentOrderViewModel;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<TableEntity> getTableList() {
        return tableList;
    }

    public void setTableList(ArrayList<TableEntity> tableList) {
        this.tableList = tableList;
    }


    @NonNull
    @Override
    public TableCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tables, null);


        return new TableCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableCardHolder holder, int position) {
        TableEntity table = tableList.get(position);

        holder.tableIdTextView.setText(table.getId().toString());
        holder.tableStatusTextView.setText(table.getStatus());
        if (table.getStatus().equals("OCCUPIED")) {
            int redColor = ContextCompat.getColor(context, R.color.cherry);
            holder.statusBackground.setBackgroundColor(redColor);
            holder.menuButton.setVisibility(View.GONE);
        }
        if (table.getTableOrder() != null) {
            holder.orderIdTextView.setText(table.getTableOrder().getId().toString());
        } else {
            holder.orderIdTextView.setText("None");
        }
        holder.menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentOrderViewModel.setSelectedTable(table);
                Log.d("Selected table", "" + table);
                Intent intent = new Intent(context.getApplicationContext(), MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }

    public void sortTablesList() {
        Collections.sort(tableList, new Comparator<TableEntity>() {
            @Override
            public int compare(TableEntity t1, TableEntity t2) {
                return t1.getId().compareTo(t2.getId());
            }
        });
    }
}
