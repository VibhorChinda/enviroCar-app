package org.envirocar.app.views.carselection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.envirocar.app.R;
import org.envirocar.app.views.carselection.CarSelectionActivity;
import org.envirocar.app.views.carselection.models.ManufactureObject;
import java.util.ArrayList;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ManufactureObject> list;
    private FragmentManager fragmentManager;
    private Context context;
    private int selectedRadioButtonId = -1;
    private RadioButton selectedRadioButton = null;
    private String selectedCarName = null;


    public VerticalRecyclerViewAdapter(ArrayList<ManufactureObject> list, FragmentManager fragmentManager, Context context, int selectedRadioButtonId, String selectedCarName) {
        this.list = list;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.selectedRadioButtonId = selectedRadioButtonId;
        this.selectedCarName = selectedCarName;
    }

    public VerticalRecyclerViewAdapter(ArrayList<ManufactureObject> list, FragmentManager fragmentManager, Context context) {
        this.list = list;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_car_list_item, parent, false);
        selectedRadioButton = rootView.findViewById(selectedRadioButtonId);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ManufactureObject manufactureObject = list.get(position);
        holder.name.setText(manufactureObject.getName());
        holder.number.setText(manufactureObject.getHsn());
        holder.description.setText(manufactureObject.getLinks().get(0).getRel());
        if(manufactureObject.getName().equals(selectedCarName)) {
            holder.radioButton.setChecked(true);
        }
        else {
            holder.radioButton.setChecked(false);
        }

        holder.radioButton.setOnClickListener(view -> {
            if(selectedRadioButton != null)
            {
                selectedRadioButton.setChecked(false);
                selectedRadioButton.isChecked();
            }
            selectedRadioButton = holder.radioButton;
            selectedCarName = holder.name.getText().toString();
            holder.radioButton.setSelected(true);
            int Id = selectedRadioButton.getId();
            Snackbar.make(holder.radioButton, selectedCarName + " is selected", Snackbar.LENGTH_SHORT).show();
            CarSelectionActivity.recyclerViewInterface.selectCar(manufactureObject.getName(), manufactureObject.getLinks().get(0).getRel(), selectedRadioButtonId);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private TextView number;
        private RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView5);
            description = itemView.findViewById(R.id.textView7);
            number = itemView.findViewById(R.id.textView8);
            radioButton = itemView.findViewById(R.id.car_selection_radio_button);
        }
    }

}
