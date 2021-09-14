package com.solvd.delivery.myBatis;

import com.solvd.delivery.DAO.classes.Employee;
import com.solvd.delivery.DAO.classes.License;
import java.sql.SQLException;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

//this class serves as DAO in MyBatis world
public interface IEmployeeMapper extends IBaseMapper<Employee> {

  @Delete("Delete from Employees where id=#{id}")
  @Override
  void deleteItem(long id) throws SQLException;


  @Select(
      "SELECT * FROM Employees where id=#{id}")

  @Results(value = {
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "first_name"),
      @Result(property = "lastName", column = "last_name"),
      @Result(property = "age", column = "age")
  })

  @Override
  Employee getItemById(long id) throws SQLException;



  @Insert("INSERT into Employees (id,first_name,last_name,age) VALUES (#{id},#{name},#{lastName},#{age})")
  @Override
  void createItem(Employee item) throws SQLException;


}

