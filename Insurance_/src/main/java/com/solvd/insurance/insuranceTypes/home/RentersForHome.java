package com.solvd.insurance.insuranceTypes.home;

import com.solvd.insurance.insuranceTypes.vehicles.home.Home;

public class RentersForHome extends Home {

    private double hoAInsuranceCost;

    public RentersForHome(double costOfProperty, double costOfPersonalBeloning, boolean earthquakePlus, double hoAInsuranceCost, int homePolicyNum) {
        super(costOfProperty, costOfPersonalBeloning, earthquakePlus, homePolicyNum);
        this.hoAInsuranceCost = hoAInsuranceCost;
    }


    public RentersForHome(){
        this(0.0, 0.0, true, 0.0, 0000000);
    }

    public void setHoAInsuranceCost(double hoAInsuranceCost) {
        this.hoAInsuranceCost = hoAInsuranceCost;
    }

    @Override
    public double getHomeCost() {

        return super.getHomeCost() + getHoAInsuranceCost();
    }

    public double getHoAInsuranceCost() {
        return hoAInsuranceCost;
    }
}
