package org.envirocar.app.views.carselection.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.envirocar.app.R;
import org.envirocar.app.views.carselection.CarSelectionActivity;
import org.envirocar.app.views.carselection.adapters.VerticalRecyclerViewAdapter;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    public BottomSheetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bottom_sheet_car_selection, container, false);

        Button yesButton = rootView.findViewById(R.id.yes_button_car_selection);
        Button noButton = rootView.findViewById(R.id.no_button_car_selection);

        yesButton.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "Car Selected", Toast.LENGTH_SHORT).show();
            this.dismiss();
            onDestroy();
        });

        noButton.setOnClickListener(view -> {
            this.dismiss();
            onDestroy();
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        CarSelectionActivity.recyclerViewInterface.sendListAgain();
    }
}
