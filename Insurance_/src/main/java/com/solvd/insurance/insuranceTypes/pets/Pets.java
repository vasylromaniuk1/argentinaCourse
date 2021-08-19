package com.solvd.insurance.insuranceTypes.pets;

public class Pets {
    private String name;
    private int age;

    public Pets(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Pets(){
        this("no pets", 0);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getCost(){
        double total=getAge()+15;
        return total;

    }
}
