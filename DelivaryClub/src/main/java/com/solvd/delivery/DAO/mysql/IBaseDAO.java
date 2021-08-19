package com.solvd.delivery.DAO.mysql;

import java.sql.SQLException;

public interface IBaseDAO <T>{
    void createItem(T item) throws SQLException;
    T getItemById(long id) throws SQLException;
    boolean updateItem(T item) throws SQLException;
    void deleteItem(long id) throws SQLException;
}
