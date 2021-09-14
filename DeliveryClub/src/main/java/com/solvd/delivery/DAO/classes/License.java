package com.solvd.delivery.DAO.classes;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "License")
@XmlType(propOrder = {"id", "number", "expiredDate", "employeeId"})
public class License {

    private long id;
    private String number;
    private String expiredDate;
    private long employeeId;

    public License() {
    }


        public long getId () {
            return id;
        }

        public void setId ( long id){
            this.id = id;
        }

        public String getNumber () {
            return number;
        }

        public void setNumber (String number){
            this.number = number;
        }

        public String getExpiredDate () {
            return expiredDate;
        }

        public void setExpiredDate (String expiredDate){
            this.expiredDate = expiredDate;
        }

        public long getEmployeeId () {
            return employeeId;
        }

        public void setEmployeeId ( long employeeId){
            this.employeeId = employeeId;
        }

        public String toString () {
         return id + " "+ number + " " + expiredDate + " " + employeeId;
        }

}
