package com.solvd.delivery.Utils;

import com.solvd.delivery.DAO.classes.Employee;
import com.solvd.delivery.DAO.mysql.jdbc.EmployeesDAO;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.xml.namespace.QName;

public class HelpUtils {

  private static final String[] names =  new String[] {"John", "Clara", "Pedro", "Nate", "Rosa", "Lisa"};
  private static final String[] lasNames = new String [] {"Brown", "Mate", "Rodriges", "Moch", "Gozales", "LaRosa"};
  private static final int [] age = new int [] {35, 27, 40, 39, 50, 44};
  private static final int [] ids = new int [] {131, 132, 133, 134, 135, 136};

  public static void generateEmployeesRecordsInDb() throws SQLException {
    EmployeesDAO db = new EmployeesDAO();

    for (int i=0; i< names.length; i++) {
      Employee employee = new Employee();
      employee.setAge(age[i]);
      employee.setId(ids[i]);
      employee.setName(names[i]);
      employee.setLastName(lasNames[i]);

      db.createItem(employee);
    }

  }

  public static Employee generateEmployeeForDemo (String name, String lastName, int age, long id){
    Employee employeeForDemo = new Employee();
    employeeForDemo.setAge(age);
    employeeForDemo.setId(id);
    employeeForDemo.setName(name);
    employeeForDemo.setLastName(lastName);
    return employeeForDemo;
  }



}
