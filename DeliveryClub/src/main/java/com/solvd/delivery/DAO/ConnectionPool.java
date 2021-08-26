package com.solvd.delivery.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {

  public static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
  private static ConnectionPool dbConnectionPoolInstance = null;


  private int maxConnections;

  private Stack<Connection> pool;
  static int poolSize;

  private ConnectionPool(int numberOfConnections) {
    this.maxConnections = numberOfConnections;
    this.pool = new Stack<>();
    this.poolSize = pool.size();

    for (int i = 0; i < maxConnections; i++, poolSize++)
      pool.push(createNewConnection());
  }

  public static ConnectionPool setUpConnectionPoolOfSize (int numberOfConnections) {
    if (dbConnectionPoolInstance == null)
      LOGGER.info("Setting up a new connection pool");
      dbConnectionPoolInstance = new ConnectionPool(numberOfConnections);
    return dbConnectionPoolInstance;
  }

  private Connection createNewConnection () {
    Connection connection = null;
      try {
        connection = DriverManager.getConnection(
            "jdbc:mysql://52.59.193.212:3306", "root", "devintern");
      } catch (SQLException e){
        LOGGER.error("Error getting database connection \n" + e.getLocalizedMessage());
      }
    return connection;
  }

  public boolean releaseConnection(Connection connection) {
    if (poolSize < maxConnections && connection !=null){
      pool.push(connection);
      poolSize++;
      return true;
    }else {
      throw new RuntimeException("Unable to release the connection. Pool is full: " + poolSize);
    }
  }

  public void closeAllConnections(){
    LOGGER.info("Closing all connections");
    try {
        while (!pool.isEmpty()){
          pool.pop().close();
        }
    } catch (SQLException e){
      LOGGER.error("Error while closing the connection : " + e.getLocalizedMessage());
    }
  }


  public Connection getConnection() {
    Connection connection = null;
    if (pool.isEmpty()){
      LOGGER.info("No connections available");
      return connection;
    } else {
      connection = pool.pop();
      poolSize--;
    }
    return connection;
  }

}
