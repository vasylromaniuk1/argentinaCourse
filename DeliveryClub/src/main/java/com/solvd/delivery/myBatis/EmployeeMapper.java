package com.solvd.delivery.myBatis;

import com.solvd.delivery.DAO.classes.Employee;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {


  @Select(
      "SELECT * FROM Employees where id=#{id}")

  @Results(value = {
      @Result(property = "id", column = "id"),
      @Result(property = "name", column = "first_name"),
      @Result(property = "lastName", column = "last_name"),
      @Result(property = "age", column = "age")
  })

  public Employee getEmployee(int id);

}
