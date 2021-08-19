package com.solvd.insurance.policyOperations;

public abstract class BondInsurance {
    private double venueCost;

    public BondInsurance(int venueCost){
        this.venueCost = venueCost;
    }

    public double getVenueCost() {
        return venueCost;
    }
    public void setVenueCost(Double venueCost){this.venueCost = venueCost;}

    public abstract double calculate();


}
