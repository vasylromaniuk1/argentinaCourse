package com.solvd.delivery.myBatis;

import java.sql.SQLException;

public interface IBaseMapper<T> {

  void createItem(T item) throws SQLException;

  T getItemById(long id) throws SQLException;

  boolean updateItem(T item) throws SQLException;

  void deleteItem(long id) throws SQLException;

}
