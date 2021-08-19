package com.solvd.insurance.insuranceTypes.vehicles;

public class Boat extends Vehicle {
    private String boatName;
    private double boatSize;
    private final String policyType = "Boat";

    public Boat(int year, double cost, boolean accidents, double miles, String boatName, double boatSize) {
        super(year, cost, accidents, miles);
        this.boatName = boatName;
        this.boatSize = boatSize;
    }

    public String getBoatName() {
        return boatName;
    }
    public void setBoatName(String boatName){this.boatName = boatName;}

    public double getBoatSize() {
        return boatSize;
    }
    public void setBoatSize(Double boatSize){this.boatSize = boatSize;}

    @Override
    public double returnTotalCost() {
        return super.returnTotalCost() * getBoatSize();
    }

    public String getPolicyType() {
        return policyType;
    }


}
