package org.envirocar.app.views.carselection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_selection_layout);

        toolbar = findViewById(R.id.toolbar2);
        horizontalRecyclerView = findViewById(R.id.recyclerView);
        verticalRecyclerView = findViewById(R.id.horizonta_recycler_view);
        shimmerFrameLayout = findViewById(R.id.shimmer_container);

        toolbar.setVisibility(View.INVISIBLE);
        horizontalRecyclerView.setVisibility(View.INVISIBLE);
        verticalRecyclerView.setVisibility(View.INVISIBLE);
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmerAnimation();

        getSortingOptionsList();
        getManufactureList();

        recyclerViewInterface = () -> {
            verticalRecyclerView.setLayoutManager(verticalLayoutManager);
            verticalRecyclerView.setAdapter(new VerticalRecyclerViewAdapter(manufactureObjects, fragmentManager, getApplicationContext()));
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
                verticalRecyclerView.setAdapter(new VerticalRecyclerViewAdapter(manufactureObjects, fragmentManager, getApplicationContext()));
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
        sortingArrayList.add("Audi");
        sortingArrayList.add("BMW");
        sortingArrayList.add("Electricity");
        sortingArrayList.add("Gas");
        sortingArrayList.add("Mercedes");

        horizontalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRecyclerView.setLayoutManager(horizontalLayoutManager);
        horizontalRecyclerView.setAdapter(new HorizontalRecyclerViewAdapter(sortingArrayList));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
