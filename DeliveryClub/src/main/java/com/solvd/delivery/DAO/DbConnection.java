package com.solvd.delivery.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

  private static DbConnection dbConnectionInstance = null;

  private Connection conn = null;

  private DbConnection () throws SQLException {
    conn = DriverManager.getConnection(
        "jdbc:mysql://52.59.193.212:3306", "root", "devintern");
  }

  public static DbConnection getInstance() throws SQLException {
    if (dbConnectionInstance == null)
      dbConnectionInstance = new DbConnection();
    return dbConnectionInstance;
  }

  public Connection getConnection() {
    return conn;
  }

  public void close() throws SQLException {
    this.conn.close();
    dbConnectionInstance = null;
  }

}
