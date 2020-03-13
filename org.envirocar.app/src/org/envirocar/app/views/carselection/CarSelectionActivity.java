package org.envirocar.app.views.carselection;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;

import org.envirocar.app.R;
import org.envirocar.app.views.carselection.adapters.HorizontalRecyclerViewAdapter;
import org.envirocar.app.views.carselection.adapters.VerticalRecyclerViewAdapter;
import org.envirocar.app.views.carselection.interfaces.RecyclerViewInterface;
import org.envirocar.app.views.carselection.interfaces.RetrofitInterface;
import org.envirocar.app.views.carselection.models.ManufactureObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarSelectionActivity extends AppCompatActivity {

    private RecyclerView horizontalRecyclerView;
    private RecyclerView verticalRecyclerView;
    private ArrayList<String> sortingArrayList = new ArrayList<>();
    private ArrayList<ManufactureObject> manufactureObjects = new ArrayList<>();
    private LinearLayoutManager horizontalLayoutManager;
    private LinearLayoutManager verticalLayoutManager;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    public static RecyclerViewInterface recyclerViewInterface;
    private Toolbar toolbar;
    private ShimmerFrameLayout shimmerFrameLayout;
    private String selectedCarName = null;
    private int selectedCarNameId = -1;
    private ImageView backButton;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selection_layout);

        toolbar = findViewById(R.id.toolbar2);
        searchBar = findViewById(R.id.search_edit_text_car_selection);
        horizontalRecyclerView = findViewById(R.id.recyclerView);
        verticalRecyclerView = findViewById(R.id.horizonta_recycler_view);
        shimmerFrameLayout = findViewById(R.id.shimmer_container);
        backButton = findViewById(R.id.go_back_button_car_selection);
        toolbar.setVisibility(View.INVISIBLE);
        horizontalRecyclerView.setVisibility(View.INVISIBLE);
        verticalRecyclerView.setVisibility(View.INVISIBLE);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();

        getSortingOptionsList();
        getManufactureList();

        backButton.setOnClickListener(view -> finish());

        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId){
                case EditorInfo.IME_ACTION_DONE:
                case EditorInfo.IME_ACTION_NEXT:
                case EditorInfo.IME_ACTION_PREVIOUS:
                    if(searchBar.getText().toString().isEmpty())
                    {
                        CarSelectionActivity.recyclerViewInterface.searchSelectedCars("All");
                    }
                    else {
                        CarSelectionActivity.recyclerViewInterface.searchSelectedCars(searchBar.getText().toString());
                    }
                    return true;
            }
            return false;
        });

        recyclerViewInterface = new RecyclerViewInterface() {
            @Override
            public void sendListAgain() {
                verticalRecyclerView.setLayoutManager(verticalLayoutManager);
                verticalRecyclerView.setAdapter(new VerticalRecyclerViewAdapter(manufactureObjects, fragmentManager, getApplicationContext(), selectedCarNameId, selectedCarName));
            }

            @Override
            public void selectCar(String carName, String carDescription, int selectedRadioButtonId) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("CarSelected", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Selected Car Name", carName);
                editor.putString("Selected Car Description", carDescription);
                editor.putInt("Selected Radio Id", selectedRadioButtonId);
                editor.apply();
            }

            @Override
            public void searchSelectedCars(String carName) {
                ArrayList<ManufactureObject> manufactureObjectsLocal = new ArrayList<>();

                if (carName.equals("All")) {
                    manufactureObjectsLocal = manufactureObjects;
                }
                else {
                    for (ManufactureObject manufactureObject : manufactureObjects) {
                        if (manufactureObject.getName().contains(carName)) {
                            manufactureObjectsLocal.add(manufactureObject);
                        }
                    }
                }
                    verticalRecyclerView.setLayoutManager(verticalLayoutManager);
                    verticalRecyclerView.setAdapter(new VerticalRecyclerViewAdapter(manufactureObjectsLocal, fragmentManager, getApplicationContext()));
            }
        };
    }

    private void getManufactureList() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://processing.envirocar.org/vehicles/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        final RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ArrayList<ManufactureObject>> call = retrofitInterface.getManufactureObjectList();
        call.enqueue(new Callback<ArrayList<ManufactureObject>>() {
            @Override
            public void onResponse(Call<ArrayList<ManufactureObject>> call, Response<ArrayList<ManufactureObject>> response) {
                manufactureObjects = response.body();
                verticalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                verticalRecyclerView.setLayoutManager(verticalLayoutManager);
                verticalRecyclerView.setAdapter(new VerticalRecyclerViewAdapter(manufactureObjects, fragmentManager, getApplicationContext(), selectedCarNameId, selectedCarName));
                shimmerFrameLayout.setVisibility(View.INVISIBLE);
                shimmerFrameLayout.stopShimmerAnimation();
                toolbar.setVisibility(View.VISIBLE);
                horizontalRecyclerView.setVisibility(View.VISIBLE);
                verticalRecyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<ManufactureObject>> call, Throwable t) {

            }
        });

    }

    private void getSortingOptionsList() {
        sortingArrayList.add("All");
        sortingArrayList.add("Petrol");
        sortingArrayList.add("Diesel");
        sortingArrayList.add("AUDI");
        sortingArrayList.add("BMW");
        sortingArrayList.add("Electricity");
        sortingArrayList.add("Gas");
        sortingArrayList.add("FORD");

        horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclerView.setLayoutManager(horizontalLayoutManager);
        horizontalRecyclerView.setAdapter(new HorizontalRecyclerViewAdapter(sortingArrayList, "All", getApplicationContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("CarSelected", 0);
        selectedCarName = sharedPreferences.getString("Selected Car Name", null);
        selectedCarNameId = sharedPreferences.getInt("Selected Car Id", 0);
    }
}
