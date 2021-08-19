package com.solvd.insurance.insuranceTypes.vehicles.vehicles;

public class Motorcycle extends Vehicle{

    private String color;
    private final String policyType = "Motorcycle";

    public Motorcycle(int year, double cost, boolean accidents, double miles, String name) {
        super(year, cost, accidents, miles);
        this.color = color;
    }

    public Motorcycle(){
        this(0, 0.0, false, 0.0, "");
    }

    public void setColor(String color) {
        this.color = color;
    }

    //motorcycle insurance will be $55 more.
    @Override
    public double returnTotalCost() {
        return super.returnTotalCost() + 55;
    }

    public String getPolicyType() {
        return policyType;
    }

}
