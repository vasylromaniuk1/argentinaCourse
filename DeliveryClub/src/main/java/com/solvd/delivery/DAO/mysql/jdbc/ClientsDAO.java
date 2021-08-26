package com.solvd.delivery.DAO.mysql.jdbc;


import com.solvd.delivery.DAO.DbConnection;
import com.solvd.delivery.DAO.classes.Client;
import com.solvd.delivery.DAO.mysql.IBaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ClientsDAO extends AbstractMysqlJdbcDAO implements IBaseDAO<Client> {
  private final static Logger LOGGER = Logger.getLogger(String.valueOf(ClientsDAO.class));
  private final static String GET_EMPLOYEES_BY_ID = "SELECT * FROM Employees WHERE id = ?";
  private final static String DB_TABLE_NAME = "mydb.Clients";
  private final String ID_COL = "id";
  private final String FIRST_NAME_COL = "first_name";
  private final String LAST_NAME_COL = "last_name";
  private final String AGE_COL = "AGE";


  @Override
  public void createItem(Client client) throws SQLException {

    Connection connection = DbConnection.getInstance().getConnection();
    try {


      PreparedStatement ps = connection.prepareStatement("INSERT INTO "+DB_TABLE_NAME+" VALUES (?, ?, ?, ?)");

      ps.setLong(1, client.getId());
      ps.setString(2, client.getName());
      ps.setString(3, client.getLastName());
      ps.setInt(4, client.getAge());

      int i = ps.executeUpdate();

    } catch (SQLException ex) {
      LOGGER.info( ex.getLocalizedMessage());
    }
  }


  @Override
  public Client getItemById(long id) throws SQLException {
    Connection connection = DbConnection.getInstance().getConnection();
    try {

      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM "+ DB_TABLE_NAME +" WHERE id ="+id);
      ResultSet resultSet = preparedStatement.executeQuery();
      Client client = new Client();

      while(resultSet.next())
      {

        client = extractClient(resultSet);
      }
      return client;

    } catch (SQLException e) {
      LOGGER.info(e.getLocalizedMessage());
    }  finally {
      connection.close();
    }
    return null;
  }


  @Override
  public boolean updateItem(Client client) throws SQLException {
    Connection connection = DbConnection.getInstance().getConnection();
    try {

      PreparedStatement ps = connection.prepareStatement("UPDATE "+ DB_TABLE_NAME +" SET first_name=?, last_name=?, age=? WHERE id = ?");

      ps.setString(1, client.getName());
      ps.setString(2, client.getLastName());
      ps.setInt(3, client.getAge());
      ps.setLong(4, client.getId());
      int i = ps.executeUpdate();

      if(i == 1) {
        return true;
      }

    } catch (SQLException ex) {
      LOGGER.info( ex.getLocalizedMessage());
    }

    return false;
  }



  @Override
  public void deleteItem(long id) throws SQLException {

    Connection connection = DbConnection.getInstance().getConnection();
    try {

      PreparedStatement ps = connection.prepareStatement("DELETE FROM "+DB_TABLE_NAME+" WHERE id=" + id);
      ps.executeUpdate();

    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    finally {
      connection.close();
    }

  }


  private Client extractClient (ResultSet resultSet) throws SQLException {
    Client client = new Client();
    client.setId(resultSet.getLong(ID_COL));
    client.setName(resultSet.getString(FIRST_NAME_COL));
    client.setLastName(resultSet.getString(LAST_NAME_COL));
    client.setAge(resultSet.getInt(AGE_COL));
    return client;
  }
}

