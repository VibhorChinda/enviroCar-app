package org.envirocar.app.views.carselection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.envirocar.app.R;
import org.envirocar.app.views.carselection.CarSelectionActivity;

import java.util.ArrayList;

public class ManufacturerRecyclerView extends RecyclerView.Adapter<ManufacturerRecyclerView.ViewHolder> {

    private ArrayList<String> list;
    private Context context;

    public ManufacturerRecyclerView(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_manufacturer_name, parent, false);
        return new ManufacturerRecyclerView.ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.manufacturerName.setText(list.get(position));

        holder.arrowHead.setOnClickListener(view -> {
            CarSelectionActivity.recyclerViewInterface.seriesName();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView manufacturerName;
        private ImageView arrowHead;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manufacturerName = itemView.findViewById(R.id.manufacturer_name);
            arrowHead = itemView.findViewById(R.id.arrow_head);
        }
    }
}
