package org.envirocar.app.views.carselection.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.envirocar.app.R;
import org.envirocar.app.views.carselection.CarSelectionActivity;
import org.envirocar.app.views.carselection.fragments.BottomSheetFragment;
import org.envirocar.app.views.carselection.interfaces.RecyclerViewInterface;
import org.envirocar.app.views.carselection.models.ManufactureObject;

import java.util.ArrayList;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ManufactureObject> list;
    private FragmentManager fragmentManager;
    private Context context;

    public VerticalRecyclerViewAdapter(ArrayList<ManufactureObject> list, FragmentManager fragmentManager, Context context) {
        this.list = list;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_car_list_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ManufactureObject manufactureObject = list.get(position);
        holder.name.setText(manufactureObject.getName());
        holder.number.setText(manufactureObject.getHsn());
        holder.description.setText(manufactureObject.getLinks().get(0).getRel());
        holder.radioButton.setSelected(false);

        holder.radioButton.setOnClickListener(view -> {
            holder.radioButton.setSelected(true);
            CarSelectionActivity.recyclerViewInterface.selectCar(manufactureObject.getName(), manufactureObject.getLinks().get(0).getRel());
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
