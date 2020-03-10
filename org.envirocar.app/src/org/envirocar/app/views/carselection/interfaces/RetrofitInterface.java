package org.envirocar.app.views.carselection.interfaces;

import org.envirocar.app.views.carselection.models.ManufactureObject;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    @GET("manufacturers")
    Call<ArrayList<ManufactureObject>> getManufactureObjectList();
}
