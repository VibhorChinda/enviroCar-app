package org.envirocar.app.views.carselection.interfaces;

public interface RecyclerViewInterface {

    void sendListAgain();
    void selectCar(String carName, String carDescription, int selectedRadioButton);
    void searchSelectedCars(String carName);
    void seriesName();
    void carNames();
}
