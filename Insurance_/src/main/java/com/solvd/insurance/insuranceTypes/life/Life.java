package com.solvd.insurance.insuranceTypes.life;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Life {

    private int years;
    private boolean extremeSports;
    private boolean smoker;
    private double coverageAmount;
    private double cost;
    private static final Logger LOGGER = LogManager.getLogger(Life.class);

    public Life(int years, boolean extremeSports, boolean smoker, double coverageAmount) {
        this.years = years;
        if(extremeSports == true){
            LOGGER.info("We do not take extreme sport people.");
        }else {
            this.extremeSports = extremeSports;
        }

        if(smoker == true){
            LOGGER.fatal("We do not accept smokers!");
        }else {
            this.smoker = smoker;
        }
        this.coverageAmount = coverageAmount;
    }

    public Life(){
        this(0, false, false, 0.0);
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setExtremeSports(boolean extremeSports) {
        this.extremeSports = extremeSports;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        double total = getYears() + getCoverageAmount() * 15;
        return total;
    }

    public int getYears() {
        return years;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }
}
