package com.solvd.insurance.policyOperations;

public class eventBond extends BondInsurance {
    public eventBond(int venueCost){
        super(venueCost);
    }

    @Override
    public double calculate() {
        double total = getVenueCost() * 3;
        return total;
    }


}
