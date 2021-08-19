package com.solvd.insurance.policyOperations;

import com.solvd.insurance.customer.Person;
import com.solvd.insurance.insuranceTypes.home.RentersForHome;
import com.solvd.insurance.insuranceTypes.vehicles.home.Home;
import com.solvd.insurance.insuranceTypes.life.Life;
import com.solvd.insurance.insuranceTypes.pets.Pets;
import com.solvd.insurance.insuranceTypes.vehicles.vehicles.Boat;
import com.solvd.insurance.insuranceTypes.vehicles.vehicles.Car;
import com.solvd.insurance.insuranceTypes.vehicles.vehicles.Motorcycle;

import java.util.Objects;

public class Policy {
    private int policyNumber;
    private Person person;
    private Car carInsurance;
    private Home homeInsurance;
    private RentersForHome rentersInsurance;
    private Boat boatInsurance;
    private Motorcycle motorcycleInsurance;
    private Life lifeInsurance;
    private String startDate;
    private String endDate;
    private String name;
    private Pets pets;

    public Policy(int policyNumber, Person person, Car carInsurance, Home homeInsurance, Boat boatInsurance,
                  Motorcycle motorcycleInsurance, Life lifeInsurance, String startDate, String endDate,
                  String name, RentersForHome rentersInsurance, Pets pets) {
        this.policyNumber = policyNumber;
        this.person = person;
        this.carInsurance = carInsurance;
        this.homeInsurance = homeInsurance;
        this.boatInsurance = boatInsurance;
        this.motorcycleInsurance = motorcycleInsurance;
        this.lifeInsurance = lifeInsurance;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.rentersInsurance = rentersInsurance;
        this.pets=pets;
    }

   //corrected equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Policy p = (Policy) obj;
        return policyNumber == p.getPolicyNumber();
    }

//    @Override
//    public boolean equals(Object obj) {
//        Policy p = (Policy) obj;
//        return policyNumber == p.getPolicyNumber();
//    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    @Override
    public String toString() {
        return getName() + " , you bought our awesome policy!";
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public Car getCarInsurance() {
        return carInsurance;
    }

    public Home getHomeInsurance() {
        return homeInsurance;
    }

    public RentersForHome getRentersInsurance() {
        return rentersInsurance;
    }

    public Boat getBoatInsurance() {
        return boatInsurance;
    }

    public Motorcycle getMotorcycleInsurance() {
        return motorcycleInsurance;
    }

    public Life getLifeInsurance() {
        return lifeInsurance;
    }

    public Pets getPets() {
        return pets;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setCarInsurance(Car carInsurance) {
        this.carInsurance = carInsurance;
    }

    public void setHomeInsurance(Home homeInsurance) {
        this.homeInsurance = homeInsurance;
    }

    public void setRentersInsurance(RentersForHome rentersInsurance) {
        this.rentersInsurance = rentersInsurance;
    }

    public void setBoatInsurance(Boat boatInsurance) {
        this.boatInsurance = boatInsurance;
    }

    public void setMotorcycleInsurance(Motorcycle motorcycleInsurance) {
        this.motorcycleInsurance = motorcycleInsurance;
    }

    public void setLifeInsurance(Life lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPets(Pets pets) {
        this.pets = pets;
    }

    public Person getPerson() {
        return person;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double grandTotalCost(){
        double total = carInsurance.getCost() + motorcycleInsurance.getCost() +
                homeInsurance.getHomeCost() + boatInsurance.returnTotalCost() +
                lifeInsurance.getCost() + pets.getCost();

        return total;
    }

    public String getName() {
        return name;
    }


}
