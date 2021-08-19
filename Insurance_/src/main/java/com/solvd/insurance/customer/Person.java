package com.solvd.insurance.customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person {
    private String name;
    private int age;
    private String address;

    private static final Logger LOGGER = LogManager.getLogger(Person.class);

    public Person(String name, int age) {
        this.name = name;
        setAge(age);
    }


    public Person(String name, int age, String address) {
        this.name = name;
        this.address = address;
        setAge(age);
    }

    private boolean isUnderaged(int age){
        return (age < 16);
    }

    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}

    public int getAge() {
        return age;
    }
    public void setAge(String Age){this.age= age;}

    public String getAddress() {
        if (this.address==null){
            return "Address is not disclosed";
        }
        return address;

    }

    public void setAge(int age) {
        if (isUnderaged(age)){
            LOGGER.info("Unlicensed/Underaged cannot have insurance");
            this.age = -1;
        }else
        {
            this.age=age;
        }
    }
}
