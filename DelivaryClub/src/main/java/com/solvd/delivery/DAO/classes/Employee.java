package com.solvd.delivery.DAO.classes;

public class Employee {

  private long id;
  private String name;
  private String lastName;
  private int age;

  public Employee () {

  }


  public Employee (long id, String name, String lastName, int age) {
   this.id = id;
   this.lastName = lastName;
   this.name = name;
   this.age = age;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String toString(){//overriding the toString() method
    return id+" "+name+" "+lastName + " " + age;
  }

}
