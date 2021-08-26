package com.solvd.delivery;


import static com.solvd.delivery.Utils.HelpUtils.generateEmployeeForDemo;
import static com.solvd.delivery.jaxb.JaxbSerializer.deserialize;
import static com.solvd.delivery.jaxb.JaxbSerializer.serializeToXml;
import static com.solvd.delivery.parsers.EmployeeParser.deSerializeToObject;
import static com.solvd.delivery.parsers.EmployeeParser.serializeToXML;
import static com.solvd.delivery.parsers.XMLstaxParsel.parseXMLfile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.solvd.delivery.DAO.ConnectionPool;
import com.solvd.delivery.DAO.classes.Employee;
import com.solvd.delivery.DAO.mysql.jdbc.EmployeesDAO;
import com.solvd.delivery.TasksForThreads.ExecuteQuery;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

  public static final Logger LOGGER = LogManager.getLogger(Main.class);
  private final static String DB_TABLE_NAME = "mydb.Employees";
  private static ObjectMapper om = new ObjectMapper();


  public static void main(String[] args)
      throws IOException, SQLException, ExecutionException, InterruptedException {

      jacksonDemo();
      jaxbDemo();
      //TODO add more demos methods below


    // ******************* XML Parser DEMO **********************

    Employee employeeForSerialization = new Employee(123L, "Tom", "Jones", 25);
    LOGGER.info("Before serialization object: " + employeeForSerialization.toString());
    serializeToXML(employeeForSerialization, "employeeToSerialize.xml");
    Employee userInfoDeserialized = deSerializeToObject("employeeToSerialize.xml");
    LOGGER.info("Deserialized object: " + userInfoDeserialized.toString());

    // ******************* STAX DEMO **********************

    String fileName = "employees.xml";
    List<Employee> employeeList = parseXMLfile(fileName);

    for (Employee employee : employeeList) {
      LOGGER.info(employee.toString());
    }

    // ********* BEGINNING OF DAO DEMONSTRATION. SINGELTON CONNECTION ********** //

    Employee employee = new Employee();
    employee.setAge(27);
    employee.setId(131);
    employee.setName("John");
    employee.setLastName("Smith");

    EmployeesDAO db = new EmployeesDAO();
    db.createItem(employee);
    LOGGER.info("Created row -> " + db.getItemById(131).toString());

    employee.setAge(65);
    employee.setLastName("Washington");
    db.updateItem(employee);
    LOGGER.info("Updated row -> " + db.getItemById(131).toString());

    db.deleteItem(131);
    LOGGER.info(db.getItemById(131).toString());

    // ********* END OF DAO DEMONSTRATION ********** //

    // ********* BEGINNING OF DAO DEMONSTRATION. CONNECTION POOL ********** //

    //generate some employees SQL records  to pull later
    //HelpUtils.generateEmployeesRecords();

    ExecutorService threadPool = Executors.newFixedThreadPool(6);
    ConnectionPool connectionPool = ConnectionPool.setUpConnectionPoolOfSize(5);

    //create get query template
    String sqlQueryGet = "SELECT * FROM " + DB_TABLE_NAME + " WHERE id = %s";

    List<Future<ResultSet>> futures = new ArrayList<>();
    //add all the Future to the list for later get() and print

    futures.add(threadPool.
        submit(new ExecuteQuery(String.format(sqlQueryGet, 131), connectionPool)));

    futures.add(threadPool.
        submit(new ExecuteQuery(String.format(sqlQueryGet, 132), connectionPool)));

    futures.add(threadPool.
        submit(new ExecuteQuery(String.format(sqlQueryGet, 133), connectionPool)));

    futures.add(threadPool.
        submit(new ExecuteQuery(String.format(sqlQueryGet, 134), connectionPool)));

    futures.add(threadPool.
        submit(new ExecuteQuery(String.format(sqlQueryGet, 135), connectionPool)));

    futures.add(threadPool.
        submit(new ExecuteQuery(String.format(sqlQueryGet, 136), connectionPool)));

    threadPool.shutdown();

    for (Future<ResultSet> future : futures) {
      printEmployeeBio(future.get());
    }

    connectionPool.closeAllConnections();

    // ********* END OF DAO DEMONSTRATION. CONNECTION POOL ********** //

  }

  //helper method
  public static void printEmployeeBio(ResultSet rs) throws SQLException {
    LOGGER.info("\n********* THIS EMPLOYEE BIO *********\n");
    while (rs.next()) {
      LOGGER.info(rs.getString("first_name"));
      LOGGER.info(rs.getString("last_name"));
      LOGGER.info(rs.getInt("age"));
    }
  }

  public static void jacksonDemo() {
      // ******************* BEGINNING JACKSON SERIALIZE/DESERIALIZE DEMO **********************

      LOGGER.info("******************* JACKSON SERIALIZE/DESERIALIZE DEMO **********************");

      Employee employeeToSerialize = generateEmployeeForDemo("Bill", "Gates", 3401, 54);

      //serialize
      String jsonResult = null;
      try {
          jsonResult = om.writerWithDefaultPrettyPrinter()
              .writeValueAsString(employeeToSerialize);
      } catch (JsonProcessingException e) {
          LOGGER.error("Failed to serialize jackson"+ e);
      }
      LOGGER.info("Serialized by jackson: " + jsonResult);

      //de-serialize

      final ObjectReader reader = om.reader(Employee.class);
      Employee employeeDeserialized = null;
      try {
          employeeDeserialized = reader
              .readValue(jsonResult);
      } catch (JsonProcessingException e) {
         LOGGER.error("Failed to deserialize jackson"+ e);
      }

      LOGGER.info("De-serialized by jackson: " + employeeDeserialized.toString());

      // ******************* END  JACKSON SERIALIZE/DESERIALIZE DEMO **********************
  }

  public static void jaxbDemo(){
      // ******************* BEGINNING JAXB Parser DEMO **********************

      LOGGER.info("******************* BEGINNING JAXB Parser DEMO **********************");

      Employee employeeToSerialize = generateEmployeeForDemo("Bill", "Gates", 3401, 54);

      try {
          serializeToXml(employeeToSerialize);
      } catch (IOException e) {
          LOGGER.error("Failed to deserialize jaxb"+ e);
      }
      Employee jaxbUnmarshaled = null;
      try {
          jaxbUnmarshaled = deserialize("jaxb.xml");
      } catch (IOException e) {
          e.printStackTrace();
      }
      LOGGER.info("JAXB unmarshaled this object succesfully from XML: " + jaxbUnmarshaled);
  }
}
