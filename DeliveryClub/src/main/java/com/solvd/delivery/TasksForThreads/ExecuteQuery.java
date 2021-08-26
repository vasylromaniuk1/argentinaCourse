package com.solvd.delivery.TasksForThreads;

import com.solvd.delivery.DAO.ConnectionPool;
import com.solvd.delivery.DAO.classes.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExecuteQuery implements Callable <ResultSet> {

  private String sqlQuery;
  private ConnectionPool connectionPool;
  private String threadName;

  public static final Logger LOGGER = LogManager.getLogger(ExecuteQuery.class);


  public ExecuteQuery (String sqlQuery, ConnectionPool connectionPool){
    this.sqlQuery = sqlQuery;
    this.connectionPool = connectionPool;
    threadName = Thread.currentThread().getName();
  }


  @Override
  public ResultSet call() throws Exception {
    Connection connection = null;
    ResultSet resultSet = null;
    LOGGER.info("New thread: " + threadName + " started for query: " + sqlQuery );
    try {
      connection = getConnectionWithRetry(connectionPool);
      PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
      resultSet = preparedStatement.executeQuery();
      return resultSet;
    } catch (SQLException | InterruptedException e) {
      LOGGER.error("Can't execute query: " + sqlQuery + "in thread: " + threadName + " caused: "+  e.getLocalizedMessage());
    }    finally {
      connectionPool.releaseConnection(connection);
    }

    return resultSet;
  }

  private Connection getConnectionWithRetry(ConnectionPool connectionPool) throws InterruptedException {
    Connection connection = connectionPool.getConnection();
    long currentTimeMillis = 0l;
    long waitLimit = System.currentTimeMillis() + 15000;

    while (connection == null && currentTimeMillis < waitLimit)
    {
      LOGGER.info("Thread " + threadName + " waiting for available connections...");
      Thread.sleep(10);
      connection = connectionPool.getConnection();
      currentTimeMillis = System.currentTimeMillis();
    }

    if (connection == null){
      LOGGER.warn("Waited for 15 seconds and gave up for thread "+ threadName);
    }
    return connection;
  }
}
