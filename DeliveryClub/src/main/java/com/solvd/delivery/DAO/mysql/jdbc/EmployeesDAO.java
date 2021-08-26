package com.solvd.delivery.DAO.mysql.jdbc;


import com.solvd.delivery.DAO.DbConnection;
import com.solvd.delivery.DAO.classes.Employee;
import com.solvd.delivery.DAO.mysql.IBaseDAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class EmployeesDAO extends AbstractMysqlJdbcDAO implements IBaseDAO<Employee> {
  private final static Logger LOGGER = Logger.getLogger(String.valueOf(EmployeesDAO.class));
  private final static String GET_EMPLOYEES_BY_ID = "SELECT * FROM Employees WHERE id = ?";
  private final static String DB_TABLE_NAME = "mydb.Employees";
  private final String ID_COL = "id";
  private final String FIRST_NAME_COL = "first_name";
  private final String LAST_NAME_COL = "last_name";
  private final String AGE_COL = "AGE";


  @Override
  public void createItem(Employee employee) throws SQLException {

    DbConnection dbConnection = DbConnection.getInstance();
    Connection connection = dbConnection.getConnection();
    try {
      //update statement
      PreparedStatement ps = connection.prepareStatement("INSERT INTO "+DB_TABLE_NAME+" VALUES (?, ?, ?, ?)");

      ps.setLong(1, employee.getId());
      ps.setString(2, employee.getName());
      ps.setString(3, employee.getLastName());
      ps.setInt(4, employee.getAge());

      int i = ps.executeUpdate();

    } catch (SQLException ex) {
     LOGGER.info( ex.getLocalizedMessage());
    }
    finally {
      dbConnection.close();

    }
  }

  @Override
  public Employee getItemById(long id) throws SQLException {
    DbConnection dbConnection = DbConnection.getInstance();
    Connection connection = dbConnection.getConnection();
    try {

      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM "+ DB_TABLE_NAME +" WHERE id ="+id);
      ResultSet resultSet = preparedStatement.executeQuery();
      Employee employee = new Employee();

      while(resultSet.next())
      {
        employee.setId(resultSet.getLong(ID_COL));
        employee.setName(resultSet.getString(FIRST_NAME_COL));
        employee.setLastName(resultSet.getString(LAST_NAME_COL));
        employee.setAge(resultSet.getInt(AGE_COL));
      }
      return employee;

    } catch (SQLException e) {
      LOGGER.info(e.getLocalizedMessage());
    }    finally {
      connection.close();
      dbConnection.close();
    }

    return null;
  }

  @Override
  public boolean updateItem(Employee employee) throws SQLException {
    DbConnection dbConnection = DbConnection.getInstance();
    Connection connection = dbConnection.getConnection();
    try {
      //update statement
      PreparedStatement ps = connection.prepareStatement("UPDATE "+ DB_TABLE_NAME +" SET first_name=?, last_name=?, age=? WHERE id = ?");

      ps.setString(1, employee.getName());
      ps.setString(2, employee.getLastName());
      ps.setInt(3, employee.getAge());
      ps.setLong(4, employee.getId());
      int i = ps.executeUpdate();

      if(i == 1) {
        return true;
      }

    } catch (SQLException ex) {
      LOGGER.info( ex.getLocalizedMessage());
    }
    finally {
      connection.close();
      dbConnection.close();
    }

    return false;
  }



  @Override
  public void deleteItem(long id) throws SQLException {

    DbConnection dbConnection = DbConnection.getInstance();
    Connection connection = dbConnection.getConnection();
    try {
      //delete statement
      PreparedStatement ps = connection.prepareStatement("DELETE FROM "+DB_TABLE_NAME+" WHERE id=" + id);
      ps.executeUpdate();

    } catch (SQLException ex) {
      LOGGER.info( ex.getLocalizedMessage());
    }
    finally {
      connection.close();
      dbConnection.close();
    }

  }
}

