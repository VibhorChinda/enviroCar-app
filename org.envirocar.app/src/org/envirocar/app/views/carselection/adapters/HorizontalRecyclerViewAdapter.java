package org.envirocar.app.views.carselection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.envirocar.app.R;
import org.envirocar.app.views.carselection.CarSelectionActivity;

import java.util.ArrayList;


public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> list;
    private String selectedOption;
    private Context context;
    private TextView selectedCapsule;

    public HorizontalRecyclerViewAdapter(ArrayList<String> list, String selectedOption, Context context) {
        this.list = list;
        this.selectedOption = selectedOption;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sorting_capsule_background, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.capsule.setText(list.get(position));
        if(list.get(position).equals(selectedOption))
        {
            holder.capsule.setTextColor(context.getResources().getColor(R.color.cario_color_primary_dark));
            holder.capsule.setTextSize(context.getResources().getDimension(R.dimen.text_medium_capsule));
            selectedCapsule = holder.capsule;
        }
        else {
            holder.capsule.setTextColor(context.getResources().getColor(R.color.black));
            holder.capsule.setTextSize(context.getResources().getDimension(R.dimen.text_small_capsule));
        }

        holder.capsule.setOnClickListener(view -> {
            if(selectedCapsule != null)
            {
                selectedCapsule.setTextColor(context.getResources().getColor(R.color.black));
                selectedCapsule.setTextSize(context.getResources().getDimension(R.dimen.text_small_capsule));
            }

            holder.capsule.setTextColor(context.getResources().getColor(R.color.cario_color_primary_dark));
            holder.capsule.setTextSize(context.getResources().getDimension(R.dimen.text_medium_capsule));
            selectedOption = holder.capsule.getText().toString();
            selectedCapsule = holder.capsule;
            CarSelectionActivity.recyclerViewInterface.searchSelectedCars(holder.capsule.getText().toString());
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView capsule;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            capsule = itemView.findViewById(R.id.textView2);
        }
    }
}
