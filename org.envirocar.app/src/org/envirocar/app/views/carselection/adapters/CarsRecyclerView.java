package org.envirocar.app.views.carselection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.envirocar.app.R;

import java.util.ArrayList;

public class CarsRecyclerView extends RecyclerView.Adapter<CarsRecyclerView.ViewHolder> {

    private ArrayList<String> list;
    private Context context;

    public CarsRecyclerView(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_car_name, parent, false);
        return new CarsRecyclerView.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.carName.setText(list.get(position));

        holder.radioButton.setOnClickListener(view -> {
            Snackbar.make(holder.radioButton, "Car Selected", Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView carName;
        private RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            carName = itemView.findViewById(R.id.car_name);
            radioButton = itemView.findViewById(R.id.radio_car);
        }
    }

}
