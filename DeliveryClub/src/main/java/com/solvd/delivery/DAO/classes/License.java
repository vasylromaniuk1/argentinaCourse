package com.solvd.delivery.DAO.classes;

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

        public void setNumber (String name){
            this.number = number;
        }

        public String getExpiredDate () {
            return expiredDate;
        }

        public void setExpiredDate (String lastName){
            this.expiredDate = expiredDate;
        }

        public long getEmployeeId () {
            return employeeId;
        }

        public void setEmployeeId ( long employeeId){
            this.employeeId = employeeId;
        }

        //public String toString () {//overriding the toString() method
        // return id + " " number + " " + expiredDate + " " + employeeId;}




}
