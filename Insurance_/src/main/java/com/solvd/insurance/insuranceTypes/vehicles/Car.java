package com.solvd.insurance.insuranceTypes.vehicles;

public class Car extends Vehicle {
    private String name;
    private int CarPolicyNum;
    private int carPolicyNum;
    private String policyType = "Car";

    public Car(int year, double cost, boolean accidents, double miles, String name, int carPolicyNum) {
        super(year, cost, accidents, miles);
        this.name = name;
        this.carPolicyNum = carPolicyNum;
    }

    public Car(){
        this(0, 0.0, false, 0.0, "", 000);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCarPolicyNum(int carPolicyNum) {
        CarPolicyNum = carPolicyNum;
    }

    @Override
    public double getCost() {
        return super.getCost() + 3.5;
    }

    public int getPolicyNum() {
        return carPolicyNum;
    }

    public String getPolicyType() {
        return policyType;
    }
}
