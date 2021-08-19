package com.solvd.insurance.insuranceTypes.vehicles;

import com.solvd.insurance.InsuranceCompanies.AbstractInsuranceType;

public class Vehicle extends AbstractInsuranceType {
    private boolean antiq;
    private double cost;
    private boolean accidents;
    private double miles;

    public Vehicle(int year, double cost, boolean accidents, double miles) {
        this.antiq = antiq;
        this.cost = cost;
        this.accidents = accidents;
        this.miles = miles;
    }

    public Vehicle(){
        this(0, 0.0, false, 0.0);
    }

    public void setAntiq(boolean antiq) {
        this.antiq = antiq;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setAccidents(boolean accidents) {
        this.accidents = accidents;
    }

    public void setMiles(double miles) {
        this.miles = miles;
    }

    public double getCost() {
        if(isAccidents() == true){
            return cost * 1.8;
        }else {
            return cost;
        }
    }

    @Override
    public int getPolicyNum() {
        return 0;
    }

    @Override
    public String getPolicyType() {
        return "";
    }

    public boolean isAccidents() {
        return accidents;
    }

    public double getMiles() {
        return miles;
    }

    public boolean getAntiq() {
        return antiq;
    }

    public double returnTotalCost(){
        double total = getCost() * (getMiles()/12);
        if(getAntiq() == true){
           return total * 1.2;
        } else {
            return total;
        }
    }
}
