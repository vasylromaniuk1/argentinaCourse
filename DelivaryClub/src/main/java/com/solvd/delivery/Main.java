package com.solvd.delivery;


import static com.solvd.delivery.parsers.EmployeeParser.deSerializeToObject;
import static com.solvd.delivery.parsers.EmployeeParser.serializeToXML;
import static com.solvd.delivery.parsers.XMLstaxParsel.parseXMLfile;

import java.io.IOException;
import java.sql.SQLException;

import com.solvd.delivery.DAO.classes.Employee;
import com.solvd.delivery.DAO.mysql.jdbc.EmployeesDAO;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws IOException, SQLException {

        // ******************* XML Parser DEMO **********************

        Employee employeeForSerialization = new Employee(123L,  "Tom", "Jones", 25);
        LOGGER.info("Before serialization object: " + employeeForSerialization.toString());

        serializeToXML(employeeForSerialization, "employee1.xml");
        Employee userInfoDeserialized  = deSerializeToObject("employee1.xml");
        LOGGER.info("Deserialized object: " + userInfoDeserialized.toString());

        // ******************* STAX DEMO **********************

        String fileName = "employees.xml";
        List<Employee> employeeList = parseXMLfile(fileName);
        for (Employee employee : employeeList) {
            LOGGER.info(employee.toString());
        }


        // ********* BEGINNING OF DAO DEMONSTRATION ********** //

        Employee employee = new Employee();
        employee.setAge(27);
        employee.setId(131);
        employee.setName("John");
        employee.setLastName("Smith");


        EmployeesDAO db = new EmployeesDAO();
        db.createItem(employee);
        LOGGER.info("Created row -> "+db.getItemById(131).toString());

        employee.setAge(131);
        employee.setLastName("Washington");
        db.updateItem(employee);
        LOGGER.info("Updated row -> " + db.getItemById(131).toString());

        db.deleteItem(131);
        LOGGER.info(db.getItemById(131).toString());

        // ********* END OF DAO DEMONSTRATION ********** //

    }

}
