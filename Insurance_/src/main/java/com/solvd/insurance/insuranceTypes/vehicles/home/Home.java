package com.solvd.insurance.insuranceTypes.vehicles.home;

import com.solvd.insurance.InsuranceCompanies.AbstractInsuranceType;

public class Home {
    private double costOfProperty;
    private double costOfPersonalBeloning;
    private boolean earthquakePlus;
    private int homePolicyNum;


    public Home(double costOfProperty, double costOfPersonalBeloning, boolean earthquakePlus, int homePolicyNum) {
        this.costOfProperty = costOfProperty;
        this.costOfPersonalBeloning = costOfPersonalBeloning;
        this.earthquakePlus = earthquakePlus;
        this.homePolicyNum = homePolicyNum;

    }

    public Home(){
        this(2.0, 1.0, true, 000000);
    }

    public void setCostOfProperty(double costOfProperty) {
        this.costOfProperty = costOfProperty;
    }

    public void setCostOfPersonalBeloning(double costOfPersonalBeloning) {
        this.costOfPersonalBeloning = costOfPersonalBeloning;
    }

    public void setEarthquakePlus(boolean earthquakePlus) {
        this.earthquakePlus = earthquakePlus;
    }

    public void setHomePolicyNum(int homePolicyNum) {
        this.homePolicyNum = homePolicyNum;
    }

    public double getHomeCost(){
        double total = costOfPersonalBeloning + costOfProperty;

        if(isEarthquakePlus() == true) {
            return total * 1.5;
        } else {
            return total;
        }
    }

    public double getCostOfProperty() {
        return costOfProperty;
    }


    public double getCostOfPersonalBeloning() {
        return costOfPersonalBeloning;
    }

    public boolean isEarthquakePlus() {
        return earthquakePlus;
    }

    public int getHomePolicyNum() {
        return homePolicyNum;
    }
}
