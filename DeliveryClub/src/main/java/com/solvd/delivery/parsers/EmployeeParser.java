package com.solvd.delivery.parsers;

import com.solvd.delivery.DAO.classes.Employee;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class EmployeeParser {
    public static final Logger LOGGER = LogManager.getLogger(EmployeeParser.class);


    public static void main(String[] args) throws IOException {
        Employee employeeForSerialization = new Employee(123L,  "Tom", "Jones", 25);
        LOGGER.info("Before serialization object: " + employeeForSerialization.toString());

        serializeToXML(employeeForSerialization, "employees.xml");

        Employee userInfoDeserialized  = deSerializeToObject("employees.xml");
        LOGGER.info("Deserialized object: " + userInfoDeserialized.toString());
    }

    public static void serializeToXML (Employee userInfo, String fileName) throws IOException
    {
        XMLEncoder encoder = null;
        try{
            encoder = new XMLEncoder(new FileOutputStream(fileName));
        }catch(FileNotFoundException fileNotFound){
            LOGGER.error("ERROR: while creating file " + fileName);
        }
        encoder.writeObject(userInfo);
        encoder.close();
    }

    public static Employee deSerializeToObject (String fileName) throws IOException {
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName));
        Employee userInfo = (Employee) decoder.readObject();
        decoder.close();
        return userInfo;
    }
}


