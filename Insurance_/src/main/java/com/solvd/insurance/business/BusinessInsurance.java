package com.solvd.insurance.business;

import com.solvd.insurance.InsuranceCompanies.AbstractInsuranceType;

public class BusinessInsurance extends AbstractInsuranceType {
    private String name;
    private double cost;
    private int years;
    private String policyType = "Business";
    private int policyNum = 123;

    public BusinessInsurance(String name, double cost, int years) {
        this.name = name;
        this.cost = cost;
        this.years = years;
    }

    public double totalCost(){
        double total = getCost() + getYears();
        return total;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){this.name=name;}

    public double getCost() {
        return cost;
    }
    public void setCost(Double cost){this.cost = cost;}

    @Override
    public int getPolicyNum() {
        return this.policyNum;
    }
    public void setPolicyNum(Integer policyNum){this.policyNum = policyNum;}

    @Override
    public String getPolicyType() {
        return this.policyType;
    }
    public void setPolicyType(String policyType){this.policyType = policyType;}

    public int getYears() {
        return years;
    }
    public void setYears(Integer years){this.years = years;}
}
